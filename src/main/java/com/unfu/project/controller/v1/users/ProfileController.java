package com.unfu.project.controller.v1.users;

import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.UserService;
import com.unfu.project.service.users.payload.request.EditProfileRequest;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import com.unfu.project.service.users.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('GUEST','STUDENT','TEACHER', 'ADMIN')")
    public ResponseEntity<ProfileResponse> getCurrentUserProfile() {

        ProfileResponse profile = profileService.getCurrentUserProfile();
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/current")
    @PreAuthorize("hasAnyAuthority('GUEST','STUDENT','TEACHER', 'ADMIN')")
    public ResponseEntity<UserResponse> getCurrentUserProfile(@Valid @RequestBody EditProfileRequest request) {

        UserResponse userResponse = userService.editCurrentUser(request);
        return ResponseEntity.ok(userResponse);
    }

}
