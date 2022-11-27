package com.gamul.gamul.repository;

import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.PriceHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByMarket(Market market, Sort sort);

    List<PriceHistory> findByMarket(Market market);

    @Query(value = "select DISTINCT(p.name) from PriceHistory p")
    List<String> findAllName();
}
