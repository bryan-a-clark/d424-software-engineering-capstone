package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserDto;
import edu.wgu.activitytracker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error, Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserDto());
        if (error != null) model.addAttribute("error", "Invalid username or password. Please try again.");

        return "pages/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserDto());
        return "pages/register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserDto userDto, Model model) {
        if (userService.addUser(userDto)) {
            model.addAttribute("message", "User registered successfully");
        } else {
            model.addAttribute("error", "User registration failed");
        }
        model.addAttribute("user", new UserDto());
        return "pages/register";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model, CsrfToken csrfToken) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("error", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return "pages/register";
    }
}
