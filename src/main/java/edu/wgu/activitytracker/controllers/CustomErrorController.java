package edu.wgu.activitytracker.controllers;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

@Controller
@AllArgsConstructor
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(Model model, WebRequest webRequest) {
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(
            webRequest,
            ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE)
        );

        model.addAttribute("errorDetails", errorMap);
        return "pages/error";
    }

    public String getErrorPath() {
        return "/error";
    }


}
