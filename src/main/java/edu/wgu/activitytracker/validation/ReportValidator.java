package edu.wgu.activitytracker.validation;

import edu.wgu.activitytracker.dto.ReportDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportValidator implements ConstraintValidator<EndDateAfterStartDate, ReportDto> {

    @Override
    public boolean isValid(ReportDto reportDto, ConstraintValidatorContext constraintValidatorContext) {
        if (reportDto.getStartDate() == null || reportDto.getEndDate() == null) {
            return true;
        }
        return reportDto.getEndDate().isAfter(reportDto.getStartDate());
    }
}
