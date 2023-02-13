package ro.softwarelabz.smartdriving.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import ro.softwarelabz.smartdriving.car.domain.Car;

import java.util.Date;

@Data
@Entity
@Table(name = "notifications")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private NotificationType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    private long userId;
    private Date createdAt;
}
