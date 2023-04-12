package com.quizgpt.accountservice.user;

import com.quizgpt.accountservice.payload.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    private final UserService userService;

    private UserRepository userRepository;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserByID(id);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);

        if (userService.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = userService.createUser(userRequest);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

        User userRequest = modelMapper.map(userDto, User.class);

        User user = userService.updateUser(id, userRequest);

        UserDto userResponse = modelMapper.map(user, UserDto.class);

        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        MessageResponse messageResponse = new MessageResponse("User Deleted Successfully.");

        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    }

}
