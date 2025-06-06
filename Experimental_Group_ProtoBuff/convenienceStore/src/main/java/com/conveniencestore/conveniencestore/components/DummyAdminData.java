package com.conveniencestore.conveniencestore.components;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserRoles;
import com.conveniencestore.conveniencestore.protobuf.UserDto;
import com.conveniencestore.conveniencestore.protobuf.UserRolesOuterClass;
import com.conveniencestore.conveniencestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DummyAdminData implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String password = new BCryptPasswordEncoder().encode("123456");
        User user = new User(UserDto.UserDTO.newBuilder().setUsername("diego").setEmail("diego@email.com").setPassword(password).setRole(UserRolesOuterClass.UserRoles.ADMIN).build());
        this.userRepository.save(user);

        for (int i = 0; i < 399; i++){
            String pwd = new BCryptPasswordEncoder().encode(generateRandomPassword());
            User user1 = new User(UserDto.UserDTO.newBuilder().setPassword(pwd).setEmail("user" + i + "@email.com").setUsername("user" + i).setRole(UserRolesOuterClass.UserRoles.EMPLOYEE).build());
            this.userRepository.save(user1);
        }
    }

    private String generateRandomPassword(){
        Random random = new Random();
        int length = random.nextInt(10) + 6;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++){
            password.append((char) (random.nextInt(94) + 33));
        }
        return password.toString();

    }
}
