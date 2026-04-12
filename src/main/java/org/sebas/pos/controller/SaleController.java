package org.sebas.pos.controller;

import org.sebas.pos.model.SaleItem;
import org.sebas.pos.model.Sales;
import org.sebas.pos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping()
    public ResponseEntity<Sales> makeSale(@RequestBody List<SaleItem> products){
        Sales sale = saleService.makeSale(products);
        System.out.println(products);
        return new ResponseEntity<>(sale, HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<?> getSalesHistory() {
        List<Sales> sales = saleService.getSalesHistory();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}
