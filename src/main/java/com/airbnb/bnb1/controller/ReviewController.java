package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.entity.Review;
import com.airbnb.bnb1.repository.PropertyRepository;
import com.airbnb.bnb1.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @RequestMapping("/createReview")
    public ResponseEntity<?> CreateReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser user,
            @RequestParam long propertyId) {

        Property property = propertyRepository.findById(propertyId).get();
        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);
        if(reviewDetails != null){
            return new ResponseEntity<>("Review Exist", HttpStatus.CREATED);
        }
        review.setAppUser(user);
        review.setProperty(property);
        Review save = reviewRepository.save(review);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @RequestMapping("/userReviews")
    public List<Review> listReviewsOfUser(
            @AuthenticationPrincipal AppUser user
    ){
        List<Review> reviews = reviewRepository.findReviewsByUser(user);
        return reviews;
    }

}
