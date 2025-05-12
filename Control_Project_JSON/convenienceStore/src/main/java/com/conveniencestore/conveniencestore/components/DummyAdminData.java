package com.conveniencestore.conveniencestore.components;

import com.conveniencestore.conveniencestore.domain.users.User;
import com.conveniencestore.conveniencestore.domain.users.UserDTO;
import com.conveniencestore.conveniencestore.domain.users.UserRoles;
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
        UserDTO userDTO = new UserDTO("diego", "diego@email.com", password, UserRoles.ADMIN);
        User user = new User(userDTO);
        this.userRepository.save(user);

        for (int i = 0; i < 399; i++){
            String pwd = new BCryptPasswordEncoder().encode(generateRandomPassword());
            UserDTO userDTO1 = new UserDTO("user" + i, "user" + i + "@email.com", pwd, UserRoles.EMPLOYEE);
            User user1 = new User(userDTO1);
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
