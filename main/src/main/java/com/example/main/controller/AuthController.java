package com.example.main.controller;

import com.example.main.builder.AuthenticationResponse;
import com.example.main.exeption.AccountNotFoundException;
import com.example.main.model.Accounts;
import com.example.main.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class AuthController {
    @Autowired
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Sign up
    @PostMapping("/accounts/signup")
    //Perform create a new account -> Gen new id
    //We need a handle token as well
    public ResponseEntity<AuthenticationResponse> createAccount(@RequestBody Accounts account) throws Exception {
        return ResponseEntity.ok(
                accountService.createAccount(account)
        );
    }
    //SIGN IN
    @PostMapping("/accounts/signin")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Accounts account) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.authenticate(account));
    }
}
