package expenses.tracker.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import expenses.tracker.example.dto.*;
//import expenses.tracker.example.repository.UserRepository;
import expenses.tracker.example.security.JwtUtil;
import expenses.tracker.example.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
private AuthenticationManager authenticationmanger;
@Autowired
private JwtUtil jwtutil;
//@Autowired
//private PasswordEncoder passwordencoder;
@Autowired
private UserService userservice;

@PostMapping("/register")
public String register(@RequestBody AuthRequest request) {
	userservice.registerUser(request);
	return "User registetred successfully";
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {

    Authentication authentication = authenticationmanger.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),   
                    request.getPassword() 
            )
    );

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    String token = jwtutil.generateToken(userDetails.getUsername());

    return ResponseEntity.ok(new AuthResponse(token));
}
}
