package org.example.services.Auth;

import org.example.model.response.AuthResponse;

public interface AuthService {
    AuthResponse studentLogin(String roll);
}
