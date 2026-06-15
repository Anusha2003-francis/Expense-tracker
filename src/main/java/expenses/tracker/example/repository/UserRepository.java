package expenses.tracker.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import expenses.tracker.example.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
}
