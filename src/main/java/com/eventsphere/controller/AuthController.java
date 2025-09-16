package com.eventsphere.controller;

import com.eventsphere.model.Role;
import com.eventsphere.model.User;
import com.eventsphere.repository.UserRepository;
import com.eventsphere.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    record RegisterReq(String username, String email, String password) {}
    record LoginReq(String username, String password) {}
    record AuthResp(String token) {}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq rq){
        if(userRepository.existsByUsername(rq.username())) return ResponseEntity.badRequest().body("Username exists");
        if(userRepository.existsByEmail(rq.email())) return ResponseEntity.badRequest().body("Email exists");

        var user = User.builder()
                .username(rq.username())
                .email(rq.email())
                .password(passwordEncoder.encode(rq.password()))
                .roles(Set.of(Role.ROLE_USER))
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername(), Set.of("ROLE_USER"));
        return ResponseEntity.ok(new AuthResp(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq rq){
        var uOpt = userRepository.findByUsername(rq.username());
        if(uOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid credentials");
        var user = uOpt.get();
        if(!passwordEncoder.matches(rq.password(), user.getPassword())) return ResponseEntity.status(401).body("Invalid credentials");
        String token = jwtUtil.generateToken(user.getUsername(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        return ResponseEntity.ok(new AuthResp(token));
    }
}
