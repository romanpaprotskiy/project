package com.unfu.project.controller;

import com.unfu.project.payload.request.AuthRequest;
import com.unfu.project.payload.response.AuthResponse;
import com.unfu.project.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/social/signin/google")
    public ResponseEntity signIn(@RequestBody AuthRequest request) {

        AuthResponse response = authenticationService.authorizeOrRegister(request);
        return ResponseEntity.ok(response);
    }
}
