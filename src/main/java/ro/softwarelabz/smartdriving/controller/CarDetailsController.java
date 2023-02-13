package ro.softwarelabz.smartdriving.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.carDetails.service.CarDetailsService;
import ro.softwarelabz.smartdriving.controller.request.CreateCarDetailsRequest;
import ro.softwarelabz.smartdriving.controller.response.Response;
import ro.softwarelabz.smartdriving.controller.response.ListResponse;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/carDetails")
public class CarDetailsController {
    private final CarDetailsService carDetailsService;

    @PostMapping
    public Response<CarDetails> create(@AuthenticationPrincipal User user, @RequestBody CreateCarDetailsRequest createCarDetailsRequest) {
        var carDetails = carDetailsService.create(user, createCarDetailsRequest);
        return Response.one(carDetails);
    }

    @GetMapping("/{id}")
    public Response<CarDetails> get(@AuthenticationPrincipal User user, @PathVariable long id) {
        var carDetails = carDetailsService.get(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Car Details not found"));
        return Response.one(carDetails);
    }

    @GetMapping
    public Response<List<CarDetails>> list(@AuthenticationPrincipal User user) {
        var carDetails = carDetailsService.list(user.getId());
        return Response.multiple(ListResponse.build(carDetails));
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal User user, @PathVariable long id) {
        carDetailsService.delete(id, user.getId());
    }

    @PutMapping("/{id}")
    public Response<CarDetails> updateCarDetails(@AuthenticationPrincipal User user, @PathVariable long id, @RequestBody CarDetails carDetails) {
        var updatedCarDetails = carDetailsService.update(id, carDetails, user.getId());
        return Response.one(updatedCarDetails);
    }
}

