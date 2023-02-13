package ro.softwarelabz.smartdriving.alert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.softwarelabz.smartdriving.car.domain.Car;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Alert {
    private String title;
    private String message;
    private Car car;
    private String email;
    private String phoneNumber;
}

