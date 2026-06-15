package expenses.tracker.example.entity;

import jakarta.persistence.*;

@Entity
public class Role {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
