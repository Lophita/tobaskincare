package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.TransactionDto;
import com.lophita.tobaskincare.persistence.Transaction;
import com.lophita.tobaskincare.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public List<TransactionDto> getAllTransaction() {
        List<Transaction> list = transactionService.findAll();
        List<TransactionDto> transactionDtoList =
                list.stream().map(transaction -> {
                    return TransactionDto.builder()
                            .id(transaction.getId())
                            .identifier(transaction.getIdentifier())
                            .name(transaction.getName())
                            .notes(transaction.getNotes())
                            .expeditionTime(transaction.getExpeditionTime())
                            .createdTime(transaction.getCreatedTime())
                            .price(transaction.getPrice())
                            .type(transaction.getType())
                            .username(transaction.getUsername())
                            .trxTime(transaction.getTrxTime())
                            .build();
                }).collect(Collectors.toList());
        return transactionDtoList;
    }
}
