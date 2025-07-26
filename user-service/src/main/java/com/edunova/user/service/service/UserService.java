package com.edunova.user.service.service;

import com.edunova.commons.data.request.PageableRequest;
import com.edunova.commons.data.response.PageResponse;
import com.edunova.commons.data.mapper.PageMappingService;
import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.UserResponse;
import com.edunova.user.service.entity.ApplicationUser;
import com.edunova.user.service.mapper.UserMapper;
import com.edunova.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        ApplicationUser user = userMapper.toEntity(request);
        ApplicationUser savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public PageResponse<UserResponse> findAll(PageableRequest pageableRequest) {
        Page<ApplicationUser> userPage = userRepository.findAll(pageableRequest.toPageable());
        return PageMappingService.mapToPageResponse(userPage, userMapper::toResponse);
    }

    public UserResponse findById(Long id) {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        ApplicationUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!existingUser.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        userMapper.updateEntity(existingUser, request);
        ApplicationUser savedUser = userRepository.save(existingUser);
        return userMapper.toResponse(savedUser);
    }

    public void delete(Long id) {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}