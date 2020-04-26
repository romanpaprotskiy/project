package com.unfu.project.service.users;

import com.unfu.project.service.users.payload.response.ProfileResponse;

import java.io.IOException;

public interface ProfileService {

    ProfileResponse getCurrentUserProfile() throws IOException;
}
