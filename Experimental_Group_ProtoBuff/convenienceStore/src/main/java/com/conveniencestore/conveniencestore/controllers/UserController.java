package com.conveniencestore.conveniencestore.controllers;

import com.conveniencestore.conveniencestore.domain.Error.ErrorDTO;
import com.conveniencestore.conveniencestore.domain.users.*;
import com.conveniencestore.conveniencestore.infra.security.TokenService;
import com.conveniencestore.conveniencestore.protobuf.EditUserDto;
import com.conveniencestore.conveniencestore.protobuf.UserDto;
import com.conveniencestore.conveniencestore.protobuf.UserResponseDto;
import com.conveniencestore.conveniencestore.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Get all users",
            description = "Get all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UserResponseJsonDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request param is not valid",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
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
    @Operation(
            summary = "Get user by id",
            description = "Get user by id",
            responses = {
                    @ApiResponse(
                        responseCode = "200",
                            description = "User found",
                            content = {
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponseJsonDTO.class)
                                )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
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
        UserResponseJsonDTO responseDTO = new UserResponseJsonDTO(user.getId(), user.getUsername(), user.getEmail(),
                user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        return ResponseEntity.ok().body(new LoginResponseDTO(token.token(), token.expiresAt(), responseDTO));
    }

    //! to proto
    @Operation(
            summary = "Register a new user",
            description = "Register a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UserDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
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
    @Operation(
            summary = "Edit a user",
            description = "Edit a user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User edited",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UserResponseJsonDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<UserResponseDto.UserResponseDTO> editUser(@PathVariable Integer id,@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User data",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EditUserDTO.class)
            )

    ) @RequestBody @Valid EditUserDto.EditUserDTO data) {
        UserResponseDto.UserResponseDTO user = this.service.update(id, data);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "{id}", produces = "application/x-protobuf")
    @Operation(
            summary = "Delete a user",
            description = "Delete a user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User deleted",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UserResponseJsonDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorDTO.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<UserResponseDto.UserResponseDTO> deleteUser(@PathVariable Integer id) {
        if (id == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.service.delete(id));
    }
}
