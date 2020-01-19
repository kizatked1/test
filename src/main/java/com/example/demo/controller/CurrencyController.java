package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Currency;
import com.example.demo.service.CurrencyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/")
    public ResponseEntity<CurrencyResponse> findAll() {
        List<Currency> currencies = currencyService.findAll();
        CurrencyResponse currencyResponse = CurrencyResponse.builder()
                .currencies(currencies)
                .build();
        return new ResponseEntity<>(currencyResponse, HttpStatus.OK);
    }
    @GetMapping("/find/")
    public ResponseEntity<CurrencyResponse> find() {
        Optional<Currency> currencie = currencyService.find((long) 10001);
        List<Currency> currencies = new ArrayList<>();
        currencie.ifPresent(currencies::add);
        CurrencyResponse currencyResponse = CurrencyResponse.builder()
                .currencies(currencies)
                .build();

        return new ResponseEntity<>(currencyResponse, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<HttpStatus> save(@RequestBody CurrencyAddRequest request) {
        currencyService.save(request.getName(), request.getSymbol());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        currencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}