package com.gamul.gamul.api.bookmark.domain.dto;

import com.gamul.gamul.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponseDto {
    private List<String> market;

    public static BookmarkResponseDto of(List<Bookmark> bookmarkList) {
        List<String> bookmarkNameList = new ArrayList<>();

        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto();

        for (Bookmark bookmark : bookmarkList) {
            bookmarkResponseDto.getMarket().add(bookmark.getMarket().getName());

            log.info("Dto of, bookmark : {}", bookmark.getMarket().getName());
        }

        return new BookmarkResponseDto(bookmarkNameList);
    }
}
