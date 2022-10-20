package com.gamul.gamul.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long productCode;
    private String productName;
    private Long speciesCode;
    private String speciesName;

    private String unit;
    private String grade;
    private String note;

    @OneToMany(mappedBy = "product")
    private List<MarketProductHistory> productHistory = new ArrayList<>();




}
