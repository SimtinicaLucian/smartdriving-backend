package ro.softwarelabz.smartdriving.carDetails.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.alert.AlertService;
import ro.softwarelabz.smartdriving.alert.email.EmailAlertImpl;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.service.CarService;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.carDetails.repository.CarDetailsRepository;
import ro.softwarelabz.smartdriving.controller.request.CreateCarDetailsRequest;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarDetailsServiceImpl implements CarDetailsService {
    private final CarDetailsRepository carDetailsRepository;


    @Override
    public CarDetails create(User user, CreateCarDetailsRequest createCarDetailsRequest) {
        return carDetailsRepository.save(CarDetails.builder()
                .email(createCarDetailsRequest.getEmail())
                .phoneNumber(createCarDetailsRequest.getPhoneNumber())
                .hasVignette(createCarDetailsRequest.isHasVignette())
                .hasInsurance(createCarDetailsRequest.isHasInsurance())
                .userId(user.getId())
                .build());


    }

    @Override
    public Optional<CarDetails> get(long id, long userId) {
        return carDetailsRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public List<CarDetails> list(long userId) {
        return carDetailsRepository.findByUserId(userId);
    }

    @Override
    public void delete(long id, long userId) {
        carDetailsRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public CarDetails update(long id, CarDetails req, long userId) {

        Optional<CarDetails> detailsCar = carDetailsRepository.findByIdAndUserId(id, userId);

        if (detailsCar.isEmpty()) {
            throw new RuntimeException("The details doesn't exist");
        }
        CarDetails carDetails = detailsCar.get();
        carDetails.setEmail(req.getEmail());
        carDetails.setPhoneNumber(req.getPhoneNumber());
        carDetails.setHasVignette(req.isHasVignette());
        carDetails.setHasInsurance(req.isHasInsurance());

        return carDetailsRepository.save(carDetails);

    }
}
