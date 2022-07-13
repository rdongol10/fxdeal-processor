package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.exceptionHandler.exception.AppException;
import com.rdongol.fxprocessor.model.FxDeal;
import com.rdongol.fxprocessor.utils.ErrorMessages;
import org.springframework.stereotype.Service;

@Service
public class FxDealProcessor {
    private FxDealService fxDealService;

    public FxDealProcessor(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    public FxDeal processFxDeal(FxDeal fxDeal) throws AppException {

        this.validateFxDeal(fxDeal);
        if (fxDealService.existsByDealId(fxDeal.getDealId())) {
            throw new AppException(ErrorMessages.FX_DEAL_EXISTS.getErrorMessage() + " " + fxDeal.getDealId());
        }

        return fxDealService.addFxDeal(fxDeal);
    }

    private void validateFxDeal(FxDeal fxDeal) throws AppException {

        fxDeal.validate();

    }
}
