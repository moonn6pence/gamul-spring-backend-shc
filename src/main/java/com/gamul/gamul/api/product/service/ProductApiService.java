package com.gamul.gamul.api.product.service;

import com.gamul.gamul.api.product.domain.dto.PriceHistoryDto;
import com.gamul.gamul.api.product.domain.dto.ProductRequestDto;
import com.gamul.gamul.api.product.domain.dto.ProductResponseDto;
import com.gamul.gamul.api.product.domain.dto.RecentPriceDto;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.PriceHistory;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        Sort sort = sortByDate();

        LocalDate nowDate = LocalDate.now();

        String targetName = productRequestDto.getProduct();

        List<String> allProductNames = priceHistoryRepository.findAllName();
        List<String> relatedProductNames = new ArrayList<>();

        for (String name : allProductNames) {
            if (targetName.equals("배")) {
                if (name.contains(targetName) && !name.contains("배추"))
                    relatedProductNames.add(name);
            }else {
                if (name.contains(targetName))
                    relatedProductNames.add(name);
            }
        }

        List<PriceHistoryDto> priceHistoryDtos = new ArrayList<>();

        for (String name : relatedProductNames) {
            List<PriceHistory> priceHistories = priceHistoryRepository.findByMarket(market, sort);

            List<RecentPriceDto> recentPriceDtos = new ArrayList<>();
            for (PriceHistory priceHistory : priceHistories) {
                if (recentPriceDtos.size() == 6) {
                    break;
                }

                if (priceHistory.getDate().isBefore(nowDate)) {
                    recentPriceDtos.add(RecentPriceDto.of(priceHistory));
                }
            }
            priceHistoryDtos.add(new PriceHistoryDto(name, priceHistories.get(0).getUnit(), recentPriceDtos));
        }

        return ProductResponseDto.of(priceHistoryDtos);
    }

    private Market getMarket(String marketName) {

        Market market = marketRepository.findByName(marketName).get();

        return market;
    }

    private Sort sortByDate() {
        return Sort.by(Sort.Direction.DESC, "date");
    }
}
