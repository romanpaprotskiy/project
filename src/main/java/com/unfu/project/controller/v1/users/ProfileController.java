package com.unfu.project.controller.v1.users;

import com.unfu.project.service.users.ProfileService;
import com.unfu.project.service.users.payload.response.ProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('GUEST','STUDENT','TEACHER', 'ADMIN')")
    public ResponseEntity<ProfileResponse> getCurrentUserProfile() {

        ProfileResponse profile = profileService.getCurrentUserProfile();
        return ResponseEntity.ok(profile);
    }

}
