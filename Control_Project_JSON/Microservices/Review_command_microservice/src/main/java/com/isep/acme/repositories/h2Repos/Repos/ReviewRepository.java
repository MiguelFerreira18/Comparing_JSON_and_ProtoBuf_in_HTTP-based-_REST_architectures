package com.isep.acme.repositories.h2Repos.Repos;

import com.isep.acme.model.H2Entity.Product;
import com.isep.acme.model.H2Entity.Review;
import com.isep.acme.model.H2Entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {


    @Query("SELECT r FROM Review r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByProductId(Product product);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='pending'")
    Optional<List<Review>> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE LOWER(r.approvalStatus) LIKE '%approved%'")
    Optional<List<Review>> findActiveReviews();

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    @Query("SELECT r FROM Review r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByUserId(User user);


    @Query("SELECT r FROM Review r WHERE size(r.upVote) > 4 AND size(r.upVote) > ((size(r.downVote) + size(r.upVote)) * 0.6)")
    Optional<List<Review>> findTopReviews();

    @Modifying
    @Query("UPDATE Review r SET r.numberApproved = r.numberApproved + 1 WHERE r.idReview = :idReview")
    @Transactional
    void updatePublish(@Param("idReview") Long idReview);

    @Modifying
    @Query("UPDATE Review r SET r.numberApproved = r.numberApproved - 1 WHERE r.idReview = :idReview")
    @Transactional
    void updateUnpublish(@Param("idReview") Long idReview);

    @Query("SELECT r FROM Review r WHERE r.numberApproved > 1")
    Optional<List<Review>> findPublishedReviews();

    @Query("SELECT r FROM Review r WHERE r.idReview=:reviewId")
    Optional<Review> findById(Long reviewId);
}
