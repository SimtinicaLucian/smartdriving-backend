package ro.softwarelabz.smartdriving.car.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.repository.CarRepository;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.carDetails.service.CarDetailsService;
import ro.softwarelabz.smartdriving.controller.request.CreateCarRequest;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarDetailsService carDetailsService;
    private final CarRepository carRepository;

    @Override
    public Car create(User user, CreateCarRequest request) {
        boolean isCar = carRepository.existsByCarNumber(request.getCarNumber());
        if (isCar) {
            throw new RuntimeException(String.format("The car with number %s already exists", request.getCarNumber()));
        }


        CarDetails carDetails = carDetailsService.get(request.getCarDetailsId(), user.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Car Details %s not found", request.getCarDetailsId())));

        return carRepository.save(Car.builder()
                .carNumber(request.getCarNumber())
                .series(request.getSeries())
                .user(user)
                .carDetails(carDetails)
                .build());
    }

    @Override
    public Car getCarByNumber(String carNumber, User user) {
        return carRepository.findByCarNumberAndUser(carNumber, user);
    }

    @Override
    public Car getCarById(User user, long id) {
        return carRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public List<Car> listCars(User user) {
        return carRepository.findAllByUser(user);
    }

    public Car updateCar(long id, Car req, User user) { // UpdateCarRequest
        Optional<Car> optionalCar = carRepository.findByIdAndUser(id, user);

        if (optionalCar.isEmpty()) {
            throw new RuntimeException("The car doesn't exist");
        }
        Car car = optionalCar.get();
        car.setCarNumber(req.getCarNumber());
        car.setSeries(req.getSeries());


        return carRepository.save(car);
    }

    @Override
    public void delete(long id, User user) {
        carRepository.deleteByIdAndUser(id, user);
    }
}



