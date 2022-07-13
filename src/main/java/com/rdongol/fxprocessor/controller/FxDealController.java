package com.rdongol.fxprocessor.controller;

import com.rdongol.fxprocessor.model.FxDeal;
import com.rdongol.fxprocessor.service.FxDealProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/fxDeal")
public class FxDealController {

    private FxDealProcessor fxDealProcessor;

    public FxDealController(FxDealProcessor fxDealProcessor) {
        this.fxDealProcessor = fxDealProcessor;
    }

    @PostMapping
    public ResponseEntity<FxDeal> add(@RequestBody FxDeal fxDeal) {
        return ResponseEntity.ok(fxDealProcessor.processFxDeal(fxDeal));
    }
}
