package com.example.demo.repositories;

import com.example.demo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    Account findByEmail(String email);

}
