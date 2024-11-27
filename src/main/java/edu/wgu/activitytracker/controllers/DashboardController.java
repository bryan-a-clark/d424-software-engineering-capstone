package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.services.ActivityService;
import edu.wgu.activitytracker.services.DistanceUnitService;
import edu.wgu.activitytracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final ActivityService activityService;
    private final UserService userService;
    private final DistanceUnitService distanceUnitService;

    @GetMapping("/dashboard")
    public String home(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnits());
        model.addAttribute("userActivity", new UserActivityDto());
        model.addAttribute("user", userService.getCurrentlyLoggedInUser());

        return "pages/dashboard";
    }
}
