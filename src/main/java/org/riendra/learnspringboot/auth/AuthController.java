package org.riendra.learnspringboot.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if(loginRequest.getUsername().equals("admin") &&
        loginRequest.getPassword().equals("password123")){
            return ResponseEntity.ok(jwtUtil.generateToken(loginRequest.getUsername()));
        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
