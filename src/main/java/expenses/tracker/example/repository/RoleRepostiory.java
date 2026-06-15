package expenses.tracker.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import expenses.tracker.example.entity.Role;

public interface RoleRepostiory extends JpaRepository<Role,Integer> {
Optional<Role> findByName(String name);
}
