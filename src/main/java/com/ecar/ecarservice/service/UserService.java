package com.ecar.ecarservice.service;

import com.ecar.ecarservice.enitiies.AppUser;
import com.ecar.ecarservice.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AppUserRepository userRepository;

    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Lấy danh sách tất cả user
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Lấy user theo id
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 3. Update user
    public AppUser updateUser(Long id, AppUser userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(userDetails.getEmail());   // update email
            user.setRoles(userDetails.getRoles());   // update roles
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // 4. Xóa user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
