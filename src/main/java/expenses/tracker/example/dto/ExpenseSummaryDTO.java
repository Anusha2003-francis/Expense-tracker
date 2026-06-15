package expenses.tracker.example.dto;

public class ExpenseSummaryDTO {

private String email;
private int month;
private int year;
private double totalExpense;
private double monthlyBudget;
private double remaingBudget;
private String status;
public ExpenseSummaryDTO(String email, int month, int year, double totalExpense, double monthlyBudget,
		double remaingBudget, String status) {
	super();
	this.email = email;
	this.month = month;
	this.year = year;
	this.totalExpense = totalExpense;
	this.monthlyBudget = monthlyBudget;
	this.remaingBudget = remaingBudget;
	this.status = status;
}
public String getEmail() {
	return email;
}

public int getMonth() {
	return month;
}

public int getYear() {
	return year;
}

public double getTotalExpense() {
	return totalExpense;
}

public double getMonthlyBudget() {
	return monthlyBudget;
}

public double getRemaingBudget() {
	return remaingBudget;
}

public String getStatus() {
	return status;
}



}
