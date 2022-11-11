package com.gamul.gamul.repository;

import com.gamul.gamul.domain.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> {

    Optional<Market> findByName(String name);

    boolean existsByName(String name);
}
