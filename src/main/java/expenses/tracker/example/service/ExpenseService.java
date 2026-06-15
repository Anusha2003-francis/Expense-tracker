package expenses.tracker.example.service;
import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import expenses.tracker.example.dto.CategoryExpenseDTO;
import expenses.tracker.example.dto.ExpenseSummaryDTO;
import expenses.tracker.example.dto.MonthlyExpenseDTO;
import expenses.tracker.example.entity.*;
import expenses.tracker.example.repository.BudgetRepository;
import expenses.tracker.example.repository.ExpenseRepository;
import expenses.tracker.example.repository.UserRepository;

@Service
public class ExpenseService {
@Autowired
private ExpenseRepository expenseRepo;
@Autowired
private UserRepository userRepo;
@Autowired
private BudgetRepository budgetRepo;
public Expense addExpenses(Expense expense) {
	return expenseRepo.save(expense);
}

public List<Expense> getExpensesByUser(String eamil ){
	User user=userRepo.findByEmail(eamil).orElseThrow(()-> new RuntimeException("User not found with this email"+eamil));
	return expenseRepo.findByUser(user);	
}

public Expense updateexpense(int id,Expense updateExpense) {
	Expense expense=expenseRepo.findById(id).orElseThrow(()->new RuntimeException("Expense Not Found"));
	expense.setAmount(updateExpense.getAmount());
	expense.setCategory(updateExpense.getCategory());
	expense.setDate(updateExpense.getDate());
	expense.setDescription(updateExpense.getDescription());
	return expenseRepo.save(expense);
	
}
public List<Expense> getExpenseByUserAndCategory(String email,String Category){
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found with this email"+email));
	return expenseRepo.findByUserAndCategory(user, Category);
}
public List<Expense> getExpenseByUserAndDateBetween(String email,LocalDate startDate,LocalDate endDate){
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found with this email"+email));
	return expenseRepo.findByUserAndDateBetween(user, startDate, endDate);
}
public double getTotalExpenses(String email) {
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found with this email"+email));
	return expenseRepo.findByUser(user).stream().mapToDouble(Expense::getAmount).sum();
}
public Page<Expense> getByExpenseByUser(String email,Pageable pageable){
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"+email));
	return expenseRepo.findByUser(user, pageable);
}
public ExpenseSummaryDTO getMonthlySummary(String email,int month,int year) {
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("user not found"+email));
	double totalExpense=expenseRepo.getTotalExpenseByMonth(user, month, year);
	double monthlyBudget=budgetRepo.findByUser(user).map(budget->budget.getMonthlylimit()).orElse(0.0);
	double remaingBudget=monthlyBudget-totalExpense;
	String status=totalExpense>monthlyBudget?"Budget exceeded":"within Budget";
	return new ExpenseSummaryDTO(email,month,year,totalExpense,monthlyBudget,remaingBudget,status);
	
	
}
public List<CategoryExpenseDTO> getcategorywisereport(String email) {
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("user not found with this email"+email));
	return expenseRepo.getcategorywiseExpense(user);
}
public List<MonthlyExpenseDTO> getMonthlyReport(String email){
	User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("user not found with email"+email));
	return expenseRepo.getMonthlyExpenses(user);
}
public void deleteExpense(int id) {
	expenseRepo.deleteById(id);
}
}
