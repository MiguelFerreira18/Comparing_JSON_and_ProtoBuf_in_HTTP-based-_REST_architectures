package com.isep.acme.generators.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.H2Entity.Vote;
import com.isep.acme.protobuf.ReviewDTOOuterClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PercentageReviewGeneratorImpl implements ReviewRecomendationGenerator {

    @Transactional
    @Override
    public ReviewDTOOuterClass.ReviewCatalog generateReviewRecomendations(Long userIdOptional, Optional<List<User>> usersOptionalList, Optional<List<Review>> reviewsOptionalList) {
        Long userId = userIdOptional;
        List<User> users = usersOptionalList.get();
        List<Review> reviewsList = reviewsOptionalList.get();
        double bestMatchPercentage = 0.0;
        List<Review> recommendations = new ArrayList<>();
        for (User user : users) {
            if (user.getUserId() != userId) {

                double matchPercentage = calculateMatchPercentage(userId, user, reviewsList);
                if (matchPercentage >= 50 && matchPercentage > bestMatchPercentage) {

                    for (Review review : reviewsList) {
                        if (review.getUser().getUsername().equals(user.getUsername()) && review.getUser().getFullName().equals(user.getFullName())) {
                            System.out.println("review:" + review.getReviewText() + "\n");
                            recommendations.add(review);
                        }
                    }
                }
            }
        }


        List<ReviewDTOOuterClass.ReviewDTO> reviews = new ArrayList<>();
        for (Review review : recommendations) {
            reviews.add(review.toDTO());
        }

        return ReviewDTOOuterClass.ReviewCatalog.newBuilder().addAllReviews(reviews).build();
    }


    private double calculateMatchPercentage(Long myUserId, User secondUser, List<Review> reviewsList) {
        int commonVotes = 0;
        int myUserVotes = 0;
        int secondUserVotes = 0;
        for (Review review : reviewsList) {//Todas as reviews
            boolean hasFoundMyUser = false;
            boolean hasFoundSecondUser = false;
            //DownVotes
            for (Vote downVote : review.getDownVote()) {
                //!Does the User comments only 24

                if (downVote.getUserID().equals(myUserId)) {
                    myUserVotes++;
                    hasFoundMyUser = true;
                } else if (downVote.getUserID().equals(secondUser.getUserId())) {
                    secondUserVotes++;
                    hasFoundSecondUser = true;
                }
                if (hasFoundMyUser && hasFoundSecondUser) {//Does common votes
                    commonVotes++;
                }
            }
            //UpVotes
            for (Vote upVote : review.getUpVote()) {
                if (upVote.getUserID().equals(myUserId)) {
                    myUserVotes++;
                    hasFoundMyUser = true;
                } else if (upVote.getUserID().equals(secondUser.getUserId())) {//
                    secondUserVotes++;
                    hasFoundSecondUser = true;
                }
                if (hasFoundMyUser && hasFoundSecondUser) {//Does common votes
                    commonVotes++;
                }
            }
        }
        double matchPercentage = (double) commonVotes / Math.max(myUserVotes, secondUserVotes) * 100.0;
        return matchPercentage;
    }
}
