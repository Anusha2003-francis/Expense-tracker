package expenses.tracker.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expenses.tracker.example.dto.AuthRequest;
import expenses.tracker.example.entity.User;
import expenses.tracker.example.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
private UserService userService;

@PreAuthorize("hasRole('USER')")
@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(userService.registerUser(request));
}
@PostMapping("/create-admin")
public ResponseEntity<User> createAdmin(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(userService.createAdmin(request));
}
}
