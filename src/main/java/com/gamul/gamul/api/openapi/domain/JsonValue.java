package com.gamul.gamul.api.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JsonValue {

    private List<Pair> unitPrice;

    private String unit;

    public static JsonValue of(String unit, int price) {
        return new JsonValue(unit, price);
    }

    public JsonValue(String unit, int price) {
        this.unitPrice = new ArrayList<>();

        int numericUnit = extractNumberFromUnit(unit);

        if (numericUnit < 0) {
            unitPrice.add(new Pair(unit, price));
        } else {
            unitPrice.add(new Pair(unit, numericUnit, price));
        }
    }

    private int extractNumberFromUnit(String unit) {
        String pattern = "\\D+";

        String[] extracted = unit.split(pattern);

        int maxUnit = 1;

        for (int i = 0; i < extracted.length; i++) {
            if(!extracted[i].equals(""))
                maxUnit = Math.max(maxUnit, Integer.parseInt(extracted[i]));
        }

        return maxUnit;
    }

    public void addJsonValue(String unit, int price) {

        int numericUnit = extractNumberFromUnit(unit);

        if (numericUnit < 0) {
            unitPrice.add(new Pair(unit, price));
        } else {
            unitPrice.add(new Pair(unit, numericUnit, price));
        }
    }

    public int getAveragePrice() {
        int priceSum = 0;
        int maxUnit = 0;
        String maxUnitOrigin = "1";

        for (Pair pair : unitPrice) {
            if (maxUnit < pair.unit) {
                maxUnit = pair.unit;
                maxUnitOrigin = pair.unitOrigin;
            }
        }

        this.unit = maxUnitOrigin;

        for (Pair pair : unitPrice) {
            if (pair.unit == 1) {
                priceSum += pair.price;
                continue;
            }
            priceSum += pair.price * (maxUnit / pair.unit);
        }

        return priceSum / unitPrice.size();
    }

    static class Pair {
        String unitOrigin;
        int unit;
        int price;

        public Pair(String unitOrigin, int unit, int price) {
            this.unitOrigin = unitOrigin;
            this.unit = unit;
            this.price = price;
        }

        public Pair(String unitOrigin, int price) {
            this.unitOrigin = unitOrigin;
            this.unit = 1;
            this.price = price;
        }
    }

}
