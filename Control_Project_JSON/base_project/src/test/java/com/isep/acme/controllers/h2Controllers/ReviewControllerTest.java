package com.isep.acme.controllers.h2Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.services.ReviewService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to serialize and deserialize JSON

    @Autowired
    private PasswordEncoder encoder;


    @BeforeAll
    static void setUp() {

    }

    @Test
    void testFindReviewByUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    void testCreateReview() throws Exception {
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
//        Long idReview = 7L;
//        long version = 4L;
//        String approvalStatus = "Approved";
//        String reviewText = "Updated review with new insights";
//        String report = "No issues";
//        LocalDate publishingDate = LocalDate.now();
//        String funFact = "Surprising fact";
//        Product p4 = new Product("v145dc2365sa", "Wallet", "stores money");
//        Rating rating = new Rating(0.9);
//        User user = new User(26L, "user2@mail.com", encoder.encode("userPW2"),
//                "Pedro Antonio", "452369871", "Rua cinco");
//
//        Review review4 = new Review(idReview, version, approvalStatus, reviewText, upvote, downvote, report, publishingDate, funFact, p4, rating, user);
//
//        String requestJson = objectMapper.writeValueAsString(review4);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/products/" + "asd578fgh267" + "/reviews")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//
//    @Test
//    void testAddVote() throws Exception {
//        Long reviewId = 5L;
//        VoteReviewDTO voteDTO = new VoteReviewDTO(23L, "upvote");
//
//        String requestJson = objectMapper.writeValueAsString(voteDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/reviews/" + reviewId + "/vote")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }

    @Test
    void testDeleteReview() throws Exception {
        Long reviewId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/reviews/" + reviewId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPendingReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reviews/pending")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPutAcceptRejectReview() throws Exception {
        Long reviewId = 1L;
        String approvalStatus = "approved"; // Replace with your test data

        mockMvc.perform(MockMvcRequestBuilders.put("/reviews/acceptreject/" + reviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(approvalStatus))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}