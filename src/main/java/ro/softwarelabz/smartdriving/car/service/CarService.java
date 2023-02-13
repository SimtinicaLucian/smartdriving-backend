package ro.softwarelabz.smartdriving.car.service;

import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.controller.request.CreateCarRequest;
import ro.softwarelabz.smartdriving.user.domain.User;


import java.util.List;
import java.util.Optional;

public interface CarService {
    Car create(User user, CreateCarRequest request);
    Car getCarByNumber(String carNumber, User user);
    Car getCarById(User user, long id);
    List<Car> listCars(User user);
//    List<Car> listCarsByUser(User user);
    Car updateCar(long id, Car car, User user);
    void delete (long id, User user);
}
