package az.hafizrzazade.employeemanagement.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.hafizrzazade.employeemanagement.model.Employee;
import az.hafizrzazade.employeemanagement.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<List<Employee>>(employeeRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee){
		employee.setId(0);
		employeeRepo.save(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id){
		Optional<Employee> resultEmployee = employeeRepo.findById(id);

		
		employeeRepo.delete(resultEmployee.get());
		
		return new ResponseEntity<Employee>(resultEmployee.get(), HttpStatus.ACCEPTED);
				
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Employee> editEmployee(@RequestBody Employee employee) {
		Employee resultEmployee = employeeRepo.save(employee);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.ACCEPTED);
	}
}
