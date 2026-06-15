package expenses.tracker.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="bugets")
public class Budget {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name="montly_limit")
private double monthlylimit;
@ManyToOne
@JoinColumn(name="user_id")
@JsonIgnore
private User user;
public Budget() {}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getMonthlylimit() {
	return monthlylimit;
}
public void setMonthlylimit(double monthlylimit) {
	this.monthlylimit = monthlylimit;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

}
