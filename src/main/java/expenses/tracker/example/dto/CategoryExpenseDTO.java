package expenses.tracker.example.dto;

public class CategoryExpenseDTO {
private String category;
private double totalamount;
public CategoryExpenseDTO(String category, double totalamount) {
	super();
	this.category = category;
	this.totalamount = totalamount;
}
public String getCategory() {
	return category;
}
public double getTotalamount() {
	return totalamount;
}

}
