package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.DistanceUnitDto;
import edu.wgu.activitytracker.services.DistanceUnitService;
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
@RequestMapping("/distance-units")
public class DistanceUnitController {

    private final DistanceUnitService distanceUnitService;

    @GetMapping("/all")
    public String getAllDistanceUnits(Model model) {
        model.addAttribute("distanceUnits", distanceUnitService.getAllDistanceUnitsByLoggedInUser());
        return "pages/distance-unit/distance-units";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("distanceUnit", new DistanceUnitDto());
        return "pages/distance-unit/distance-unit-form";
    }


    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("distanceUnit", distanceUnitService.getDistanceUnitById(id));
        return "pages/distance-unit/distance-unit-form";
    }


    @PostMapping("/save")
    public String saveDistanceUnit(@Valid DistanceUnitDto distanceUnitDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("distanceUnit", distanceUnitDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/distance-unit/distance-unit-form";
        }
        distanceUnitService.saveDistanceUnit(distanceUnitDto);
        return "redirect:/distance-units/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteDistanceUnit(@PathVariable Integer id) {
        distanceUnitService.deleteDistanceUnit(id);
        return "redirect:/distance-units/all";
    }
}
