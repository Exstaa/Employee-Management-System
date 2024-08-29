package az.hafizrzazade.employeemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employeemanagement_accounts")
public class Account {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private Integer id;	
	private String username;
	private String password;
}
