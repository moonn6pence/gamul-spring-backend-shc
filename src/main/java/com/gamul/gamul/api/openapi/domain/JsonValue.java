package com.gamul.gamul.api.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonValue {
    private int priceSum;
    private int count;

    public static JsonValue of(int price) {
        return new JsonValue(price, 1);
    }

    public void addJsonValue(int price) {
        count++;
        this.priceSum += price;
    }

    public int getAveragePrice() {
        return priceSum / count;
    }
}
