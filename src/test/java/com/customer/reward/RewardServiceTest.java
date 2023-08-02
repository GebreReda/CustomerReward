package com.customer.reward;

import com.customer.reward.service.RewardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RewardServiceTest {
    private RewardService rewardService;
    @BeforeEach
    public void setup() {
        rewardService = new RewardService();
    }
    @Test
    public void testCalculateRewardPoints() {
        double amount1 = 120.0;
        double amount2 = 70.0;
        int expectedPoints1 = 110;
        int expectedPoints2 = 20;
        int actualPoints1 = rewardService.calculateRewardPoints(amount1);
        int actualPoints2 = rewardService.calculateRewardPoints(amount2);
        Assertions.assertEquals(expectedPoints1, actualPoints1);
        Assertions.assertEquals(expectedPoints2, actualPoints2);
    }
    @Test
    public void testCalculateRewardPointsWithZeroAmount() {
        double amount = 0.0;
        int expectedPoints = 0;
        int actualPoints = rewardService.calculateRewardPoints(amount);
        Assertions.assertEquals(expectedPoints, actualPoints);
    }
    // Add more test cases for edge cases and other scenarios.
}
