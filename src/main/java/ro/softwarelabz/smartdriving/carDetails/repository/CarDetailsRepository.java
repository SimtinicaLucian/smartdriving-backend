package ro.softwarelabz.smartdriving.carDetails.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface CarDetailsRepository extends JpaRepository<CarDetails, Long> {

    Optional<CarDetails> findByIdAndUserId(long id, long userId);
    List<CarDetails> findByUserId(long userId);

    @Transactional
    void deleteByIdAndUserId(long id, long userId);





}
