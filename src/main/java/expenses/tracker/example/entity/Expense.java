package expenses.tracker.example.entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name="expenses")
public class Expense {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private double amount;
private String category;
@Schema(example = "2025-04-10")
@JsonFormat(pattern = "yyyy-MM-dd")
private LocalDate date;
private String description;
//many expenses one user
@ManyToOne
@JoinColumn(name="user_id")
@JsonIgnore
private User user;
public Expense() {}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

}
