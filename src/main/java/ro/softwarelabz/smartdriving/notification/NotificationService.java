package ro.softwarelabz.smartdriving.notification;

import ro.softwarelabz.smartdriving.notification.domain.Notification;
import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;

public interface NotificationService {

    Notification save(User user, Notification notification);

    List<Notification> list(User user);

    List<Notification> listByCarId(User user, long carId);
}
