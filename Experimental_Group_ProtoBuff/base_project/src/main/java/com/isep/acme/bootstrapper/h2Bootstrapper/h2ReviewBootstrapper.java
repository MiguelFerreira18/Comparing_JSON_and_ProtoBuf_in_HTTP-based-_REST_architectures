package com.isep.acme.bootstrapper.h2Bootstrapper;

import com.isep.acme.model.H2Entity.*;
import com.isep.acme.model.Role;
import com.isep.acme.repositories.h2Repos.Repos.ProductRepository;
import com.isep.acme.repositories.h2Repos.Repos.RatingRepository;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import com.isep.acme.repositories.h2Repos.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("h2Profile")//h2Profile
@Component
public class h2ReviewBootstrapper implements CommandLineRunner {

    @Autowired
    private ReviewRepository repo;
    @Autowired
    private ProductRepository pRepo;
    @Autowired
    private RatingRepository ratingRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("admin3@mail.com").isEmpty()) {
            User admin1 = new User("admin3@mail.com", encoder.encode("AdminPW3"),
                    "Jose Antonio2", "353489123", "Rua tres");
            admin1.addAuthority(new Role(Role.Admin));
            userRepo.save(admin1);
            if (ratingRepo.findByRate(4.0).isEmpty()) {
                Rating rate4 = new Rating(4.0);
                ratingRepo.save(rate4);
                if (pRepo.findBySku("azd578fgh267").isEmpty()) {
                    Product p1 = new Product("azd578fgh267", "lord of the rings book", "Book about lord of the rings trilogy");
                    pRepo.save(p1);

                    Review review1 = new Review("Amazing book", LocalDate.now(), p1,rate4, admin1);
                    repo.save(review1);
                    Review review2 = new Review("Incredible book", LocalDate.now(), p1,rate4, admin1);
                    repo.save(review2);
                    Review review3 = new Review("Couldn't ask for better book", LocalDate.now(), p1,rate4, admin1);
                    repo.save(review3);
                    Review review4 = new Review("Great book", LocalDate.now(), p1,rate4, admin1);
                    repo.save(review4);
                    Review review5 = new Review("My only regret is not having read this earlier book", LocalDate.now(), p1,rate4, admin1);
                    repo.save(review5);
                }
            }

        }




    }

}
