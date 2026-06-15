package expenses.tracker.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import expenses.tracker.example.entity.Budget;
import expenses.tracker.example.entity.User;
import expenses.tracker.example.service.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {
@Autowired
private UserService userService;
@Autowired 
private BudgetService budgetService;

@PostMapping("/{email}")
public Budget setBudget(@PathVariable String email,@RequestBody Budget budget) {
	User user=userService.getUserByEmail(email);
	budget.setUser(user);
	return budgetService.saveBudget(budget);
}
@GetMapping("/{email}")
public Optional<Budget> getBudget(@PathVariable String email){
	User user=userService.getUserByEmail(email);
	return budgetService.getBudgetByUser(user);
}

}
