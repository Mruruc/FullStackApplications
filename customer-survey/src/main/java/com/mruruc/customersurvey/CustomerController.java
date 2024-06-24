package com.mruruc.customersurvey;

import com.mruruc.customersurvey.model.Customer;
import com.mruruc.customersurvey.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repo;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "survey";
    }
    @PostMapping("/")
    public String submitForm(@ModelAttribute Customer customer) {
       repo.save(customer);
        return "success";
    }


}
