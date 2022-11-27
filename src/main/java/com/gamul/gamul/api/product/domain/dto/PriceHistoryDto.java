package com.gamul.gamul.api.product.domain.dto;

import com.gamul.gamul.domain.entity.PriceHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PriceHistoryDto {

    private String name;
    private String unit;
    private List<RecentPriceDto> recentPriceDtos;
}
