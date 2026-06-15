package expenses.tracker.example.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import expenses.tracker.example.entity.User;
import expenses.tracker.example.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
@Autowired
private UserRepository userRepo;
@Override	
public UserDetails loadUserByUsername(String email) 
		                             throws UsernameNotFoundException{
		User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
		List<SimpleGrantedAuthority> authorities =
			    user.getRoles().stream()
			    .map(role -> new SimpleGrantedAuthority(role.getName()))
			        .toList();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
	}
}
