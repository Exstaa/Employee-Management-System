package az.hafizrzazade.employeemanagement.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.hafizrzazade.employeemanagement.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    public Optional<Account> findByUsername(String username);

}
