package com.example.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackFor = SQLException.class)
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    
    public Account addAccount(Account account){
        Account user = accountRepository.findAccountByUsername(account.getUsername());
        if (account.getUsername().isBlank() || account.getPassword().length() < 4){
            return null;
        }
        if(user == null){
            return accountRepository.save(account);
        }
        return null;
    }

    public Account loginAccount(Account account){
        Account user = accountRepository.findAccountByUsername(account.getUsername());
        if (user != null){
            if (user.getPassword().equals(account.getPassword())){
                return user;
            }
        }
        return null;
    }
}
