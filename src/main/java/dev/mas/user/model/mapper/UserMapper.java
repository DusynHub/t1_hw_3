package dev.mas.user.model.mapper;

import dev.mas.user.model.User;
import dev.mas.user.model.dto.NewUserDto;
import dev.mas.user.model.dto.UpdateUserDto;
import dev.mas.user.model.dto.UserDto;
import dev.mas.user.model.dto.UserDtoForOrder;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    UserDtoForOrder toUserDtoForOrder(User user);

    User toUser(UserDto userdto);

    User newToUser(NewUserDto newUser);

    User updateToUser(UpdateUserDto newUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFields(UpdateUserDto updateUser,
                          @MappingTarget User userToBeUpdated);

}
