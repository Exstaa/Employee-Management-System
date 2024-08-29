package az.hafizrzazade.employeemanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employeemanagement_employees")
public class Employee {	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    private Integer id;	
	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private Double salary;
	private String status;
	private String hireDate;
}
