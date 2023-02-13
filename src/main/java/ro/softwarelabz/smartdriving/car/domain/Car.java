package ro.softwarelabz.smartdriving.car.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ro.softwarelabz.smartdriving.carDetails.domain.CarDetails;
import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String carNumber;
    @Column(unique = true)
    private String series;
    @Deprecated
    @Column(name ="insurance")
    private boolean insurance;
    @Deprecated
    @Column(name ="vignette")
    private boolean vignette;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carDetails_id")
    private CarDetails carDetails;
}

