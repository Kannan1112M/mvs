package com.mvs.service;

import com.mvs.dto.ChangePasswordReq;
import com.mvs.dto.LoginRequest;
import com.mvs.dto.UserReqDto;
import com.mvs.user.entity.User;
import com.mvs.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<User> login(@Valid LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("user not found")
        );

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new EntityNotFoundException("user not found");
        }

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> create(UserReqDto req) {

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        user.setRawPassword(req.getPassword());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok("User Saved Successfully");
    }


    public ResponseEntity<String> forgotPassword(LoginRequest req) {

        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("User Not Found")
        );

        user.setRawPassword(req.getPassword());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("Password Changed Successfully");
    }

    public ResponseEntity<String> changePassword(@Valid ChangePasswordReq request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("User Not Found")
        );

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new EntityNotFoundException("User Not Found");
        }

        user.setRawPassword(request.getNewPassword());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("Password Changed Successfully");
    }

    public ResponseEntity<List<String>> getRoles() {
        return ResponseEntity.ok(userRepository.getRoles());
    }
}
