package com.example.transporttn.services;

import com.example.transporttn.entites.Account;

public interface IUserService {
    Account registerUser(Account user);

    String loginUser(String email, String password);

}
