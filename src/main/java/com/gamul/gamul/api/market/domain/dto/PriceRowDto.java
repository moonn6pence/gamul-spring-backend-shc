package com.gamul.gamul.api.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PriceRowDto {
    private String name;
    private List<MarketPriceDto> row;

    public static PriceRowDto of(String name, List<MarketPriceDto> row) {
        return new PriceRowDto(name, row);
    }
}
