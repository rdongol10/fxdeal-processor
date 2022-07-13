package com.rdongol.fxprocessor.service;

import com.rdongol.fxprocessor.model.FxDeal;
import com.rdongol.fxprocessor.repository.FxDealRepository;
import org.springframework.stereotype.Service;

@Service
public class FxDealService {

    private FxDealRepository fxDealRepository;

    public FxDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FxDeal addFxDeal(FxDeal fxDeal) {
        return this.fxDealRepository.save(fxDeal);
    }

    public boolean existsByDealId(String dealId) {
        return this.fxDealRepository.existsByDealId(dealId);
    }

}
