package com.myorg.mvc.service;

import com.myorg.mvc.exceptions.DoubleRegistrationException;
import com.myorg.mvc.exceptions.UserNotFoundException;
import com.myorg.mvc.model.User;
import com.myorg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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

    public void updateUser(Long userId, String name, String email) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (isValidName(name, user)) {
            user.setName(name);
        }

        if (isValidEmail(email, user)) {
            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new DoubleRegistrationException(user.getEmail());
            }
            user.setEmail(email);
        }
    }

    @Override
    public User createEmptyUser() {
        return new User();
    }

    private boolean isValidEmail(String email, User user) {
        return email != null && !email.trim().isBlank() &&
                !Objects.equals(user.getEmail(), email);
    }

    private boolean isValidName(String name, User user) {
        return name != null && name.length() > 0 && !Objects.equals(user.getName(), name);
    }
}