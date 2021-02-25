package com.anz.demo.controller;

import com.anz.demo.dto.Account;
import com.anz.demo.dto.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAccounts() {
        ParameterizedTypeReference<List<Account>> responseType = new ParameterizedTypeReference<List<Account>>() {
        };
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Account>> response =
                restTemplate.exchange(baseUrl + port + "/accounts", HttpMethod.GET, httpEntity, responseType);

        assertTrue(response.getBody().size() == 5);
    }

    @Test
    public void getTransactions() {
        ParameterizedTypeReference<List<Transaction>> responseType = new ParameterizedTypeReference<List<Transaction>>() {
        };
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<List<Transaction>> response =
                restTemplate.exchange(baseUrl + port + "/transactions/887878701", HttpMethod.GET, httpEntity, responseType);

        assertTrue(response.getBody().size() == 3);
    }
}