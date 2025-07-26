package com.edunova.user.service.controller;

import com.edunova.commons.data.request.PageableRequest;
import com.edunova.commons.data.response.PageResponse;
import com.edunova.commons.web.api.ResponseCreator;
import com.edunova.commons.web.api.RestResponse;
import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.UserResponse;
import com.edunova.user.service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.create(request);
        return ResponseCreator.created(response, "user.created");
    }

    @GetMapping
    public ResponseEntity<RestResponse<PageResponse<UserResponse>>> findAll(@Valid PageableRequest pageableRequest) {
        PageResponse<UserResponse> response = userService.findAll(pageableRequest);
        return ResponseCreator.ok(response, "user.list.success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> findById(@PathVariable Long id) {
        UserResponse response = userService.findById(id);
        return ResponseCreator.ok(response, "user.found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        UserResponse response = userService.update(id, request);
        return ResponseCreator.ok(response, "user.updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseCreator.noContent("user.deleted");
    }
}