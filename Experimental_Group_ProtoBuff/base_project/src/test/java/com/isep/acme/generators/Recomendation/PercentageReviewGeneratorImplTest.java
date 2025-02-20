package com.isep.acme.generators.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.*;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import com.isep.acme.repositories.h2Repos.Repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PercentageReviewGeneratorImplTest {

    @InjectMocks
    private PercentageReviewGeneratorImpl generator; // Use InjectMocks to inject dependencies

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder; // Mocked PasswordEncoder

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this); // Initialize mocks
//    }
//
//    @Test
//    void testGenerateReviewRecomendations_UserExistsWithBestMatch_ReturnsReviews() {
//        // Arrange
//        Long userId = 1L;
//        User targetUser = new User("admin1@mail.com", encoder.encode("AdminPW1"),
//                "Jose Antonio", "355489123", "Rua Um");
//        User bestMatch = new User("admin2@mail.com", encoder.encode("AdminPW2"),
//                "Antonio Jose", "321984553", "Rua dois");
//
//        List<Vote> upvote = new ArrayList<>();
//        for (int i = 0; i < 23; i++) {
//            Vote v = new Vote("upvote", (long) 2.0);
//            upvote.add(v);
//        }
//        List<Vote> downvote = new ArrayList<>();
//        for (int i = 0; i <15; i++) {
//            Vote v = new Vote("downvote", (long) 3.0);
//            downvote.add(v);
//        }
//
//        Long idReview = 1L;
//        long version = 1L;
//        String approvalStatus = "Approved";
//        String reviewText = "This is a review";
//        String report = "No report";
//        LocalDate publishingDate = LocalDate.now();
//        String funFact = "Fun fact";
//        Rating rating = new Rating(0.5);
//        Product p1 = new Product("asd578fgh267", "Pen", "very good nice product");
//        Product p2 = new Product("c1d4e7r8d5f2", "Pencil", " writes ");
//
//        Review review1 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p1, rating, targetUser);
//        Review review2 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p2, rating, bestMatch);
//
//        List<Review> recommendations = Arrays.asList(review1, review2);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(targetUser));
//        when(userRepository.findAll()).thenReturn(Arrays.asList(targetUser, bestMatch));
//        when(reviewRepository.findByUserId(bestMatch)).thenReturn(Optional.of(recommendations));
//
//        // Act
//        PercentageReviewGeneratorImpl generator = new PercentageReviewGeneratorImpl();
//        List<ReviewDTO> result = generator.generateReviewRecomendations();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Review 1", result.get(0).getReviewText());
//        assertEquals("Review 2", result.get(1).getReviewText());
//    }
//
//    @Test
//    public void testGenerateReviewRecomendations_UserDoesNotExist_ReturnsNull() {
//        // Arrange
//        Long userId = 1L;
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        // Act
//        PercentageReviewGeneratorImpl generator = new PercentageReviewGeneratorImpl();
//        List<ReviewDTO> result = generator.generateReviewRecomendations(userId);
//
//
//
//        // Assert
//            assertNull(result);
//        }
//
//    @Test
//    public void testGenerateReviewRecomendations_NoBestMatch_ReturnsNull() {
//        // Arrange
//        Long userId = 1L;
//        User targetUser = new User("admin1@mail.com", encoder.encode("AdminPW1"),
//                "Jose Antonio", "355489123", "Rua Um");
//        when(userRepository.findById(userId)).thenReturn(Optional.of(targetUser));
//        when(userRepository.findAll()).thenReturn(Collections.singletonList(targetUser));
//
//        // Act
//        PercentageReviewGeneratorImpl generator = new PercentageReviewGeneratorImpl();
//        List<ReviewDTO> result = generator.generateReviewRecomendations(userId);
//
//        // Assert
//        assertNull(result);
//    }
}