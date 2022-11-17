package com.gamul.gamul.api.product.controller;

import com.gamul.gamul.api.product.domain.dto.ProductRequestDto;
import com.gamul.gamul.api.product.service.ProductApiService;
import com.gamul.gamul.api.web.DefaultResponse;
import com.gamul.gamul.api.web.ResponseMessage;
import com.gamul.gamul.api.web.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductApiService productApiService;

    @GetMapping("")
    public ResponseEntity getProductPriceHistory(@RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.READ_PRICE_HISTORY, productApiService.getProductPriceHistory(productRequestDto)), HttpStatus.OK);
    }
}
