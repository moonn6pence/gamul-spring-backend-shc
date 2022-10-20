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
public class District {
    @Id @GeneratedValue
    private Long code;
    private String name;

    @OneToMany(mappedBy = "district")
    private List<Market> markets = new ArrayList<>(); // OneToMany


//    //연관관계 메서드
    public void addMarket(Market market){
        markets.add(market);
        market.setDistrict(this);
    }

}
