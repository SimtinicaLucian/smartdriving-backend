package ro.softwarelabz.smartdriving.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateCarDetailsRequest {
    private String email;
    private String phoneNumber;
    private boolean hasVignette;
    private boolean hasInsurance;
    private List<Long> carsIds;
}
