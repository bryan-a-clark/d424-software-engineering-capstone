package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.datahandlers.UserMapper;
import edu.wgu.activitytracker.dto.UserDto;
import edu.wgu.activitytracker.entities.User;
import edu.wgu.activitytracker.repositories.UserRepository;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public boolean addUser(UserDto userDto) {
        var userToSave = userMapper.mapDtoToEntity(userDto);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        var savedUser = userRepository.save(userToSave);

        return userRepository.findById(savedUser.getId()).isPresent();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public User getCurrentlyLoggedInUser() {
        return Objects.requireNonNull(userRepository.findUserByUsername(getLoggedInUsername()).orElse(null));
    }

    private String getLoggedInUsername() {
        Authentication authentication = authenticationService.getAuthentication();
        return authentication.getName();
    }
}
