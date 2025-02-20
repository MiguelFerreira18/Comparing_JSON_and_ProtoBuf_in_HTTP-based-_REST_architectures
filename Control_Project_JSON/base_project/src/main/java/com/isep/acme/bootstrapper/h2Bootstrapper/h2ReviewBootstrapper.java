package com.isep.acme.bootstrapper.h2Bootstrapper;

import com.isep.acme.model.H2Entity.*;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Profile("h2Profile")//h2Profile
@Component
public class h2ReviewBootstrapper {

    @Autowired
    private ReviewRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public void run(String... args) throws Exception {
        if (repo.findById(1L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 23; i++) {
                Vote v = new Vote("upvote", (long) 2.0);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                Vote v = new Vote("downvote", (long) 3.0);
                downvote.add(v);
            }

            Long idReview = 1L;
            long version = 1L;
            String approvalStatus = "Approved";
            String reviewText = "Not that fun";
            String report = "dont report";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Fun fact";
            Product p1 = new Product("asd578fgh267", "Pen", "very good nice product");
            Rating rating = new Rating(0.5);
            User user = new User(26L, "user2@mail.com", encoder.encode("userPW2"),
                    "Pedro Antonio", "452369871", "Rua cinco");
            Review review1 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p1, rating, user);
            System.out.println("Review: " + review1.getReviewText());
            repo.save(review1);
        }

        if (repo.findById(2L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                Vote v = new Vote("upvote", (long) 2.0);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Vote v = new Vote("downvote", (long) 3.0);
                downvote.add(v);
            }

            Long idReview = 2L;
            long version = 2L;
            String approvalStatus = "Approved";
            String reviewText = "Another review";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Interesting fact";
            Product p2 = new Product("c1d4e7r8d5f2", "Pencil", " writes ");
            Rating rating = new Rating(0.8);
            User user = new User(23L, "admin1@mail.com", encoder.encode("AdminPW1"),
                    "Antonio Jose", "321984553", "Rua dois");
            Review review2 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p2, rating, user);
            System.out.println("Review: " + review2.getReviewText());
            repo.save(review2);
        }
        if (repo.findById(3L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Vote v = new Vote("upvote", (long) 2.0);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Vote v = new Vote("downvote", (long) 3.0);
                downvote.add(v);
            }

            Long idReview = 3L;
            long version = 3L;
            String approvalStatus = "Rejected";
            String reviewText = "A different review";
            String report = "Reported";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Fascinating fact";
            Product p3 = new Product("c4d4f1v2f5v3", "Rubber", "erases");
            Rating rating = new Rating(0.7);
            User user = new User(23L, "admin1@mail.com", encoder.encode("AdminPW1"),
                    "Antonio Jose", "321984553", "Rua dois");
            Review review3 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p3, rating, user);
            System.out.println("Review: " + review3.getReviewText());
            repo.save(review3);
        }

        if (repo.findById(4L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Vote v = new Vote("upvote", (long) 2.0);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                Vote v = new Vote("downvote", (long) 3.0);
                downvote.add(v);
            }

            Long idReview = 4L;
            long version = 4L;
            String approvalStatus = "Approved";
            String reviewText = "Updated review with new insights";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.now();
            String funFact = "Surprising fact";
            Product p4 = new Product("v145dc2365sa", "Wallet", "stores money");
            Rating rating = new Rating(0.9);
            User user = new User(26L, "user2@mail.com", encoder.encode("userPW2"),
                    "Pedro Antonio", "452369871", "Rua cinco");
            Review review4 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p4, rating, user);
            System.out.println("Review: " + review4.getReviewText());
            repo.save(review4);
        }

        if (repo.findById(5L).isEmpty()) {
            List<Vote> upvote = new ArrayList<>();
            for (int i = 0; i < 18; i++) {
                Vote v = new Vote("upvote", (long) 2.0);
                upvote.add(v);
            }
            List<Vote> downvote = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                Vote v = new Vote("downvote", (long) 3.0);
                downvote.add(v);
            }

            Long idReview = 5L;
            long version = 5L;
            String approvalStatus = "Pending";
            String reviewText = "Fifth review for testing";
            String report = "No issues";
            LocalDate publishingDate = LocalDate.of(2023, 10, 30); // Altere a data conforme necessário
            String funFact = "Interesting detail";
            Product p5 = new Product("fg54vc14tr78", "pencil case", " stores pencils");
            Rating rating = new Rating(0.6);
            User user = new User(27L, "user3@mail.com", encoder.encode("userPW3"),
                    "Ricardo Joao", "452858596", "Rua seis");
            Review review5 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p5, rating, user);
            System.out.println("Review: " + review5.getReviewText());
            repo.save(review5);
        }

    }

}
