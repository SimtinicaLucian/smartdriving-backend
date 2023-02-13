package ro.softwarelabz.smartdriving.verification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.service.CarService;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.clients.VignetteService;
import ro.softwarelabz.smartdriving.clients.impl.ERovinietaImpl;
import ro.softwarelabz.smartdriving.clients.objects.VignetteVerifyRequest;
import ro.softwarelabz.smartdriving.alert.Alert;
import ro.softwarelabz.smartdriving.alert.AlertFactory;
import ro.softwarelabz.smartdriving.alert.AlertService;
import ro.softwarelabz.smartdriving.alert.AlertType;
import ro.softwarelabz.smartdriving.notification.NotificationService;
import ro.softwarelabz.smartdriving.user.domain.User;
import ro.softwarelabz.smartdriving.user.service.UserService;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationService {
    private final CarService carService;
    private final UserService userService;
    private final AlertFactory alertFactory;
    private final VignetteService vignetteService;

    private ERovinietaImpl eRovinieta;


    public void verifyCars() {
        var users = userService.listUsers();

        for (var user : users) {
            var userCars = carService.listCars(user);
            for (var car : userCars) {
                verifyCar(user, car);
            }
        }
    }


//    @Scheduled(fixedDelayString = "${vignette.expiration.reminder.delay}")
//    public void sendVignetteExpirationReminder(Car car, User user) {
//        List<Car> cars = carService.listCars(user);
//        for (Car car1 : cars) {
//            Vignette vignette = auto.getVignette();
//            if (vignette.isExpiring(Duration.ofDays(30))) {
//                User user = auto.getUser();
//                emailService.sendVignetteExpirationReminder(user, vignette);
//            }
//        }


    private void verifyCar(User user, Car car) {
        log.info("Verifying car {}", car.getCarNumber());
        var carDetails = car.getCarDetails();

        if (Objects.nonNull(carDetails)) {
            if (carDetails.isHasVignette()) {
                var hasVignette = vignetteService.hasVignette(VignetteVerifyRequest.builder()
                        .carNumber(car.getCarNumber())
                        .vin(car.getSeries())
                        .build());

                if (!hasVignette) {
                    log.info("Car {} has no vignette", car.getCarNumber());
                    if (StringUtils.isNotBlank(carDetails.getEmail())) {
                        AlertService alertService = alertFactory.getNotificationService(AlertType.EMAIL);
                        alertService.notifyUser(user, Alert.builder()
                                .car(car)
                                .message("Vignette expired")
                                .title("Vignette expired")
                                .email(carDetails.getEmail())
                                .build());
                    }
                    if (StringUtils.isNotBlank(carDetails.getPhoneNumber())) {
                        AlertService alertService = alertFactory.getNotificationService(AlertType.SMS);
                        alertService.notifyUser(user, Alert.builder()
                                .car(car)
                                .message("Vignette expired")
                                .title("Vignette expired")
                                .phoneNumber(carDetails.getPhoneNumber())
                                .build());
                    }
                } else {
                    var notificationDates = List.of(5, 10, 25);
                    for(long notificationDate : notificationDates) {
                        log.info("day {}", notificationDate);
                        sendReminderIfIsNeeded(notificationDate, user, car, carDetails);
                    }
                }
            }


        } else {
            log.info("Car {} has no details to verify.", car.getCarNumber());
        }
    }

    private void sendReminderIfIsNeeded(long days, User user, Car car, CarDetails carDetails) {
        var checkVignette = vignetteService.isExpiredBeforeDays(days, VignetteVerifyRequest.builder()
                .carNumber(car.getCarNumber())
                .vin(car.getSeries())
                .build());

        if (checkVignette) {
            log.info("checking", car.getCarNumber());
            if (StringUtils.isNotBlank(carDetails.getEmail())) {
                AlertService alertService = alertFactory.getNotificationService(AlertType.EMAIL);
                alertService.notifyUser(user, Alert.builder()
                        .car(car)
                        .message("Vignette expire checking in {} days")
                        .title("Vignette expire checking")
                        .email(carDetails.getEmail())
                        .build());
            }
        }
    }
}
