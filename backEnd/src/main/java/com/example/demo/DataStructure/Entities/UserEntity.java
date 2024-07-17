package com.example.demo.DataStructure.Entities;

import com.example.demo.DataStructure.Entities.SubClasses.MyUserDetailsService;
import com.example.demo.DataStructure.Repositories.UserModelRepository;
import com.example.demo.DataStructure.UserModel;
import com.example.demo.Services.EncryptionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEntity implements UserDetailsService {

    UserModelRepository userModelRepository;
    EncryptionUtil encryptionUtil;

    public UserModel addUser(UserModel userModel) {
        return userModelRepository.save(userModel);
    }

    public boolean isUserExist(UserModel userModel) {
        Optional<UserModel> userOp = userModelRepository.findByEmail(userModel.getEmail());
        return userOp.isPresent();
    }

    public UserModel getUserByEmail(String email) {
        return userModelRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userModelRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetailsService(user.get());
    }

    public String getUserEmail(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
        }
        return null;
    }
}
