package com.gamul.gamul.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Market {

    @Id @GeneratedValue
    private Long marketId;
    private String name;

    private String x;
    private String y;

//   JoinColumn : 외래키 지정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district")
    private District district;

    @OneToMany(mappedBy = "market")
    private List<MarketProductHistory> marketProductHistoryList = new ArrayList<>();

    public void addMarketProductHistory(MarketProductHistory marketProductHistory){
        marketProductHistoryList.add(marketProductHistory);
        marketProductHistory.setMarket(this);
    }
}
