package com.edunova.user.service.controller;

import com.edunova.commons.data.request.PageableRequest;
import com.edunova.commons.data.response.PageResponse;
import com.edunova.commons.web.api.ResponseCreator;
import com.edunova.commons.web.api.RestResponse;
import com.edunova.user.service.data.request.CreateUserRequest;
import com.edunova.user.service.data.request.UpdateUserRequest;
import com.edunova.user.service.data.response.ApplicationUserResponse;
import com.edunova.user.service.service.ApplicationUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final ApplicationUserService applicationUserService;

    @PostMapping
    public ResponseEntity<RestResponse<ApplicationUserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
        ApplicationUserResponse response = applicationUserService.create(request);
        return ResponseCreator.created(response, "user.created");
    }

    @GetMapping
    public ResponseEntity<RestResponse<PageResponse<ApplicationUserResponse>>> findAll(@Valid PageableRequest pageableRequest) {
        PageResponse<ApplicationUserResponse> response = applicationUserService.findAll(pageableRequest);
        return ResponseCreator.ok(response, "user.list.success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ApplicationUserResponse>> findById(@PathVariable Long id) {
        ApplicationUserResponse response = applicationUserService.findById(id);
        return ResponseCreator.ok(response, "user.found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<ApplicationUserResponse>> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        ApplicationUserResponse response = applicationUserService.update(id, request);
        return ResponseCreator.ok(response, "user.updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> delete(@PathVariable Long id) {
        applicationUserService.delete(id);
        return ResponseCreator.noContent("user.deleted");
    }
}