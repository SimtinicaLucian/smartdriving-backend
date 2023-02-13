package ro.softwarelabz.smartdriving.carDetails.service;

import jakarta.transaction.Transactional;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.controller.request.CreateCarDetailsRequest;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface CarDetailsService {

    CarDetails create(User user, CreateCarDetailsRequest createCarDetailsRequest);

    Optional<CarDetails> get(long id, long userId);

    List<CarDetails> list(long userId);


    void delete(long id, long userId);

    CarDetails update(long id, CarDetails carDetails, long userId);

}
