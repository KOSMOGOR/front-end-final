package com.example.demo.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encrypt(String rawString) {
        return passwordEncoder.encode(rawString);
    }

    public boolean matches(String rawString, String encodedString) {
        return passwordEncoder.matches(rawString, encodedString);
    }
}
