package ro.softwarelabz.smartdriving.alert.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.alert.Alert;
import ro.softwarelabz.smartdriving.alert.AlertService;
import ro.softwarelabz.smartdriving.alert.email.objects.Mail;
import ro.softwarelabz.smartdriving.notification.NotificationService;
import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.notification.domain.NotificationType;
import ro.softwarelabz.smartdriving.user.domain.User;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmailAlertImpl implements AlertService {
    @Value("${app.email}")
    private String sender;
    private final JavaMailSender mailSender;
    private final NotificationService notificationService;


    @Override
    public void notifyUser(User user, Alert alert) {
        log.info("Sending email to {}", alert.getEmail());
        Mail mail = Mail.builder()
                .mailFrom(sender)
                .mailTo(alert.getEmail())
                .mailSubject(alert.getTitle())
                .mailContent(alert.getMessage())
                .build();
        sendMail(mail);
        notificationService.save(user, Notification.builder()
                .userId(user.getId())
                .type(NotificationType.EMAIL)
                .message(alert.getMessage())
                .car(alert.getCar())
                .build());
    }

    private void sendMail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
