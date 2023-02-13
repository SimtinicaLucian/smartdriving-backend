package ro.softwarelabz.smartdriving.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.user.domain.User;
import ro.softwarelabz.smartdriving.user.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserBySub(String sub) {
        return userRepository.findBySub(sub);
    }

    @Override
    public User updateUser(long id, User req) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("The user doesn't exist");
        }
        User user = optionalUser.get();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());

        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        log.info("The {} has been deleted to the database",id);
        userRepository.deleteById(id);
    }
}
