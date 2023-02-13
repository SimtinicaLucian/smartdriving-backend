package ro.softwarelabz.smartdriving.alert;

import ro.softwarelabz.smartdriving.user.domain.User;

public interface AlertService {
    void notifyUser(User user, Alert alert);
}
