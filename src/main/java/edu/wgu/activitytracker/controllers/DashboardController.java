package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.services.ActivityService;
import edu.wgu.activitytracker.services.DistanceUnitService;
import edu.wgu.activitytracker.services.UserActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final ActivityService activityService;
    private final UserActivityService userActivityService;
    private final DistanceUnitService distanceUnitService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("userActivities", userActivityService.getLoggedInUsersUserActivities());
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnits());
        model.addAttribute("userActivity", new UserActivityDto());

        return "pages/dashboard";
    }

    @PostMapping("/dashboard/activity/new")
    public String addUserActivity(UserActivityDto userActivityDto) {
        userActivityService.addUserActivity(userActivityDto);
        return "redirect:/dashboard";
    }
}
