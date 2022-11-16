package com.gamul.gamul.api.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private List<PriceHistoryDto> priceHistories;

    public static ProductResponseDto of(List<PriceHistoryDto> priceHistoryDtos) {
        return new ProductResponseDto(priceHistoryDtos);
    }
}
