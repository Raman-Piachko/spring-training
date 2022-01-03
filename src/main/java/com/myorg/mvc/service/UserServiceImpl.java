package com.myorg.mvc.service;

import com.myorg.mvc.entity.User;
import com.myorg.mvc.entity.UserDetailsImpl;
import com.myorg.mvc.exceptions.DoubleRegistrationException;
import com.myorg.mvc.exceptions.UserNotFoundException;
import com.myorg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service("UserService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String REGEX_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User addNewUser(User userToSave) {
        Optional<User> userByEmail = userRepository.findByEmail(userToSave.getEmail());
        if (userByEmail.isPresent()) {
            throw new DoubleRegistrationException(userToSave.getEmail());
        }
        User userToInsert = createEmptyUser();
        userToInsert.setFirstName(userToSave.getFirstName());
        userToInsert.setLastName(userToSave.getLastName());
        userToInsert.setEmail(userToSave.getEmail());
        userToInsert.setBirthday(userToSave.getBirthday());
        String userName = (userToSave.getFirstName() + userToSave.getLastName()).toLowerCase(Locale.ROOT);
        userToInsert.setUsername(userName);
        userToInsert.setPassword(passwordEncoder.encode(userToSave.getPassword()));

        return userRepository.save(userToInsert);
    }

    public User deleteUser(Long userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);

        return userToDelete;
    }

    public User updateUser(Long userId, String firstName, String lastName, String email, String birthday) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

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
        User repoUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        String repoUserEmail = repoUser.getEmail();
        String userEmail = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userBirthday = user.getBirthday();

        insertValues(repoUser, repoUserEmail, userEmail, firstName, lastName, userBirthday);
        return user;
    }

    private void insertValues(User repoUser, String repoUserEmail, String userEmail, String firstName, String lastName, String userBirthday) {
        if (isValidName(firstName, lastName)) {
            repoUser.setFirstName(firstName);
            repoUser.setLastName(lastName);
        }

        if (isValidEmail(userEmail, repoUserEmail)) {
            repoUser.setEmail(userEmail);
        } else {
            throw new DoubleRegistrationException(repoUser.getEmail());
        }

        repoUser.setBirthday(userBirthday);
    }

    private boolean isValidEmail(String emailAddress, String userEmail) {
        return !Objects.equals(userEmail, emailAddress)
                && Pattern.compile(UserServiceImpl.REGEX_PATTERN)
                .matcher(emailAddress)
                .matches();
    }

    private boolean isValidName(String firstName, String lastName) {
        return firstName != null
                && lastName != null
                && !firstName.trim().isEmpty()
                && !lastName.trim().isEmpty();
    }
}