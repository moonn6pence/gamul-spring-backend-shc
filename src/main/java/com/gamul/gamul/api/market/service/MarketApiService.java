package com.gamul.gamul.api.market.service;

import com.gamul.gamul.api.market.domain.dto.*;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.PriceHistory;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
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

        List<Map<String, PriceHistory>> marketLatestPrices = new ArrayList<>();

        for (Market market : nearMarkets) {
            List<PriceHistory> unrefined = priceHistoryRepository.findByMarket(market);
            marketLatestPrices.add(getLatestPriceHistory(unrefined));
        }

        List<PriceRowDto> table = new ArrayList<>();

        List<String> columnNames = getAllProductName();

        for (String name : columnNames) {
            List<MarketPriceDto> row = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                PriceHistory priceHistory = marketLatestPrices.get(i).getOrDefault(name,null);
                if (priceHistory == null) {
                    row.add(null);
                    continue;
                }
                MarketPriceDto marketPriceDto = MarketPriceDto.of(priceHistory.getPrice(), priceHistory.getUnit());
                row.add(marketPriceDto);
            }
            PriceRowDto priceRowDto = PriceRowDto.of(name, row);
            table.add(priceRowDto);
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

        return Arrays.asList("사과", "양파", "배", "돼지고기", "무", "계란");

    }

    private Map<String, PriceHistory> getLatestPriceHistory(List<PriceHistory> unrefined) {
        List<String> products = getAllProductName();

        Map<String, PriceHistory> latestPriceHistory = new HashMap<>();

        for (String name : products) {
            List<PriceHistory> certainProductPrices = unrefined.stream()
                    .filter(price -> price.getName().equals(name))
                    .collect(Collectors.toList());

            if (certainProductPrices.isEmpty()) {
                latestPriceHistory.put(name, null);
                continue;
            }

            PriceHistory latestPrice = Collections.max(certainProductPrices, Comparator.comparing(PriceHistory::getDate));
            latestPriceHistory.put(name, latestPrice);
        }

        return latestPriceHistory;
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
