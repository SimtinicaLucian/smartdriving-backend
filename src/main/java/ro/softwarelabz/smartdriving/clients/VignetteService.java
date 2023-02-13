package ro.softwarelabz.smartdriving.clients;

import ro.softwarelabz.smartdriving.clients.objects.ERovinietaResponse;
import ro.softwarelabz.smartdriving.clients.objects.VignetteVerifyRequest;

import java.time.Duration;
import java.time.LocalDate;

public interface VignetteService {
    boolean hasVignette(VignetteVerifyRequest request);


    boolean isExpiredBeforeDays(long daysBeforeExpiration, VignetteVerifyRequest request);


}
