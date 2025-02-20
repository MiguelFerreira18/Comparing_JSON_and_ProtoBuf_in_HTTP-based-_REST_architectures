package com.isep.acme.controllers;

import com.isep.acme.model.View.UserView;
import com.isep.acme.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userServiceImpl;

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable final Long userId) {

        return userServiceImpl.getUser(userId);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetails> create(@PathVariable final String username) {
        UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

        return ResponseEntity.ok().body(userDetails);
    }
}
