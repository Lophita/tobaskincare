package com.lophita.tobaskincare.rest;

import com.lophita.tobaskincare.dto.StockDto;
import com.lophita.tobaskincare.service.StockService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Stock")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String custom() {
        return "custom";
    }

    @GetMapping("/")
    public List<StockDto> getAllStock() {
//        return stockService.findAllStock();
        return new ArrayList<>();
    }
}
