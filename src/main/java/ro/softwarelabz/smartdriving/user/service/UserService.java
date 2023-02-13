package ro.softwarelabz.smartdriving.user.service;

import ro.softwarelabz.smartdriving.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    Optional<User> getByEmail(String email);
    List<User> listUsers();
    Optional<User> getUserById(long id);
    Optional<User> getUserBySub(String sub);
    User updateUser(long id, User user);
    void delete (long id);

}
