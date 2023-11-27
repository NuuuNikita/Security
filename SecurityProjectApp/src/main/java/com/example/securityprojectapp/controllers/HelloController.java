package com.example.securityprojectapp.controllers;

import com.example.securityprojectapp.security.PersonDetails;
import com.example.securityprojectapp.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    private final AdminServices adminServices;

    @Autowired
    public HelloController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminServices.doAdminStuff();
        return "/admin";
    }
}
