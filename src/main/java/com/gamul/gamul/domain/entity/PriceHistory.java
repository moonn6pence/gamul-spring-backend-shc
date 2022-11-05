package com.gamul.gamul.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Getter @Setter
public class PriceHistory {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime date;

    private Long price;

    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketId")
    private Market market;


}
