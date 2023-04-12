package com.quizgpt.accountservice.user;

import com.quizgpt.accountservice.role.ERole;
import com.quizgpt.accountservice.role.Role;
import com.quizgpt.accountservice.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User createUser(User userRequest) {
        // Check if the user already exists in the database
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userRequest.getEmail() + " already exists.");
        }
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + userRequest.getUsername() + " already exists.");
        }
        // Create new user's account
        User user = new User(userRequest.getUsername(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()));

        // Everyone is an admin.  Multiple roles is messy and too complex to handle
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "Not Found"));
        user.setUsername(newUser.getUsername());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "Not Found"));
        userRepository.delete(user);
    }

    @Override
    public User getUserByID(Long id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new InvalidConfigurationPropertyValueException("id", id, "Not Found");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new InvalidConfigurationPropertyValueException("email", email, "Not Found");
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> result = userRepository.findByEmail(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new InvalidConfigurationPropertyValueException("username", username, "Not Found");
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
