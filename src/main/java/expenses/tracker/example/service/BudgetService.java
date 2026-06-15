package expenses.tracker.example.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import expenses.tracker.example.entity.Budget;
import expenses.tracker.example.entity.User;
import expenses.tracker.example.repository.BudgetRepository;


@Service
public class BudgetService {
@Autowired
private BudgetRepository budgetRepo;

public Budget saveBudget(Budget budget) {
	return budgetRepo.save(budget);
}
	
public Optional<Budget> getBudgetByUser(User user){
	return budgetRepo.findByUser(user);
}
}