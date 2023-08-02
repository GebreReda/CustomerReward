package com.customer.reward.controller;

import com.customer.reward.model.Transaction;
import com.customer.reward.repository.TransactionRepository;
import com.customer.reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final RewardService rewardService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(RewardService rewardService, TransactionRepository transactionRepository) {
        this.rewardService = rewardService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{customerId}/reward-points")
    public ResponseEntity<Map<String, Integer>> getRewardPointsForCustomer(@PathVariable String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        Map<String, Integer> rewardPointsPerMonth = rewardService.calculateRewardPointsPerMonth(transactions).get(customerId);

        if (rewardPointsPerMonth == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rewardPointsPerMonth);
    }

    @GetMapping("/reward-points")
    public ResponseEntity<Map<String, Integer>> getTotalRewardPoints() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        Map<String, Integer> totalRewardPoints = rewardService.calculateTotalRewardPoints(rewardService.calculateRewardPointsPerMonth(allTransactions));

        return ResponseEntity.ok(totalRewardPoints);
    }
}