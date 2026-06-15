package expenses.tracker.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import expenses.tracker.example.dto.AuthRequest;
import expenses.tracker.example.entity.Role;
import expenses.tracker.example.entity.User;
import expenses.tracker.example.repository.RoleRepostiory;
import expenses.tracker.example.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepostiory roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUserByEmail(String email) {
	    return userRepo.findByEmail(email)
	            .orElseThrow(() ->
	                    new RuntimeException("User not found with email: " + email));
	}
	
	public User registerUser(AuthRequest request) {

	    Role userRole = roleRepo.findByName("ROLE_USER")
	            .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

	    User user = new User();
	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
         user.setRoles(Set.of(userRole));

	    return userRepo.save(user);
	}

    public User createAdmin(AuthRequest request) {

        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(adminRole));

        return userRepo.save(user);
    }
}