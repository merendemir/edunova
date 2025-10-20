package com.edunova.user.service.service;

import com.edunova.commons.data.request.PageableRequest;
import com.edunova.commons.data.response.PageResponse;
import com.edunova.commons.data.mapper.PageMappingService;
import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.ApplicationUserResponse;
import com.edunova.user.service.entity.ApplicationUser;
import com.edunova.user.service.mapper.ApplicationUserMapper;
import com.edunova.user.service.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserMapper applicationUserMapper;

    public ApplicationUserResponse create(CreateUserRequest request) {
        if (applicationUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        ApplicationUser user = applicationUserMapper.toEntity(request);
        ApplicationUser savedUser = applicationUserRepository.save(user);
        return applicationUserMapper.toResponse(savedUser);
    }

    public PageResponse<ApplicationUserResponse> findAll(PageableRequest pageableRequest) {
        Page<ApplicationUser> userPage = applicationUserRepository.findAll(pageableRequest.toPageable());
        return PageMappingService.mapToPageResponse(userPage, applicationUserMapper::toResponse);
    }

    public ApplicationUserResponse findById(Long id) {
        ApplicationUser user = applicationUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationUserMapper.toResponse(user);
    }

    public ApplicationUserResponse update(Long id, UpdateUserRequest request) {
        ApplicationUser existingUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!existingUser.getEmail().equals(request.getEmail()) && 
            applicationUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        applicationUserMapper.updateEntity(existingUser, request);
        ApplicationUser savedUser = applicationUserRepository.save(existingUser);
        return applicationUserMapper.toResponse(savedUser);
    }

    public void delete(Long id) {
        ApplicationUser user = applicationUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        applicationUserRepository.delete(user);
    }
}