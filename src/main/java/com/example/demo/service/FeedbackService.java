package com.example.demo.service;

import com.example.demo.models.Feedback;
import com.example.demo.repository.FeedbackRepository;

import java.io.IOException;
import java.util.List;

public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService() throws IOException {
        this.feedbackRepository=new FeedbackRepository();
    }

    public void addFeedback(int hotelId, String feedback) throws IOException {
        Feedback feedback1 =  new Feedback(hotelId,feedback);
        feedbackRepository.addFeedback(feedback1);
    }
    public List<Feedback> getAllFeedbacks(){
        return feedbackRepository.getFeedbacks();
    }

    public List<Feedback> getAllFeedbacksByHotelId(int hotelId){
        return  feedbackRepository.getFeedbacksByIdHotel(hotelId);
    }
}
