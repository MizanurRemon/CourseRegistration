package org.example.services.Auth;


import jakarta.transaction.Transactional;
import org.example.dao.Auth.AuthDao;
import org.example.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthDao dao;
    @Override
    public AuthResponse studentLogin(String roll) {
        return dao.studentLogin(roll);
    }
}
