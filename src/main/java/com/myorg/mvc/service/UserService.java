package com.myorg.mvc.service;

import com.myorg.mvc.exceptions.UserNotFoundException;
import com.myorg.mvc.model.User;
import com.myorg.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long ID) {
        return userRepository.getById(ID);
    }


    public User addNewUser(User user) {
        userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        userRepository.save(user);
        return user;
    }

    public User deleteUser(Long userID) {
        userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        User userToDelete = userRepository.findById(userID).get();

        userRepository.deleteById(userID);
        return userToDelete;
    }

    @Transactional
    public void updateUser(Long userID, String name, String email) {

        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));

        if (isValidName(name, user)) {
            user.setName(name);
        }

        if (isValidEmail(email, user)) {

            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("User with this email is registered");
            }
            user.setEmail(email);
        }
    }

    private boolean isValidEmail(String email, User user) {
        return email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email);
    }

    private boolean isValidName(String name, User user) {
        return name != null && name.length() > 0 && !Objects.equals(user.getName(), name);
    }

}
