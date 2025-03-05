package com.rejj.ecommerce.service;

import com.rejj.ecommerce.dto.UpdateUserRequest;
import com.rejj.ecommerce.model.Client;
import com.rejj.ecommerce.model.User;
import com.rejj.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UpdateUserRequest request) {
        User user = getUserById(id);

        // Validate username uniqueness if changed
        if (!user.getUsername().equals(request.getUsername()) &&
            userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Validate email uniqueness if changed
        if (!user.getEmail().equals(request.getEmail()) &&
            userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Update basic info
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Update password if provided
        if (request.getCurrentPassword() != null && request.getNewPassword() != null) {
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new RuntimeException("Current password is incorrect");
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        // Update address if user is a client
        if (user instanceof Client && request.getAddress() != null) {
            ((Client) user).setAddress(request.getAddress());
        }

        return userRepository.save(user);
    }

    public void blockUser(Long id) {
        User user = getUserById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    public void unblockUser(Long id) {
        User user = getUserById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
} 