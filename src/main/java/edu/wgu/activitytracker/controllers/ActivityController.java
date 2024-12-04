package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.ActivityDto;
import edu.wgu.activitytracker.services.ActivityService;
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
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public String getAllDistanceUnits(Model model) {
        model.addAttribute("activities", activityService.getAllActivitiesByLoggedInUser());
        return "pages/activity/activity-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("activity", new ActivityDto());
        return "pages/activity/activity-form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("activity", activityService.getActivityById(id));
        return "pages/activity/activity-form";
    }

    @PostMapping("/save")
    public String saveDistanceUnit(@Valid ActivityDto activityDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activity", activityDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/activity/activity-form";
        }
        activityService.saveActivity(activityDto);
        return "redirect:/activities";
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Integer id) {
        activityService.deleteActivity(id);
        return "redirect:/activities";
    }
}
