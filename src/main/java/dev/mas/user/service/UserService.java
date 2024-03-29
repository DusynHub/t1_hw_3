package dev.mas.user.service;

import dev.mas.user.model.User;
import dev.mas.user.model.dto.NewUserDto;
import dev.mas.user.model.dto.UpdateUserDto;
import dev.mas.user.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(NewUserDto newUserDto);


    UserDto updateUser(long id, UpdateUserDto updateUser);

    void deleteUser(long id);

    List<UserDto> getAllUsers(int page, int size);

    UserDto getUserById(long userId);

    User getUserEntity(long userId);


}
