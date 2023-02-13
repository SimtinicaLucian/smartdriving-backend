package ro.softwarelabz.smartdriving.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.service.CarService;
import ro.softwarelabz.smartdriving.controller.request.CreateCarRequest;
import ro.softwarelabz.smartdriving.controller.response.Response;
import ro.softwarelabz.smartdriving.controller.response.ListResponse;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/car")
public class CarController {
    private final CarService carService;

    @PostMapping
    public Response<Car> car(@AuthenticationPrincipal User user, @RequestBody CreateCarRequest createCarRequest) {
        var car = carService.create(user, createCarRequest);
        return Response.one(car);
    }

    @GetMapping
    public Response<List<Car>> listCars(@AuthenticationPrincipal User user) {
        var cars = carService.listCars(user);
        return Response.multiple(ListResponse.build(cars));
    }

    @GetMapping("/{id}")
    Response<Car> getById(@AuthenticationPrincipal User user, @PathVariable long id) {
        var car = carService.getCarById(user, id);
        return Response.one(car);
    }

    @PutMapping("/{id}")
    public Response<Car> updateCar(@AuthenticationPrincipal User user, @PathVariable long id, @RequestBody Car car) {
        var updateCar = carService.updateCar(id, car, user);
        return Response.one(updateCar);
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal User user, @PathVariable long id) {
        carService.delete(id, user);
    }
}
