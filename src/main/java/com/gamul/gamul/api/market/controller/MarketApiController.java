package com.gamul.gamul.api.market.controller;

import com.gamul.gamul.api.market.domain.dto.MarketRequestDto;
import com.gamul.gamul.api.market.service.MarketApiService;
import com.gamul.gamul.api.web.DefaultResponse;
import com.gamul.gamul.api.web.ResponseMessage;
import com.gamul.gamul.api.web.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/market")
@RequiredArgsConstructor
public class MarketApiController {

    private final MarketApiService marketApiService;

    @GetMapping("")
    public ResponseEntity getMarketPrice(@Valid MarketRequestDto marketRequestDto) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.READ_PRICE_HISTORY, marketApiService.getMarketPrice(marketRequestDto)), HttpStatus.OK);
    }
}
