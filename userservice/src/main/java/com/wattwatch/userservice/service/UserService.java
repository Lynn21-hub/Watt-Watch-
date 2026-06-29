package com.wattwatch.userservice.service;
// here is all the logic to create a user 
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.wattwatch.userservice.dto.UserDto;
import com.wattwatch.userservice.dto.UpdateUserDto;
import com.wattwatch.userservice.entity.User;
import com.wattwatch.userservice.repository.UserRepository;

@Slf4j //this is a logging framework that allows us to log messages at different levels (info, debug, error, etc.)
@Service
public class UserService {
    private final UserRepository userRepository;
    //inject the user repository through the constructor 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userdto){
        final User createdUser= User.builder()
                .name(userdto.getName())
                .surname(userdto.getSurname())
                .email(userdto.getEmail())
                .address(userdto.getAddress())
                .alerting(userdto.isAlerting())
                .alertThreshold(userdto.getAlertThreshold())
                .build();
        User saved = userRepository.save(createdUser);
        return toDto(saved); 
    }

    private UserDto toDto(User user){ //turns the User (internal implementation) into a UserDto and send it back to client 
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .alertThreshold(user.getAlertThreshold())
                .build();
    }

    public UserDto getUserById(Long id){
       
        return userRepository.findById(id).map(this::toDto).orElse(null);
    }

    public void updateUser(Long id,UserDto userDto){
       
        User user=userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found"));

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setAlerting(userDto.isAlerting());
        user.setAlertThreshold(userDto.getAlertThreshold());
        userRepository.save(user);
    }
    
    public void deleteUser(Long id){
       
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }

    public UserDto patchUser(Long id, UpdateUserDto updateUserDto) {
      
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }
        if (updateUserDto.getSurname() != null) {
            user.setSurname(updateUserDto.getSurname());
        }
        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }
        if (updateUserDto.getAddress() != null) {
            user.setAddress(updateUserDto.getAddress());
        }
        if (updateUserDto.getAlerting() != null) {
            user.setAlerting(updateUserDto.getAlerting());
        }
        if (updateUserDto.getAlertThreshold() != null) {
            user.setAlertThreshold(updateUserDto.getAlertThreshold());
        }

        User saved = userRepository.save(user);
        return toDto(saved);
    }


    
}
