package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    protected SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String getSignup() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute UserPayload user, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (user.getUsername().isEmpty()) {
            signupError = "The username must not be empty.";
        } else if (user.getPassword().isEmpty()) {
            signupError = "The password must not be empty.";
        } else if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            // this attribute will be handed to the login controller as model attribute
            redirectAttributes.addFlashAttribute("signupSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }
    }
}
