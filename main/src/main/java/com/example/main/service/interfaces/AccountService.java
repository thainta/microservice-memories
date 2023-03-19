package com.example.main.service.interfaces;

import com.example.main.builder.AuthenticationResponse;
import com.example.main.exeption.AccountNotFoundException;
import com.example.main.model.Accounts;

import java.util.List;

public interface AccountService {
    AuthenticationResponse createAccount(Accounts account) throws Exception; //Done
    AuthenticationResponse createAdminAccount(Accounts account) throws Exception;
    AuthenticationResponse authenticate(Accounts account) throws AccountNotFoundException;
    List<Accounts> getAllAccounts();
    boolean deleteAccount(Long id) throws AccountNotFoundException;
    Accounts getAccountById(Long id);
    Accounts updateAccount(Long id, Accounts account) throws AccountNotFoundException;
    boolean softDeleteAccount(Long id);
    boolean recoverAccount(Long id);
    List<Accounts> getAllAccountsByRoleId(Long roleId);
    List<Accounts> getRecentAccountRegister();
    Long countAllByRoles(Long roleId);
}
