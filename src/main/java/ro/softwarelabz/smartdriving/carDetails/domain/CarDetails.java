package ro.softwarelabz.smartdriving.carDetails.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.softwarelabz.smartdriving.car.domain.Car;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String phoneNumber;
    private boolean hasVignette;
    private boolean hasInsurance;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carDetails")
    private List<Car> cars;
    private long userId;

}
