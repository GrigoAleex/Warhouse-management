package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesRandomNumber;

import java.util.Random;

public class GenerateRandomNumberGaussianDistribution implements GeneratesRandomNumber {

    @Override
    public Integer handle(Integer upperBound, Integer lowerBound, Integer standardDeviation, Integer mean) {
        Random random = new Random();
        double randomNumber;

        do {
            randomNumber = random.nextGaussian() * standardDeviation + mean;
        } while (randomNumber < lowerBound || randomNumber > upperBound);

        return (int) randomNumber;
    }
}
