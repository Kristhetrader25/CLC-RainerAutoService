package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.models.LoginPrincipal;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDataService implements AuthService 
{

    private final UserRepository userRepo;

    public UserDataService(UserRepository userRepo) 
    {
        this.userRepo = userRepo;
    }

    @Override
    public LoginPrincipal authenticate(String username, String password) 
    {
        UserEntity user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) 
        {
            return new LoginPrincipal(
                user.getUsername(),
                user.getDisplayName(),
                user.getRole()
            );
        }
        return null;
    }

    @Override
    public void logout(jakarta.servlet.http.HttpSession session) 
    {
        session.invalidate();
    }
}
