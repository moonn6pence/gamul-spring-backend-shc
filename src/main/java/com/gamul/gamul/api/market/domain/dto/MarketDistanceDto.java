package com.gamul.gamul.api.market.domain.dto;

import com.gamul.gamul.domain.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketDistanceDto implements Comparable<MarketDistanceDto>{
    private Market market;
    private Double distance;

    @Override
    public int compareTo(MarketDistanceDto target) {
        if (target.distance < distance) {
            return 1;
        } else if (target.distance > distance) {
            return -1;
        }
        return 0;
    }

    public static MarketDistanceDto of(Market market, Double distance) {
        return new MarketDistanceDto(market, distance);
    }
}
