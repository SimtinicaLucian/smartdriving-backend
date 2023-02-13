package ro.softwarelabz.smartdriving.car.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.user.domain.User;


import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarNumberAndUser(String carNumber, User user);
    Optional<Car> findByIdAndUser(long id, User user);
    Optional<Car> findByInsuranceIsTrueAndUser(User user);
    Optional<Car> findByVignetteIsTrueAndUser(User user);
    List<Car> findByUser(User user);
    List<Car> findAllByUser(User user);
    boolean existsByCarNumber(String carNumber);
    @Transactional
    void deleteByIdAndUser(long id, User user);
}
