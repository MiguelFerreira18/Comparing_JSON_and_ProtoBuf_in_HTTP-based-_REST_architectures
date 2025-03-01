package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.users.*;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserAlreadyExistsException;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserNotFoundException;
import com.conveniencestore.conveniencestore.protobuf.*;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements ServiceInterface<UserResponseDto.UserResponseDTO, UserDto.UserDTO> {
    private final UserRepository userRepository;

    public UserResponseDto.UserResponseDTO insert(UserDto.UserDTO data) {
        if (userRepository.findUserByEmail(data.getEmail()).isPresent()) throw new UserAlreadyExistsException();
        String password = new BCryptPasswordEncoder().encode(data.getPassword());
        data = UserDto.UserDTO.newBuilder()
                .setUsername(data.getUsername())
                .setEmail(data.getEmail())
                .setPassword(password)
                .setRole(data.getRole()).build();
        User user = new User(data);
        user = this.userRepository.save(user);

        return UserResponseDto.UserResponseDTO.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(convertRole(user.getRole()))
                .setCreatedAt(convertLocalDateTime(user.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(user.getUpdatedAt())).build();
    }


    public List<UserResponseDto.UserResponseDTO> getAll(String orderby, String order) {
        Sort.Direction direction;
        switch (order) {
            case "asc" -> {
                direction = Sort.Direction.ASC;
            }
            case "desc" -> {
                direction = Sort.Direction.DESC;
            }
            default -> {
                direction = Sort.DEFAULT_DIRECTION;
            }
        }
        List<UserResponseDTO> users = this.userRepository.findAllFilteredResponse(Sort.by(direction, orderby));
        return users.stream().map(this::convertFromUserResponseDto).toList();

    }

    public UserResponseDto.UserResponseDTO getById(int id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserResponseDto.UserResponseDTO.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(convertRole(user.getRole()))
                .setCreatedAt(convertLocalDateTime(user.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(user.getUpdatedAt())).build();
    }

    public UserResponseDto.UserResponseDTO delete(int id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return UserResponseDto.UserResponseDTO.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(convertRole(user.getRole()))
                .setCreatedAt(convertLocalDateTime(user.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(user.getUpdatedAt())).build();
    }

    public  UserResponseDto.UserResponseDTO update(int id, UserDto.UserDTO data) {
        throw new UnsupportedOperationException();
    }

    public UserResponseDto.UserResponseDTO update(int id, EditUserDto.EditUserDTO data) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (!data.getUsername().isEmpty()) {
            user.setUsername(data.getUsername());
        }
        if (!data.getEmail().isEmpty()) {
            user.setEmail(data.getEmail());
        }
        this.userRepository.save(user);
        return UserResponseDto.UserResponseDTO.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(convertRole(user.getRole()))
                .setCreatedAt(convertLocalDateTime(user.getCreatedAt()))
                .setUpdatedAt(convertLocalDateTime(user.getUpdatedAt())).build();
    }


    private UserResponseDto.UserResponseDTO convertFromUserResponseDto(UserResponseDTO user) {
        return UserResponseDto.UserResponseDTO.newBuilder()
                .setId(user.id())
                .setUsername(user.username())
                .setEmail(user.email())
                .setRole(convertRole(user.role()))
                .setCreatedAt(convertLocalDateTime(user.createdAt()))
                .setUpdatedAt(convertLocalDateTime(user.updatedAt())).build();
    }
    private UserRolesOuterClass.UserRoles convertRole(UserRoles role){
        if (role == UserRoles.ADMIN) return UserRolesOuterClass.UserRoles.ADMIN;
        else return UserRolesOuterClass.UserRoles.EMPLOYEE;
    }
    private LocalDateTimeOuterClass.LocalDateTime convertLocalDateTime(LocalDateTime time){
        return LocalDateTimeOuterClass.LocalDateTime.newBuilder()
                .setYear(time.getYear())
                .setMonth(time.getMonthValue())
                .setDay(time.getDayOfMonth())
                .build();
    }


}
