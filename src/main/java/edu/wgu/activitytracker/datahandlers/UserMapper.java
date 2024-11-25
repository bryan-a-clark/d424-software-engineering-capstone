package edu.wgu.activitytracker.datahandlers;

import edu.wgu.activitytracker.dto.UserDto;
import edu.wgu.activitytracker.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public User mapDtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword());
    }

    @Override
    public UserDto mapEntityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
