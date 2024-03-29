package dev.mas.user.service;

import dev.mas.exception.ResourceNotFoundException;
import dev.mas.user.model.User;
import dev.mas.user.model.dto.NewUserDto;
import dev.mas.user.model.dto.UpdateUserDto;
import dev.mas.user.model.dto.UserDto;
import dev.mas.user.model.mapper.UserMapper;
import dev.mas.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto saveUser(NewUserDto newUserDto) {
        return userMapper.toUserDto(userRepository.save(userMapper.newToUser(newUserDto)));
    }

    @Override
    public UserDto getUserById(long userId) {
        return userMapper.toUserDto(getUserByIdMandatory(userId));
    }

    @Override
    public User getUserEntity(long userId) {
        return getUserByIdMandatory(userId);
    }

    @Override
    @Transactional
    public UserDto updateUser(long id, UpdateUserDto updateUser) {
        User userToBeUpdated = getUserByIdMandatory(id);

        userMapper.updateUserFields(updateUser, userToBeUpdated);

        return userMapper.toUserDto(
                userRepository.save(userToBeUpdated)
        );
    }

    @Override
    @Transactional
    public void deleteUser(long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format("User with id = '%d' not found and can't be deleted", id)
            );
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAllBy(pageRequest)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    private User getUserByIdMandatory(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("User with id = '%d' not found", id)
                )
        );
    }

}
