package com.rdongol.fxprocessor.repository;

import com.rdongol.fxprocessor.model.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Long> {
    boolean existsByDealId(String dealId);
}
