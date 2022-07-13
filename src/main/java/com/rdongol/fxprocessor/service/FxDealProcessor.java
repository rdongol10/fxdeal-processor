package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.model.FxDeal;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class FxDealProcessor {
    private FxDealService fxDealService;

    public FxDealProcessor(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    public long processFxDeal(FxDeal fxDeal) throws Exception {

        this.validateFxDeal(fxDeal);
        if (fxDealService.existsByDealId(fxDeal.getDealId())) {
            throw new EntityExistsException("Fx deal " + fxDeal.getDealId() + " already exists");
        }


        return fxDealService.addFxDeal(fxDeal).getId();
    }

    private void validateFxDeal(FxDeal fxDeal) throws Exception {

        fxDeal.validate();

    }
}
