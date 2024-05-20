package com.example.demo.repository;

import com.example.demo.models.Feedback;
import com.example.demo.models.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepository {
    private static final String FILE_PATH = "/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/feedbacks.json";
    private final List<Feedback> feedbacks;

    public FeedbackRepository() throws IOException {
        this.feedbacks = readFromFile();
    }

    public void saveFeedbacks() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(FILE_PATH), feedbacks);
    }

    private static List<Feedback> readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() == 0) {
            return new ArrayList<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(file, new TypeReference<List<Feedback>>() {});
    }

    public void addFeedback(Feedback feedback) throws IOException {
        feedback.setId(getNextId());
        feedbacks.add(feedback);
        saveFeedbacks();
    }

    public int getNextId(){
        return feedbacks.size()+1;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public List<Feedback> getFeedbacksByIdHotel(int hotelId){
        List<Feedback> feedbackList = new ArrayList<>();
        for(Feedback feedback: feedbacks){
            if(feedback.getHotelId() == hotelId){
                feedbackList.add(feedback);
            }
        }
        return feedbackList;
    }
}
