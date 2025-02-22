package com.elisporter.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    private final Random random = new Random();

    public boolean isFraudulentCustomer(Integer customerId) {

        //Could implement further checks to a 3rd party here
        boolean isFraudster = random.nextBoolean();

        fraudCheckHistoryRepository.save(FraudCheckHistory
                .builder()
                .customerId(customerId)
                .isFraudster(isFraudster)
                .createdAt(LocalDateTime.now())
                .build());


        return isFraudster;
    }
}
