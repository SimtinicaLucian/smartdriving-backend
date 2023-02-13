package ro.softwarelabz.smartdriving.clients.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class VignetteVerifyRequest {
    private String carNumber;
    private String vin;
}
