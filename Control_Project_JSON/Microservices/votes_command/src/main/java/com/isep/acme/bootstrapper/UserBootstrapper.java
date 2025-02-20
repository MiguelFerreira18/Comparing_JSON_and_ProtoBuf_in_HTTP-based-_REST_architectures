package com.isep.acme.bootstrapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Review;
import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;

@Component
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ReviewRepository repo;

    @Override
    public void run(String... args) throws Exception {

        // boot();

        
    }

    public void boot(){
        //admin
        if(userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            User admin1 = new User("admin1@mail.com", encoder.encode("AdminPW1"),
                    "Jose Antonio", "355489123", "Rua Um");
            admin1.addAuthority(new Role(Role.Admin));

            userRepo.save(admin1);
        }

        if(userRepo.findByUsername("admin2@mail.com").isEmpty()) {
            User mod1 = new User("admin2@mail.com", encoder.encode("AdminPW2"),
                    "Antonio Jose", "321984553", "Rua dois");
            mod1.addAuthority(new Role(Role.Mod));
            userRepo.save(mod1);
        }
        if(userRepo.findByUsername("user1@mail.com").isEmpty()) {
            User user1 = new User("user1@mail.com", encoder.encode("userPW1"),
                    "Nuno Miguel", "253647883", "Rua tres");
            user1.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user1);
        }
        if(userRepo.findByUsername("user2@mail.com").isEmpty()) {
            User user2 = new User("user2@mail.com", encoder.encode("userPW2"),
                    "Miguel Nuno", "253698854", "Rua quatro");
            user2.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user2);
        }
        if(userRepo.findByUsername("user3@mail.com").isEmpty()) {
            User user3 = new User("user3@mail.com", encoder.encode("userPW3"),
                    "Antonio Pedro", "254148863", "Rua vinte");
            user3.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user3);
        }

        if(userRepo.findByUsername("user4@mail.com").isEmpty()) {
            User user4 = new User("user4@mail.com", encoder.encode("userPW4"),
                    "Pedro Antonio", "452369871", "Rua cinco");
            user4.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user4);
        }
        if(userRepo.findByUsername("user5@mail.com").isEmpty()) {
            User user5 = new User("user5@mail.com", encoder.encode("userPW5"),
                    "Ricardo Joao", "452858596", "Rua seis");
            user5.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user5);
        }
        if(userRepo.findByUsername("user6@mail.com").isEmpty()) {
            User user6 = new User("user6@mail.com", encoder.encode("userPW6"),
                    "Joao Ricardo", "425364781", "Rua sete");
            user6.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user6);
        }
        if(userRepo.findByUsername("user7@mail.com").isEmpty()) {
            User user7 = new User("user7@mail.com", encoder.encode("userPW7"),
                    "Luis Pedro", "526397747", "Rua oito");
            user7.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user7);
        }
        if(userRepo.findByUsername("user8@mail.com").isEmpty()) {
            User user8 = new User("user8@mail.com", encoder.encode("userPW8"),
                    "Pedro Luis", "523689471", "Rua nove ");
            user8.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user8);
        }

        User user9 = new User("user9@mail.com", encoder.encode("userPW9"),
                    "Marco Antonio", "253148965", "Rua dez");
            user9.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user9);

        User user10 = new User("user10@mail.com", encoder.encode("userPW10"),
                    "Antonio Marco", "201023056", "Rua onze");
            user10.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user10);

        User user11 = new User("user11@mail.com", encoder.encode("userPW11"),
                    "Rui Ricardo", "748526326", "Rua doze");
            user11.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user11);
            
        /*
        Review review1 = new Review(
            2L,
            1L,
            "approved",
            "This is a neutral review.",
            null,
            null,
            "No issues reported",
            LocalDate.now(),
            "A fun fact about the product",
            98L, 
            4.6,  
            user11
        );

        repo.save(review1);

        Review review2 = new Review(
            2L,
            1L,
            "approved",
            "This is a neutral review.",
            "No issues reported",
            LocalDate.now(),
            "A fun fact about the product",
            102L, 
            3.0,  
            user9
        );
    
        repo.save(review2);
    
        Review review3 = new Review(
            3L,
            1L,
            "approved",
            "This is a negative review.",
            "No issues reported",
            LocalDate.now(),
            "Fascinating fact about the product",
            67L, 
            4.2,  
            user10
        );

        repo.save(review3);
        */
    }
}
