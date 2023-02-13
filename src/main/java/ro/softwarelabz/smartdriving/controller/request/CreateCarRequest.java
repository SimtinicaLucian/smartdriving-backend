package ro.softwarelabz.smartdriving.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private String carNumber;
    private String series;
    private long carDetailsId;
}
