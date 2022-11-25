package com.gamul.gamul.api.openapi.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class JsonInfo {
    private final Map<JsonKey, JsonValue> info;

    public JsonInfo() {
        this.info = new HashMap<>();
    }

    public void addJson(JsonKey jsonKey,String unit, int price) {
        if (info.containsKey(jsonKey)) {
            info.get(jsonKey).addJsonValue(unit, price);
            return;
        }

        info.put(jsonKey, JsonValue.of(unit, price));
    }
}
