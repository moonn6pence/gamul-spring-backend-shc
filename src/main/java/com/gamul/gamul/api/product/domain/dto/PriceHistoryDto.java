package com.gamul.gamul.api.product.domain.dto;

import com.gamul.gamul.domain.entity.PriceHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PriceHistoryDto {

    private LocalDate date;
    private int price;
    private String unit;

    public static PriceHistoryDto of(PriceHistory priceHistory) {
        return new PriceHistoryDto(priceHistory.getDate(), priceHistory.getPrice(), priceHistory.getUnit());
    }
}
