package com.gamul.gamul.api.bookmark.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkRequestDto {
    @NotBlank
    private String market;
}
