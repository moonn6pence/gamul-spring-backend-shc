package com.gamul.gamul.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class PriceHistory {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDate date;

    private int price;

    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketId")
    private Market market;

    public static PriceHistory createPriceHistory(String name, LocalDate date, int price, String unit, Market market) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setName(name);
        priceHistory.setDate(date);
        priceHistory.setPrice(price);
        priceHistory.setUnit(unit);
        priceHistory.setMarket(market);

        return priceHistory;
    }
}
