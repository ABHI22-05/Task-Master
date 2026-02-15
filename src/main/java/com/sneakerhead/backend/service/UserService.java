package com.sneakerhead.backend.service;

import com.sneakerhead.backend.dto.response.UserResponse;
import com.sneakerhead.backend.entity.User;
import com.sneakerhead.backend.exception.ResourceNotFoundException;
import com.sneakerhead.backend.repository.UserRepository;
import com.sneakerhead.backend.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    @Transactional(readOnly = true)
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return entityMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return entityMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(entityMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse updateProfile(UserResponse userResponse) {
        User currentUser = getCurrentUserEntity();

        if (userResponse.getFullName() != null) {
            currentUser.setFullName(userResponse.getFullName());
        }
        if (userResponse.getPhone() != null) {
            currentUser.setPhone(userResponse.getPhone());
        }
        if (userResponse.getProfilePicture() != null) {
            currentUser.setProfilePicture(userResponse.getProfilePicture());
        }

        User updatedUser = userRepository.save(currentUser);
        return entityMapper.toUserResponse(updatedUser);
    }
}
