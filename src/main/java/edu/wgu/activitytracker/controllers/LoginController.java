package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserDto;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error, Model model, CsrfToken csrfToken) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("csrfToken", csrfToken);
        if (error != null) model.addAttribute("error", "Invalid username or password. Please try again.");
        return "login";
    }
}
