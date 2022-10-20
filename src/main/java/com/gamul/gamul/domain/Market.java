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
public class Market {

    @Id @GeneratedValue
    private Long marketId;
    private String name;

    private String x;
    private String y;

//   JoinColomn : 외래키 지정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district")
    private District district;

    @OneToMany(mappedBy = "market")
    private List<> __ = new ArrayList<>();
}
