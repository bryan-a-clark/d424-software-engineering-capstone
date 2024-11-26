package edu.wgu.activitytracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import edu.wgu.activitytracker.validation.UniqueUser;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;
    @NotBlank(message = "Username is required")
    @UniqueUser
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}
