package com.kim9fe.test.multiplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kim9fe.test.multiplication.domain.MultiplicationResultAttempt;
import com.kim9fe.test.multiplication.service.MultiplicationService;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;

@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {
    
    private MultiplicationService multiplicationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MultiplicationResultAttemptController(final MultiplicationService multiplicationService){
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    public MultiplicationResultAttempt postResult(@RequestBody MultiplicationResultAttempt attempt) {

        ObjectMapper oMapper = new ObjectMapper();

        logger.info("-----------------------------------------------");

        try{
            //System.out.println(oMapper.writeValueAsString(attempt));
            logger.info("1:" + oMapper.writeValueAsString(attempt));
            //System.out.println(new Gson().toJson(attempt));
            logger.info("2:" + new Gson().toJson(attempt));
        } catch(JsonProcessingException ex){
            System.out.println(ex.getMessage());
        }

        logger.info("-----------------------------------------------");

        Boolean correct = multiplicationService.checkAttempt(attempt);

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt
            (attempt.getUser()
            , attempt.getMultiplication()
            , attempt.getResultAttemp()
            , correct);

        return checkedAttempt;
    }

    @Getter
    @RequiredArgsConstructor
    public static class ResultResponse{
        private final boolean correct;
    }
}


