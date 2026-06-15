package expenses.tracker.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import expenses.tracker.example.entity.Budget;
import expenses.tracker.example.entity.User;

public interface BudgetRepository extends JpaRepository<Budget,Integer> {
Optional<Budget> findByUser(User user);
}
