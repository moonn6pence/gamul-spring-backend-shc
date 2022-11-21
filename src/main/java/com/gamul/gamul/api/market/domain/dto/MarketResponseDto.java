package com.gamul.gamul.api.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MarketResponseDto {
    private String first;
    private String second;
    private String third;
    private List<PriceRowDto> table;

    public static MarketResponseDto of(String first, String second, String third, List<PriceRowDto> rows) {

        return new MarketResponseDto(first, second, third, rows);
    }
}
