package com.gamul.gamul.api.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarketPriceDto {
    private int price;
    private String unit;

    public static MarketPriceDto of(int price, String unit) {
        return new MarketPriceDto(price, unit);
    }
}
