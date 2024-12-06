package edu.wgu.activitytracker.controllers;

import edu.wgu.activitytracker.dto.ReportDto;
import edu.wgu.activitytracker.services.ReportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public String report(Model model) {
        model.addAttribute("report", new ReportDto());
        return "pages/generate-report";
    }

    @PostMapping("/generate")
    public String report(@Valid ReportDto reportDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("report", reportDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/generate-report";
        }
        var report = reportService.generateReportData(reportDto);
        model.addAttribute("report", report);
        model.addAttribute("activities", report.getUserActivities());
        return "pages/report";
    }
}
