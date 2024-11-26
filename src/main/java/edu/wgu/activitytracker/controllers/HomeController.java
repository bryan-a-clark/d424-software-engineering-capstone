package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.services.ActivityService;
import edu.wgu.activitytracker.services.DistanceUnitService;
import edu.wgu.activitytracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ActivityService activityService;
    private final UserService userService;
    private final DistanceUnitService distanceUnitService;

    @GetMapping("/")
    public String home(Model model, CsrfToken csrfToken) {
        model.addAttribute("csrfToken", csrfToken);
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnits());
        model.addAttribute("userActivity", new UserActivityDto());
        model.addAttribute("user", userService.getCurrentlyLoggedInUser());
        return "index";
    }
}
