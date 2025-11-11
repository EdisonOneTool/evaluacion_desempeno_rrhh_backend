package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.entities.UserInfo;
import com.edisonla.evaluacion_desempeno.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<UserInfo> userInfo = repository.findByEmail(email);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        UserInfo user = userInfo.get();
        return new User(user.getEmail(), user.getPassword(), user.getRoles());
    }

    // Add any additional methods for registering or managing users
    public String addUser(UserInfo userInfo) {
        // Encrypt password before saving
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added successfully!";
    }
}