package com.healthcare.user.service;

import com.healthcare.user.exception.DuplicateEmailException;
import com.healthcare.user.exception.UserNotFoundException;
import com.healthcare.user.model.User;
import com.healthcare.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new DuplicateEmailException(user.getEmail());
        });
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User patch) {
        User existing = getById(id);
        if (patch.getName() != null) {
            existing.setName(patch.getName());
        }
        if (patch.getEmail() != null && !patch.getEmail().equals(existing.getEmail())) {
            userRepository.findByEmail(patch.getEmail()).ifPresent(u -> {
                throw new DuplicateEmailException(patch.getEmail());
            });
            existing.setEmail(patch.getEmail());
        }
        return userRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
