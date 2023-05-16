package org.example.dao.Auth;

import org.example.model.response.AuthResponse;

public interface AuthDao {
    AuthResponse studentLogin(String roll);
}
