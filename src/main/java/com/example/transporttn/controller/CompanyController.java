package com.example.transporttn.controller;

import com.example.transporttn.entites.Account;
import com.example.transporttn.entites.Company;
import com.example.transporttn.services.IAccountService;
import com.example.transporttn.services.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ICompanyService iCompanyService;

    @PostMapping("/register")
    public Company registerUser(@RequestBody Company company) {
        iCompanyService.registerCompany(company);
        return company;
    }

}
