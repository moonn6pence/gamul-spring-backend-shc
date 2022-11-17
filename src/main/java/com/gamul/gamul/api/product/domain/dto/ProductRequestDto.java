package com.gamul.gamul.api.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank
    String product;

    @NotBlank
    String market;
}
