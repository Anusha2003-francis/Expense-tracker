package expenses.tracker.example.controller;

import java.time.LocalDate;
import java.util.List;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import expenses.tracker.example.dto.CategoryExpenseDTO;
import expenses.tracker.example.dto.ExpenseSummaryDTO;
import expenses.tracker.example.dto.MonthlyExpenseDTO;
import expenses.tracker.example.entity.Expense;
import expenses.tracker.example.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/expenses")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {

@Autowired
private ExpenseService expenseService;
@PreAuthorize("hasRole('USER')")
@PostMapping
public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
	return ResponseEntity.ok(expenseService.addExpenses(expense));
}
@GetMapping("/user/{email}")
public List<Expense> getexpense(@PathVariable String email){

return expenseService.getExpensesByUser(email);
}
@PutMapping("/{id}")
public Expense updateexpense(@PathVariable int id,@RequestBody Expense expense) {
	return expenseService.updateexpense(id, expense);
}
@GetMapping("/user/{email}/category/{category}")
public List<Expense> getexpensebyuserandcategory(@PathVariable String email,@PathVariable String category){

	return expenseService.getExpenseByUserAndCategory(email, category);
}
@GetMapping("/user/{email}/date-range")
public List<Expense> getexpensebyuseranddatebetween(@PathVariable String email,@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
		@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
	return expenseService.getExpenseByUserAndDateBetween(email, startDate, endDate);
	
}
@GetMapping("/user/{email}/total")
public double gettotalExpense(@PathVariable  String email) {

	return expenseService.getTotalExpenses(email);
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/user/{email}/paginated")
public Page<Expense> getByExpenseByUser(@PathVariable String email,@ParameterObject Pageable pageable){
	return expenseService.getByExpenseByUser(email, pageable);
	
}
@GetMapping("/summary/{email}")
public ExpenseSummaryDTO getMonthySummary(@PathVariable String email, @RequestParam int month,@RequestParam int year) {
	return expenseService.getMonthlySummary(email, month,year);
}
@GetMapping("/expensewisecategory")
public List<CategoryExpenseDTO>getcategorywiseExpense(@PathVariable String email){
	return expenseService.getcategorywisereport(email);
}
@GetMapping("/monthlyexpense")
public List<MonthlyExpenseDTO> getMonthlyReport(@RequestParam String email){
	return expenseService.getMonthlyReport(email);
}
@DeleteMapping("/{id}")
public void deleteExpense(@PathVariable int id) {
	expenseService.deleteExpense(id);
}
}
