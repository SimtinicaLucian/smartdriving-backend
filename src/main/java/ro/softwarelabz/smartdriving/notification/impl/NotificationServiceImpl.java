package ro.softwarelabz.smartdriving.notification.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.service.CarService;
import ro.softwarelabz.smartdriving.notification.NotificationService;
import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.notification.repository.NotificationRepository;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final CarService carService;
    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(User user, Notification notification) {
        notification.setUserId(user.getId());
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> list(User user) {
        return notificationRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Notification> listByCarId(User user, long carId) {
        Car car = carService.getCarById(user, carId);
        return notificationRepository.findAllByUserIdAndCar(user.getId(), car);
    }
}
