package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/")
    public List<StockDto> getAllStock() {
//        return stockService.findAllStock();
        return null;
    }
}
