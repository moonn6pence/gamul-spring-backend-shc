package com.gamul.gamul.api.product.domain.dto;


import com.gamul.gamul.domain.entity.PriceHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RecentPriceDto {
    private LocalDate date;
    private int price;

    public static RecentPriceDto of(PriceHistory priceHistory) {

        return new RecentPriceDto(priceHistory.getDate(), priceHistory.getPrice());
    }
}
