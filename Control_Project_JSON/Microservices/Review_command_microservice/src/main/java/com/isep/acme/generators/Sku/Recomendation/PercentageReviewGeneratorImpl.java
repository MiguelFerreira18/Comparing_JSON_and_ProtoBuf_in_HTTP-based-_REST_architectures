package com.isep.acme.generators.Sku.Recomendation;

import com.isep.acme.Dto.ReviewDTO;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import com.isep.acme.model.H2Entity.Vote;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PercentageReviewGeneratorImpl implements ReviewRecomendationGenerator {

    @Transactional
    @Override
    public List<ReviewDTO> generateReviewRecomendations(Long userIdOptional, Optional<List<User>> usersOptionalList, Optional<List<Review>> reviewsOptionalList) {
        Long userId = userIdOptional;
        System.out.println("myId:" + userId + "\n");
        List<User> users = usersOptionalList.get();
        System.out.println("users:" + users.size() + "\n");
        List<Review> reviewsList = reviewsOptionalList.get();
        System.out.println("reviews:" + reviewsList.size() + "\n");
        User bestMatch = null;
        double bestMatchPercentage = 0.0;
        List<Review> recommendations = new ArrayList<>();
        for (User user : users) { //All users
            if (user.getUserId() != userId) {

                double matchPercentage = calculateMatchPercentage(userId, user, reviewsList);//Recebe a lista de Reviews
                System.out.println("matchPercentage:" + matchPercentage + "\n");
                if (matchPercentage >= 50 && matchPercentage > bestMatchPercentage) {

                    for (Review review : reviewsList) {
                        if (review.getUser().getUsername().equals(user.getUsername())  && review.getUser().getFullName().equals(user.getFullName()) ) {
                            System.out.println("review:" + review.getReviewText() + "\n");
                            recommendations.add(review);
                        }
                    }
                }
            }
        }

//        if (bestMatch != null) {

//            //Vai buscar as reviews do utilizador bestmatch que n\ao sejam iguais à do utilizador
//            for (Review review : reviewsList) {
//                if (review.getUser().getUserId() == bestMatch.getUserId()) {
//                    recommendations.add(review);
//                }
//            }
//            //Remover os que o utilizador já votou
//            for (Review review : recommendations) {
//                System.out.println(review.getReviewText() + "\n");
//            }

            List<ReviewDTO> reviews = new ArrayList<>();
            for (Review review : recommendations) {
                reviews.add(review.toDTO());
            }

            return reviews;
//        } else {
//            // Handle the case when there is no good match
//            return null;
//        }
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
                } else if (downVote.getUserID().equals(secondUser.getUserId())) {//
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
