package com.gamul.gamul.api.product.service;

import com.gamul.gamul.api.product.domain.dto.PriceHistoryDto;
import com.gamul.gamul.api.product.domain.dto.ProductRequestDto;
import com.gamul.gamul.api.product.domain.dto.ProductResponseDto;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.PriceHistory;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApiService {

    private final PriceHistoryRepository priceHistoryRepository;

    private final MarketRepository marketRepository;

    @Transactional(readOnly = true)
    public ProductResponseDto getProductPriceHistory(ProductRequestDto productRequestDto) {

        Market market = getMarket(productRequestDto.getMarket());

        log.info("market name = {}", market.getName());

        List<PriceHistory> priceHistories = priceHistoryRepository.findByNameAndMarket(productRequestDto.getProduct(), market);
        List<PriceHistoryDto> priceHistoryDtos = new ArrayList<>();

        for (PriceHistory priceHistory : priceHistories) {
            priceHistoryDtos.add(PriceHistoryDto.of(priceHistory));
        }

        return ProductResponseDto.of(priceHistoryDtos);
    }

    private Market getMarket(String marketName) {

        Market market = marketRepository.findByName(marketName).get();

        return market;
    }
}
