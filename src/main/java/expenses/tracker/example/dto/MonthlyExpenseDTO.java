package expenses.tracker.example.dto;

public class MonthlyExpenseDTO {
private int month;
private int year;
private double totalExpense;
public MonthlyExpenseDTO(int month, int year, double totalExpense) {

	this.month = month;
	this.year = year;
	this.totalExpense = totalExpense;
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

}
