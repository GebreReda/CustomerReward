package com.customer.reward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        // You can set up any test data or configuration needed before each test
    }

    @Test
    public void testGetRewardPointsForCustomer() {
        String customerId = "1";
        // Prepare the request URL and headers, if needed
        String url = "http://localhost:" + port + "/transactions/" + customerId + "/reward-points";

        // Make the HTTP GET request
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);

        // Verify the HTTP status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify the response body or any other expectations
        Map<String, Integer> rewardPointsPerMonth = responseEntity.getBody();
        // Add more assertions based on your expectations
    }

    @Test
    public void testGetTotalRewardPoints() {
        // Prepare the request URL and headers, if needed
        String url = "http://localhost:" + port + "/transactions/reward-points";

        // Make the HTTP GET request
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);

        // Verify the HTTP status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify the response body or any other expectations
        Map<String, Integer> totalRewardPoints = responseEntity.getBody();
        // Add more assertions based on your expectations
    }

    // Add more integration tests for other endpoints or scenarios
}