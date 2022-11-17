package com.gamul.gamul.api.market.service;

import com.gamul.gamul.api.market.domain.dto.*;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MarketApiService {

    private final MarketRepository marketRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    @Transactional(readOnly = true)
    public MarketResponseDto getMarketPrice(MarketRequestDto marketRequestDto) {

        Market currentMarket = getMarket(marketRequestDto.getMarket());

        List<Market> nearMarkets = getThreeNearestMarketsByDistance(currentMarket);

        String first = nearMarkets.get(0).getName();
        String second = nearMarkets.get(1).getName();
        String third = nearMarkets.get(2).getName();

        List<String> columnNames = getAllProductName();

        List<PriceRowDto> table = new ArrayList<>();

        for (String name : columnNames) {

        }

        return MarketResponseDto.of(first, second, third, table);
    }

    private List<Market> getThreeNearestMarketsByDistance(Market currentMarket) {

        List<Market> markets = marketRepository.findAll();
        List<MarketDistanceDto> marketDistanceDtos = new ArrayList<>();

        for (Market market : markets) {
            MarketDistanceDto marketDistanceDto = MarketDistanceDto.of(market, computeMarketDistance(currentMarket, market));
            marketDistanceDtos.add(marketDistanceDto);
        }

        Collections.sort(marketDistanceDtos);

        List<Market> result = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            result.add(marketDistanceDtos.get(i).getMarket());
        }

        return result;
    }

    private Double computeMarketDistance(Market current, Market target) {

        Double currentX = current.getX();
        Double currentY = current.getY();
        Double targetX = current.getX();
        Double targetY = current.getY();

        return Math.pow(currentX - targetX, 2) + Math.pow(currentY - targetY, 2);
    }

    private List<String> getAllProductName() {
        return Arrays.asList("사과","양파","배","돼지고기","무","계란");
    }

//    @Transactional(readOnly = true)
//    public MarketInitResponseDto getAllMarket() {
//        return MarketInitResponseDto.of()
//    }

    private Market getMarket(String marketName) {

        Market market = marketRepository.findByName(marketName).get();

        return market;
    }
}
