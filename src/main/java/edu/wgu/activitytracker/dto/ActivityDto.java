package edu.wgu.activitytracker.dto;

import edu.wgu.activitytracker.validation.UniqueActivityName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivityDto {

    private Integer id;

    @NotNull(message="Name is required")
    @Size(min=1, message="Name is required")
    @UniqueActivityName
    private String name;

    private boolean isDefault;
}
