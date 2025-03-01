package com.conveniencestore.conveniencestore.components;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserRoles;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyAdminData implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String password = new BCryptPasswordEncoder().encode("123456");
        UserDTO userDTO = new UserDTO("diego", "diego@email.com", password, UserRoles.ADMIN);
        User user = new User(userDTO);
        this.userRepository.save(user);
    }
}
