package com.customer.reward.service;

import com.customer.reward.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService {

    public int calculateRewardPoints(double amount) {
        double over100 = Math.max(amount - 100, 0); // Calculate dollars spent over $100
        double over50 = Math.max(amount - 50, 0);   // Calculate dollars spent over $50

        int pointsOver100 = (int) (over100 * 2);   // Earn 2 points for each dollar over $100
        int pointsOver50 = (int) (over50);        // Earn 1 point for each dollar over $50

        return pointsOver100 + pointsOver50;
    }

    public Map<String, Map<String, Integer>> calculateRewardPointsPerMonth(List<Transaction> transactions) {
        Map<String, Map<String, Integer>> rewardPointsPerMonth = new HashMap<>();

        for (Transaction transaction : transactions) {
            String customerId = transaction.getCustomerId();
            String month = transaction.getDate().getMonth().toString();

            int rewardPoints = calculateRewardPoints(transaction.getAmount());

            // Update the rewardPointsPerMonth map
            rewardPointsPerMonth.computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(month, rewardPoints, Integer::sum);
        }

        return rewardPointsPerMonth;
    }

    public Map<String, Integer> calculateTotalRewardPoints(Map<String, Map<String, Integer>> rewardPointsPerMonth) {
        Map<String, Integer> totalRewardPoints = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : rewardPointsPerMonth.entrySet()) {
            String customerId = entry.getKey();
            int totalPoints = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
            totalRewardPoints.put(customerId, totalPoints);
        }

        return totalRewardPoints;
    }
}
