package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.users.*;
import com.conveniencestore.conveniencestore.infra.security.TokenService;
import com.conveniencestore.conveniencestore.protobuf.EditUserDto;
import com.conveniencestore.conveniencestore.protobuf.UserDto;
import com.conveniencestore.conveniencestore.protobuf.UserResponseDto;
import com.conveniencestore.conveniencestore.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private static final List<String> VALID_SEARCH_PARAMETERS = List.of("id", "username", "email", "asc", "desc");

    //! to proto
    @GetMapping(produces = "application/x-protobuf")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false, defaultValue = "id")
            String orderby,
            @RequestParam(required = false, defaultValue = "asc")
            String order
    ) {
        if (VALID_SEARCH_PARAMETERS.contains(orderby) && VALID_SEARCH_PARAMETERS.contains(order))
            return ResponseEntity.ok().body(UserResponseDto.UserResponseCatalog.newBuilder().addAllUsers(this.service.getAll(orderby, order)).build());
        ErrorDTO error = new ErrorDTO("Request param is not valid.", 400);
        return ResponseEntity.status(400).body(error);
    }

    //! to proto
    @GetMapping(value = "{id}", produces = "application/x-protobuf")
    public ResponseEntity<UserResponseDto.UserResponseDTO> getUserById(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserAuthDTO data) {
        UsernamePasswordAuthenticationToken usernameAndPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernameAndPassword);
        User user = (User) auth.getPrincipal();
        var token = this.tokenService.genToken(user);
        UserResponseDTO responseDTO = new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(),
                user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        return ResponseEntity.ok().body(new LoginResponseDTO(token.token(), token.expiresAt(), responseDTO));
    }

    //! to proto
    @PostMapping(produces = "application/x-protobuf", consumes = "application/x-protobuf")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserDto.UserDTO data) {
        if (data.getPassword().isEmpty()) {
            ErrorDTO error = new ErrorDTO("Please provide the password.", 400);
            return ResponseEntity.status(400).body(error);
        }
        UserResponseDto.UserResponseDTO user = this.service.insert(data);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "{id}",produces = "application/x-protobuf", consumes = "application/x-protobuf")
    public ResponseEntity<UserResponseDto.UserResponseDTO> editUser(@PathVariable Integer id, @RequestBody @Valid EditUserDto.EditUserDTO data) {
        UserResponseDto.UserResponseDTO user = this.service.update(id, data);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "{id}", produces = "application/x-protobuf")
    public ResponseEntity<UserResponseDto.UserResponseDTO> deleteUser(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.service.delete(id));
    }
}
