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

    private Double x;
    private Double y;
}
