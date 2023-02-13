package ro.softwarelabz.smartdriving.alert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.alert.email.EmailAlertImpl;
import ro.softwarelabz.smartdriving.alert.sms.SmsAlertImpl;

@Service
@RequiredArgsConstructor
public class AlertFactory {
    private final EmailAlertImpl emailNotification;
    private final SmsAlertImpl smsNotification;

    public AlertService getNotificationService(AlertType type) {
        return switch (type) {
            case EMAIL -> emailNotification;
            case SMS -> smsNotification;
        };
    }
}
