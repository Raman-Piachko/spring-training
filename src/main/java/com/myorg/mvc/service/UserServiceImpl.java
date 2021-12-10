package com.myorg.mvc.service;

import com.myorg.mvc.entity.User;
import com.myorg.mvc.exceptions.DoubleRegistrationException;
import com.myorg.mvc.exceptions.UserNotFoundException;
import com.myorg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new DoubleRegistrationException(user.getEmail());
        }

        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);

        return userToDelete;
    }

    public User updateUser(Long userId, String firstName, String lastName, String email, String birthday) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        String userEmail = user.getEmail();
        insertValues(user, userEmail, email, firstName, lastName, birthday);
        return user;
    }


    @Override
    public User createEmptyUser() {
        return new User();
    }

    @Override
    public User updateUser(Long userId, User user) {
        User repoUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        String repoUserEmail = repoUser.getEmail();
        String userEmail = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userBirthday = user.getBirthday();

        insertValues(repoUser, repoUserEmail, userEmail, firstName, lastName, userBirthday);
        return user;
    }

    private void insertValues(User repoUser, String repoUserEmail, String userEmail, String firstName, String lastName,
                              String userBirthday) {
        if (isValidName(firstName, lastName)) {
            repoUser.setFirstName(firstName);
            repoUser.setLastName(lastName);
        }

        if (isValidEmail(userEmail, regexPattern, repoUserEmail)) {
            repoUser.setEmail(userEmail);
        } else throw new DoubleRegistrationException(repoUser.getEmail());

        repoUser.setBirthday(userBirthday);
    }

    private boolean isValidEmail(String emailAddress, String regexPattern, String userEmail) {
        return !Objects.equals(userEmail, emailAddress) && Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    private boolean isValidName(String firstName, String lastName) {
        return firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0;
    }
}