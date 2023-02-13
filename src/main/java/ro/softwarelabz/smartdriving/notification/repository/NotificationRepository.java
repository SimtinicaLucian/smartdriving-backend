package ro.softwarelabz.smartdriving.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.notification.domain.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(long userId);
    List<Notification> findAllByUserIdAndCar(long userId, Car car);
}
