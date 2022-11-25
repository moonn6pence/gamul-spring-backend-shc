package com.gamul.gamul.api.openapi.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class JsonKey {

    private String marketName;
    private String itemName;
    private String unit;
    private LocalDate date;

    public JsonKey(String marketName, String itemName, String unit, String date) {
        this.marketName = marketName;
        this.itemName = itemName;
        this.unit = unit;
        this.date = LocalDate.parse(date);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (!(object instanceof JsonKey))
            return false;

        JsonKey key = (JsonKey) object;

        return Objects.equals(marketName, key.marketName) &&
                Objects.equals(itemName, key.itemName) &&
                Objects.equals(unit, key.unit) &&
                Objects.equals(date, key.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketName, itemName, unit, date);
    }
}
