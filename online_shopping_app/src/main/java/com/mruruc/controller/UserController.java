package com.mruruc.controller;

import com.mruruc.model.Address;
import com.mruruc.model.User;
import com.mruruc.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public String register(@ModelAttribute User user, Model model, HttpSession session) {
        try {
            User savedUser = userService.saveUser(user);
            session.setAttribute("user", savedUser);
            return "redirect:/address";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/register";
        }
    }

    @PostMapping(value = "/register-address")
    public String saveAddress(@ModelAttribute Address address) {
        userService.saveAddress(address);
        return "redirect:/";
    }

    @PostMapping(value = "/login-credential")
    public String login(@ModelAttribute User user, HttpSession httpSession, Model model) {
        try {
            httpSession.setAttribute("user", userService.loginCredential(user));
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }


    private Map<String, String> getErrorMap(BindingResult bindingResult) {
        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors()
                .forEach((error) -> {
                    map.put(error.getField(), error.getDefaultMessage());
                });
        return map;
    }

}
