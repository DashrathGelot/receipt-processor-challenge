package com.fetch.rewards.receiptprocessorchallenge;

import com.fetch.rewards.receiptprocessorchallenge.rest.ReceiptController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReceiptProcessorChallengeApplicationTests {

    @Autowired
    ReceiptController receiptController;

    @Test
    void contextLoads() {

    }

}
