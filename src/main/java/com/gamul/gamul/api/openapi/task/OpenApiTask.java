package com.gamul.gamul.api.openapi.task;

import com.gamul.gamul.api.openapi.domain.JsonInfo;
import com.gamul.gamul.api.openapi.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenApiTask implements CommandLineRunner {

    private final String apiDomain = "http://openAPI.seoul.go.kr:8088";
    @Value("${openApi.key}")
    private String key;
    private final String type = "json";
    private final String service = "ListNecessariesPricesService";
    private final OpenApiService openApiService;

    // 초 분 시 일 월 주 년
    @Scheduled(cron = "0 0 0 L * *")
    public void gatherCurrentMonthOpenApiData(){

        LocalDate today = LocalDate.now();
        String todayYearMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        gatherOpenApiDataOf(todayYearMonth);
    }

    public void gatherPastOpenApiDate() {
        LocalDate today = LocalDate.now();

        for (int month = 1; month < 6; month++) {
            LocalDate past = today.minusMonths(month);
            String pastYearMonth = past.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            gatherOpenApiDataOf(pastYearMonth);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        gatherPastOpenApiDate();
    }
    private void gatherOpenApiDataOf(String todayYearMonth) {
        JsonInfo jsonInfo = new JsonInfo();

        int startIndex = 1, endIndex = 1000;

        while (true) {
            URL url = buildUrl(apiDomain, key, type, service, startIndex, endIndex, todayYearMonth);

            String result = readUrl(url);
            JSONObject jsonObject = parseStringToJson(result);

            if (!iterateJson(jsonObject,jsonInfo)) {
                log.info("스케줄러 open api url = {}",url);
                break;
            }

            startIndex = endIndex + 1;
            endIndex += 1000;
        }

        openApiService.insertInfoToPriceHistory(jsonInfo);
    }

    private URL buildUrl(String apiDomain, String key, String type, String service, int startIndex, int endIndex, String today) {

        try {
            StringBuilder urlBuilder = new StringBuilder(apiDomain);
            urlBuilder.append("/" + key + "/" + type + "/" + service + "/" + startIndex + "/" + endIndex);
            urlBuilder.append("/" + URLEncoder.encode(" ", "UTF-8")); // 마트이름
            urlBuilder.append("/" + URLEncoder.encode(" ", "UTF-8")); // 상품이름
            urlBuilder.append("/" + today);

            return new URL(urlBuilder.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("Open Api 호출 url 인코딩 에러");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("URL 에러");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String readUrl(URL url) {

        StringBuffer resultBuffer = new StringBuffer();

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
            String returnLine;
            while ((returnLine = bufferedReader.readLine()) != null) {
                resultBuffer.append(returnLine);
            }

            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return resultBuffer.toString();
    }

    private JSONObject parseStringToJson(String result) {
        try {
            JSONParser parser = new JSONParser();
            return  (JSONObject) parser.parse(result);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private boolean iterateJson(JSONObject json,JsonInfo jsonInfo) {

        String key;
        Iterator<String> keys = json.keySet().iterator();
        key = keys.next();

        if (key.equals("RESULT")) {
            return false;
        }

        openApiService.processJson(json, jsonInfo);

        return true;
    }


}
