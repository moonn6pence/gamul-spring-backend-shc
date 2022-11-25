package com.gamul.gamul.api.openapi.service;

import com.gamul.gamul.api.openapi.domain.JsonInfo;
import com.gamul.gamul.api.openapi.domain.JsonKey;
import com.gamul.gamul.api.openapi.domain.JsonValue;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.PriceHistory;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final PriceHistoryRepository priceHistoryRepository;
    private final MarketRepository marketRepository;

    public void processJson(JSONObject json, JsonInfo jsonInfo) {

        JSONObject object = (JSONObject) json.get("ListNecessariesPricesService");
        JSONObject item;
        JSONArray row = (JSONArray) object.get("row");

        for (int i = 0; i < row.size(); i++) {
            item = (JSONObject) row.get(i);
            String marketName = (String) item.get("M_NAME");
            String itemName = (String) item.get("A_NAME");
            String unit = (String) item.get("A_UNIT");
            String date = (String) item.get("P_DATE");
            int price = Integer.parseInt((String) item.get("A_PRICE"));

            JsonKey jsonKey = new JsonKey(marketName, itemName, date);

            jsonInfo.addJson(jsonKey, unit, price);
        }
    }

    @Transactional
    public void insertInfoToPriceHistory(JsonInfo jsonInfo) {
        Map<JsonKey,JsonValue> info = jsonInfo.getInfo();
        for (JsonKey jsonKey : info.keySet()) {
            try {
                int averagePrice = info.get(jsonKey).getAveragePrice();
                String unit = info.get(jsonKey).getUnit();

                Market market = marketRepository.findByName(jsonKey.getMarketName()).get();

                priceHistoryRepository.save(PriceHistory.createPriceHistory(jsonKey.getItemName(), jsonKey.getDate(), averagePrice, unit, market));

            } catch (Exception e) {
                continue;
            }

        }
    }

}
