package ro.softwarelabz.smartdriving.alert.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.alert.Alert;
import ro.softwarelabz.smartdriving.alert.AlertService;
import ro.softwarelabz.smartdriving.notification.NotificationService;
import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.notification.domain.NotificationType;
import ro.softwarelabz.smartdriving.user.domain.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsAlertImpl implements AlertService {
    @Value("${sms.twilio.account-sid}")
    private String ACCOUNT_SID;
    @Value("${sms.twilio.auth-token}")
    private String AUTH_TOKEN;

    @Value("${sms.twilio.phone-number}")
    private String phoneNumber;
    private final NotificationService notificationService;

    @Override
    public void notifyUser(User user, Alert alert) {
        sendSms(alert.getEmail(), alert.getMessage());
        notificationService.save(user, Notification.builder()
                .message(alert.getMessage())
                .type(NotificationType.SMS)
                .car(alert.getCar())
                .userId(user.getId())
                .build());
    }

    public void sendSms(String to, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(to), // to
                        new PhoneNumber(phoneNumber), // from
                        body)
                .create();
        System.out.println(message.getSid());


        var status = message.getStatus();
        switch (status) {
            case SENT -> log.info("The message was sent");
            case SENDING -> log.info("The message is sending");
            case FAILED -> log.error("The message wasn't sent. Reason {}", message.getErrorMessage());
            default -> log.info("Sms status: {}", message.getStatus());
        }
    }
}

