package com.conveniencestore.conveniencestore.services;

import com.conveniencestore.conveniencestore.domain.users.EditUserDTO;
import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserResponseDTO;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserAlreadyExistsException;
import com.conveniencestore.conveniencestore.domain.users.exceptions.UserNotFoundException;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements ServiceInterface<UserResponseDTO, UserDTO> {
    private final UserRepository userRepository;

    public UserResponseDTO insert(UserDTO data) {
        if (userRepository.findUserByEmail(data.email()).isPresent()) throw new UserAlreadyExistsException();
        String password = new BCryptPasswordEncoder().encode(data.password());
        data = new UserDTO(data.username(), data.email(), password, data.role());
        User user = new User(data);
        user = this.userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public List<UserResponseDTO> getAll(String orderby, String order) {
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
        return this.userRepository.findAllFilteredResponse(Sort.by(direction, orderby));
    }

    public UserResponseDTO getById(int id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserResponseDTO delete(int id) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        this.userRepository.delete(user);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserResponseDTO update(int id, UserDTO data) {
        throw new UnsupportedOperationException();
    }

    public UserResponseDTO update(int id, EditUserDTO data) {
        User user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(data.username() != null){
            user.setUsername(data.username());
        }
        if(data.email() != null){
            user.setEmail(data.email());
        }
        this.userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }


}
