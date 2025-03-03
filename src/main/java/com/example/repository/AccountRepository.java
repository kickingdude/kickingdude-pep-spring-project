package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    /**
     * 
     * @param username
     * @return
     */
    Account findAccountByUsername(String username);

    /**
     * 
     * @param accountId
     * @return
     */
    Account findAccountByAccountId(Integer accountId);
}
