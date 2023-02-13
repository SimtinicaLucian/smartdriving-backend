package ro.softwarelabz.smartdriving.verification.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.car.domain.Car;
import ro.softwarelabz.smartdriving.car.service.CarService;
import ro.softwarelabz.smartdriving.user.domain.User;
import ro.softwarelabz.smartdriving.verification.service.VerificationService;

import java.util.List;

/**
 * VerificationJobs calls the verification task at a certain time.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationJobs {
    private final VerificationService verificationService;


    @Scheduled(cron = "1 * * * * *")
    private void verificationTask() {
        log.info("Verification Job has called.");
        verificationService.verifyCars();
    }
}





