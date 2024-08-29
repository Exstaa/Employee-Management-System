package az.hafizrzazade.employeemanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.hafizrzazade.employeemanagement.model.Account;
import az.hafizrzazade.employeemanagement.repository.AccountRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private AccountRepository accountRepo;
	
	@PostMapping({"", "/"})
	public ResponseEntity<Optional<Account>> getAccountByUsername(@RequestBody String username){
		Optional<Account> account = accountRepo.findByUsername(username);
		return new ResponseEntity<Optional<Account>>(account,HttpStatus.OK);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Account> postAccount(@RequestBody Account account){
		account.setId(0);
		accountRepo.save(account);
		return new ResponseEntity<Account>(account,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable Integer id){
		Optional<Account> resultAccount = accountRepo.findById(id);

		
		accountRepo.delete(resultAccount.get());
		
		return new ResponseEntity<Account>(resultAccount.get(), HttpStatus.ACCEPTED);
				
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Account> editAccount(@RequestBody Account account) {
		Account resultAccount = accountRepo.save(account);
		return new ResponseEntity<Account>(resultAccount, HttpStatus.ACCEPTED);
	}
}
