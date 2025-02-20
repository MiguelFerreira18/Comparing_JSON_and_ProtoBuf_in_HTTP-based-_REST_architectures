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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Profile("h2Profile")
@Component
public class h2BootstrapperGeral implements CommandLineRunner {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private ReviewRepository reviewRepo;


    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            User admin1 = new User("admin1@mail.com", encoder.encode("AdminPW1"),
                    "Jose Antonio", "355489123", "Rua Um");

            //AuthorityManagerServiceImpl ADD REDUNDANCY AND SECURITY, remove the addAuthority later from this admin.addAuthority
            admin1.addAuthority(new Role(Role.Admin));

            userRepo.save(admin1);
//            neo4JUserRepository.save(new Neo4jUser("admin1@mail.com", encoder.encode("AdminPW1"),
//                    "Jose Antonio", "355489123", "Rua Um"));
        }

        if (userRepo.findByUsername("admin2@mail.com").isEmpty()) {
            User mod1 = new User("admin2@mail.com", encoder.encode("AdminPW2"),
                    "Antonio Jose", "321984553", "Rua dois");
            mod1.addAuthority(new Role(Role.Mod));
            userRepo.save(mod1);
        }
        if (userRepo.findByUsername("user1@mail.com").isEmpty()) {
            User user1 = new User("user1@mail.com", encoder.encode("userPW1"),
                    "Nuno Miguel", "253647883", "Rua tres");
            user1.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user1);
        }
        if (userRepo.findByUsername("user2@mail.com").isEmpty()) {
            User user2 = new User("user2@mail.com", encoder.encode("userPW2"),
                    "Miguel Nuno", "253698854", "Rua quatro");
            user2.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user2);
        }
        if (userRepo.findByUsername("user3@mail.com").isEmpty()) {
            User user3 = new User("user3@mail.com", encoder.encode("userPW3"),
                    "Antonio Pedro", "254148863", "Rua vinte");
            user3.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user3);
        }

        if (userRepo.findByUsername("user4@mail.com").isEmpty()) {
            User user4 = new User("user4@mail.com", encoder.encode("userPW4"),
                    "Pedro Antonio", "452369871", "Rua cinco");
            user4.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user4);
        }
        if (userRepo.findByUsername("user5@mail.com").isEmpty()) {
            User user5 = new User("user5@mail.com", encoder.encode("userPW5"),
                    "Ricardo Joao", "452858596", "Rua seis");
            user5.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user5);
        }
        if (userRepo.findByUsername("user6@mail.com").isEmpty()) {
            User user6 = new User("user6@mail.com", encoder.encode("userPW6"),
                    "Joao Ricardo", "425364781", "Rua sete");
            user6.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user6);
        }
        if (userRepo.findByUsername("user7@mail.com").isEmpty()) {
            User user7 = new User("user7@mail.com", encoder.encode("userPW7"),
                    "Luis Pedro", "526397747", "Rua oito");
            user7.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user7);
        }
        if (userRepo.findByUsername("user8@mail.com").isEmpty()) {
            User user8 = new User("user8@mail.com", encoder.encode("userPW8"),
                    "Pedro Luis", "523689471", "Rua nove ");
            user8.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user8);
        }
        if (userRepo.findByUsername("user9@mail.com").isEmpty()) {
            User user9 = new User("user9@mail.com", encoder.encode("userPW9"),
                    "Marco Antonio", "253148965", "Rua dez");
            user9.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user9);
        }
        if (userRepo.findByUsername("user10@mail.com").isEmpty()) {
            User user10 = new User("user10@mail.com", encoder.encode("userPW10"),
                    "Antonio Marco", "201023056", "Rua onze");
            user10.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user10);
        }
        if (userRepo.findByUsername("user11@mail.com").isEmpty()) {
            User user11 = new User("user11@mail.com", encoder.encode("userPW11"),
                    "Rui Ricardo", "748526326", "Rua doze");
            user11.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user11);
        }

        if (productRepo.findBySku("asd578fgh267").isEmpty()) {
            Product p1 = new Product("asd578fgh267", "Pen", "very good nice product");
            productRepo.save(p1);
        }
        if (productRepo.findBySku("c1d4e7r8d5f2").isEmpty()) {
            Product p2 = new Product("c1d4e7r8d5f2", "Pencil", " writes ");
            productRepo.save(p2);
        }
        if (productRepo.findBySku("c4d4f1v2f5v3").isEmpty()) {
            Product p3 = new Product("c4d4f1v2f5v3", "Rubber", "erases");
            productRepo.save(p3);
        }
        if (productRepo.findBySku("v145dc2365sa").isEmpty()) {
            Product p4 = new Product("v145dc2365sa", "Wallet", "stores money");
            productRepo.save(p4);
        }
        if (productRepo.findBySku("fg54vc14tr78").isEmpty()) {
            Product p5 = new Product("fg54vc14tr78", "pencil case", " stores pencils");
            productRepo.save(p5);
        }
        if (productRepo.findBySku("12563dcfvg41").isEmpty()) {
            Product p6 = new Product("12563dcfvg41", "Glasses case", " stores glasses");
            productRepo.save(p6);
        }
        if (productRepo.findBySku("vcg46578vf32").isEmpty()) {
            Product p7 = new Product("vcg46578vf32", "tissues", " nose clearing material");
            productRepo.save(p7);
        }
        if (productRepo.findBySku("vgb576hgb675").isEmpty()) {
            Product p8 = new Product("vgb576hgb675", "mouse pad", " mouse adapted surface");
            productRepo.save(p8);
        }
        if (productRepo.findBySku("unbjh875ujg7").isEmpty()) {
            Product p9 = new Product("unbjh875ujg7", " mug ", " drink something from it");
            productRepo.save(p9);
        }
        if (productRepo.findBySku("u1f4f5e2d2xw").isEmpty()) {
            Product p10 = new Product("u1f4f5e2d2xw", " Lamp ", " it lights");
            productRepo.save(p10);
        }
        if (productRepo.findBySku("j85jg76jh845").isEmpty()) {
            Product p11 = new Product("j85jg76jh845", " Pillow ", " cold both sides");
            productRepo.save(p11);
        }
        if (productRepo.findBySku("g4f7e85f4g54").isEmpty()) {
            Product p12 = new Product("g4f7e85f4g54", " chair ", " comfortable ");
            productRepo.save(p12);
        }

        if (ratingRepo.findByRate(0.5).isEmpty()) {
            Rating rate05 = new Rating(0.5);
            ratingRepo.save(rate05);
        }

        if (ratingRepo.findByRate(1.0).isEmpty()) {
            Rating rate1 = new Rating(1.0);
            ratingRepo.save(rate1);
        }

        if (ratingRepo.findByRate(1.5).isEmpty()) {
            Rating rate15 = new Rating(1.5);
            ratingRepo.save(rate15);
        }

        if (ratingRepo.findByRate(2.0).isEmpty()) {
            Rating rate2 = new Rating(2.0);
            ratingRepo.save(rate2);
        }

        if (ratingRepo.findByRate(2.5).isEmpty()) {
            Rating rate25 = new Rating(2.5);
            ratingRepo.save(rate25);
        }

        if (ratingRepo.findByRate(3.0).isEmpty()) {
            Rating rate3 = new Rating(3.0);
            ratingRepo.save(rate3);
        }

        if (ratingRepo.findByRate(3.5).isEmpty()) {
            Rating rate35 = new Rating(3.5);
            ratingRepo.save(rate35);
        }

        if (ratingRepo.findByRate(4.0).isEmpty()) {
            Rating rate4 = new Rating(4.0);
            ratingRepo.save(rate4);
        }

        if (ratingRepo.findByRate(4.5).isEmpty()) {
            Rating rate45 = new Rating(4.5);
            ratingRepo.save(rate45);
        }

        if (ratingRepo.findByRate(5.0).isEmpty()) {
            Rating rate5 = new Rating(5.0);
            ratingRepo.save(rate5);
        }

        if (reviewRepo.findById(1L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i <7; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }

            Long idReview = 1L;
            long version = 1L;
            String approvalStatus = "Approved";
            String reviewText = "Review text";
            String report = "dont report";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Not that fun";
            Product p1 = productRepo.findById(18L).get();
            Rating rating = ratingRepo.findByRate(0.5).get();
            User user = userRepo.getById(5L);
            Review review1 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p1, rating, user);
            reviewRepo.save(review1);
        }

        if (reviewRepo.findById(2L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }

            Long idReview = 2L;
            long version = 2L;
            String approvalStatus = "Approved";
            String reviewText = "Another review";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Interesting fact";
            Product p2 = productRepo.findById(17L).get();
            Rating rating = ratingRepo.findByRate(1.0).get();
            User user = userRepo.getById(5L);
            Review review2 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p2, rating, user);
            reviewRepo.save(review2);
        }
        if (reviewRepo.findById(3L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }

            Long idReview = 3L;
            long version = 3L;
            String approvalStatus = "Approved";
            String reviewText = "A different review";
            String report = "Reported";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Fascinating fact";
            Product p3 = productRepo.findById(16L).get();
            Rating rating = ratingRepo.findByRate(1.0).get();
            User user = userRepo.getById(5L);
            Review review3 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p3, rating, user);
            reviewRepo.save(review3);
        }

        if (reviewRepo.findById(4L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }

            Long idReview = 4L;
            long version = 4L;
            String approvalStatus = "Approved";
            String reviewText = "Updated review with new insights";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Surprising fact";
            Product p4 = productRepo.findById(15L).get();
            Rating rating = ratingRepo.findByRate(2.0).get();
            User user = userRepo.getById(5L);
            Review review4 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p4, rating, user);
            reviewRepo.save(review4);
        }

        if (reviewRepo.findById(5L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();

            Long idReview = 5L;
            long version = 5L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p5 = productRepo.findById(14L).get();
            Rating rating = ratingRepo.findByRate(2.5).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p5, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(6L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 6L;
            long version = 6L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p6 = productRepo.findById(24L).get();
            Rating rating = ratingRepo.findByRate(3.0).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p6, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(7L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 7L;
            long version = 7L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p7 = productRepo.findById(23L).get();
            Rating rating = ratingRepo.findByRate(0.5).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p7, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(8L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }

            Long idReview = 8L;
            long version = 8L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p8 = productRepo.findById(22L).get();
            Rating rating = ratingRepo.findByRate(1.0).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p8, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(9L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 9L;
            long version = 9L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p9 = productRepo.findById(21L).get();
            Rating rating = ratingRepo.findByRate(1.5).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p9, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(10L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 10L;
            long version = 10L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p10 = productRepo.findById(20L).get();
            Rating rating = ratingRepo.findByRate(2.0).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p10, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(11L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 11L;
            long version = 11L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p11 = productRepo.findById(19L).get();
            Rating rating = ratingRepo.findByRate(2.5).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p11, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(12L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 12L;
            long version = 12L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p12 = productRepo.findById(18L).get();
            Rating rating = ratingRepo.findByRate(3.0).get();
            User user = userRepo.getById(5L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p12, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(13L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 13L;
            long version = 13L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p13 = productRepo.findById(17L).get();
            Rating rating = ratingRepo.findByRate(0.5).get();
            User user = userRepo.getById(10L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p13, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(14L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 18; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 14L;
            long version = 14L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p14 = productRepo.findById(16L).get();
            Rating rating = ratingRepo.findByRate(1.0).get();
            User user = userRepo.getById(10L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p14, rating, user);
            reviewRepo.save(review5);
        }

        if (reviewRepo.findById(15L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("upvote", n);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 21; i++) {
                Random rand = new Random();
                long n = rand.nextInt(13) + 1;
                Vote v = new Vote("downvote", n);
                downvote.add(v);
            }


            Long idReview = 15L;
            long version = 15L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p15 = productRepo.findById(15L).get();
            Rating rating = ratingRepo.findByRate(2.0).get();
            User user = userRepo.getById(10L);
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p15, rating, user);
            reviewRepo.save(review5);
        }

        // Continuation from previous code
        for (long j = 16L; j <= 45L; j++) {
            if (reviewRepo.findById(j).isEmpty()) {
                List<Vote> upvote = new ArrayList<>();
                List<Vote> downvote = new ArrayList<>();
                Random rand = new Random();
                int upvoteCount = rand.nextInt(25); // Random number of upvotes
                int downvoteCount = rand.nextInt(25); // Random number of downvotes

                for (int i = 0; i < upvoteCount; i++) {
                    long n = rand.nextInt(13) + 1;
                    Vote v = new Vote("upvote", n);
                    upvote.add(v);
                }

                for (int i = 0; i < downvoteCount; i++) {
                    long n = rand.nextInt(13) + 1;
                    Vote v = new Vote("downvote", n);
                    downvote.add(v);
                }

                Long idReview = j;
                long version = j;
                String[] statuses = {"Approved", "Pending", "Rejected"};
                String approvalStatus = statuses[rand.nextInt(statuses.length)];
                String reviewText = "Review text for " + j;
                String report = (rand.nextBoolean()) ? "Reported" : "No issues";
                LocalDate publishingDate = LocalDate.of(2023, rand.nextInt(12) + 1, rand.nextInt(28) + 1);
                String funFact = "Fun fact for review " + j;
                Product product = productRepo.findById((long)(rand.nextInt(12) + 14)).get();
                Double[] ratings = {0.5, 1.0, 1.5 , 2.0, 2.5, 3.0};
                Rating rating = ratingRepo.findByRate(ratings[rand.nextInt(ratings.length)]).get();
                User user = userRepo.getById((long)rand.nextInt(10) + 1);

                Review review = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, product, rating, user);
                reviewRepo.save(review);
            }
        }

    }
}
