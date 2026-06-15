package expenses.tracker.example.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="users")
public class User {
@Id	
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@NotBlank(message="Name is required")
private String name;

@Email(message="email need to be valid")
@NotBlank(message="email is required ")
private String email;

@NotBlank(message="password is required")
private String password;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles=new HashSet<>();

//one user can have many expenses
@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<Expense> expense;

//one user but many budget
@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<Budget> budgest;
public User() {}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Set<Role> getRoles() {
	return roles;
}
public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

}


