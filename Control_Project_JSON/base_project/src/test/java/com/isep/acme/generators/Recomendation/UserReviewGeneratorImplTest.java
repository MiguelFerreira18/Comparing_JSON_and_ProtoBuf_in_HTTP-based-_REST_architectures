package com.isep.acme.generators.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.Vote;
import com.isep.acme.repositories.h2Repos.Repos.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Profile("h2Profile")
class UserReviewGeneratorImplTest {

    @InjectMocks
    private UserReviewGeneratorImpl userReviewGenerator;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateReviewRecomendations() {
        // Arrange
        List<Review> expectedReviews = createSampleReviewList(); // Replace with actual test data
        Mockito.when(reviewRepository.findTopReviews()).thenReturn(Optional.of(expectedReviews));
        System.out.print("test expectedReviews: " + expectedReviews.size());
        // Act
        List<ReviewDTO> actualReviews = userReviewGenerator.generateReviewRecomendations(1L,Optional.empty(),Optional.empty());

        // Assert
        assertFalse(actualReviews.isEmpty());
        assertEquals(expectedReviews.size(), actualReviews.size());
    }

    @Test
    public void testGenerateReviewRecomendationsWithNoTopReviews() {
        // Arrange
        Mockito.when(reviewRepository.findTopReviews()).thenReturn(Optional.empty());

        // Act
        List<ReviewDTO> actualReviews = userReviewGenerator.generateReviewRecomendations(2L,Optional.empty(),Optional.empty());

        // Assert
        assertEquals(0, actualReviews.size());
    }

    // Helper method to create a sample list of reviews for testing
    private List<Review> createSampleReviewList() {
        // Implement this method to create a list of reviews for testing
        Vote vote1 = new Vote("upVote", 1l);
        Vote vote2 = new Vote("downVote", 2l);
        Vote vote3 = new Vote("upVote", 3l);
        Vote vote4 = new Vote("downVote", 4l);
        Vote vote5 = new Vote("upVote", 5l);
        Vote vote6 = new Vote("upVote", 1l);
        Vote vote7 = new Vote("upVote", 1l);
        Vote vote8 = new Vote("upVote", 1l);
        Vote vote9 = new Vote("upVote", 1l);
        Vote vote10 = new Vote("downVote", 4l);
        Vote vote11 = new Vote("downVote", 4l);

        List<Vote> downVote = List.of(vote2, vote4, vote10, vote11);
        List<Vote> upVote = List.of(vote1, vote3, vote5, vote6, vote7, vote8, vote9);
        Review review1 = new Review(1l, 1, "approved", "reviewText1", upVote, downVote, "report", LocalDate.now(),  null, null, null);

        List<Vote> downVote1 = List.of(vote2, vote4);
        List<Vote> upVote1 = List.of(vote1, vote3, vote5, vote6);

        Review review2 = new Review(2l, 1, "approved", "reviewText2", upVote1, downVote1, "report", LocalDate.now(),  null, null, null);

        List<Vote> downVote2 = List.of(vote2, vote4);
        List<Vote> upVote2 = List.of(vote1, vote3, vote5, vote6, vote7, vote8, vote9);

        Review review3 = new Review(3l, 1, "approved", "reviewText3", upVote2, downVote2, "report", LocalDate.now(), null, null, null);

        List<Vote> downVote3 = List.of(vote2, vote4, vote10, vote11);
        List<Vote> upVote3 = List.of(vote1, vote3, vote5, vote6, vote7);

        Review review4 = new Review(4l, 1, "approved", "reviewText4", upVote3, downVote3, "report", LocalDate.now(), null, null, null);

        return List.of(review1, review2, review3, review4); // Replace with actual data
    }
}
