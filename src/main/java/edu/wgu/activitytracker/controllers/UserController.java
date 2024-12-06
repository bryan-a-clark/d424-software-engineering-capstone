package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserDto;
import edu.wgu.activitytracker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserDto());
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                String errorMessage = ex.getMessage();
                model.addAttribute("error", errorMessage);
            }
        }
        return "pages/login";
    }

    @PostMapping("/user/register")
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/register";
        }
        if (userService.addUser(userDto)) {
            model.addAttribute("success", "User registered successfully");
            redirectAttributes.addFlashAttribute("success", "User registered successfully");
            return "redirect:/login";
        } else {
            model.addAttribute("errors", List.of(new ObjectError("user", "Error saving new user.")));
            return "pages/register";
        }
    }

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "pages/register";
    }

    @GetMapping("/logout")
    public String logout(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "You have been successfully logged out.");
        return "redirect:/login";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model, CsrfToken csrfToken) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("error", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return "pages/register";
    }
}
