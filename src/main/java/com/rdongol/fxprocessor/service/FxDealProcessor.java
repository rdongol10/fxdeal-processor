package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.model.FxDeal;
import com.rdongol.fxprocessor.utils.ErrorMessages;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FxDealProcessor {
    private FxDealService fxDealService;

    public FxDealProcessor(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    protected static final Logger LOGGER = Logger.getLogger(FxDealProcessor.class.getName());

    public long processFxDeal(FxDeal fxDeal) throws Exception {

        this.validateFxDeal(fxDeal);
        if (fxDealService.existsByDealId(fxDeal.getDealId())) {
            LOGGER.log(Level.SEVERE, ErrorMessages.FX_DEAL_EXISTS.getErrorMessage() + " " + fxDeal.getDealId());
            throw new EntityExistsException(ErrorMessages.FX_DEAL_EXISTS.getErrorMessage() + " " + fxDeal.getDealId());
        }

        return fxDealService.addFxDeal(fxDeal).getId();
    }

    private void validateFxDeal(FxDeal fxDeal) throws Exception {

        fxDeal.validate();

    }
}
