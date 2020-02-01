package com.unfu.project.controller.v1.authentication;

import com.unfu.project.service.authentication.payload.request.AuthRequest;
import com.unfu.project.service.authentication.payload.response.AuthResponse;
import com.unfu.project.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/social/signin/google")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody AuthRequest request) throws IOException {

        AuthResponse response = authenticationService.authorizeOrRegister(request);
        return ResponseEntity.ok(response);
    }
}
