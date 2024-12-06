package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.services.ActivityService;
import edu.wgu.activitytracker.services.DistanceUnitService;
import edu.wgu.activitytracker.services.UserActivityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user-activities")
public class UserActivityController {

    private final ActivityService activityService;
    private final UserActivityService userActivityService;
    private final DistanceUnitService distanceUnitService;

    @GetMapping()
    public String getDashboard(Model model) {
        model.addAttribute("userActivities", userActivityService.getLoggedInUsersUserActivities());
        return "pages/user-activity-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("activities", activityService.getAllActivitiesByLoggedInUser());
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnitsByLoggedInUser());
        model.addAttribute("userActivity", new UserActivityDto());
        return "pages/user-activity-form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("activities", activityService.getAllActivitiesByLoggedInUser());
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnitsByLoggedInUser());
        model.addAttribute("userActivity", userActivityService.getActivityById(id));
        return "pages/user-activity-form";
    }

    @PostMapping("/user-activity/save")
    public String addUserActivity(@Valid UserActivityDto userActivityDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activities", activityService.getAllActivitiesByLoggedInUser());
            model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnitsByLoggedInUser());
            model.addAttribute("userActivity", userActivityDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/user-activity-form";
        }
        userActivityService.saveUserActivity(userActivityDto);
        return "redirect:/user-activities";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserActivity(@PathVariable Integer id) {
        userActivityService.deleteUserActivity(id);
        return "redirect:/user-activities";
    }
}
