package com.gamul.gamul.api.bookmark.domain.dto;

import com.gamul.gamul.domain.entity.Bookmark;
import com.gamul.gamul.exception.NoBookmarkException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class BookmarkResponseDto {
    private List<String> market;

    public BookmarkResponseDto(){
        market = new ArrayList<>();
    }

    public static BookmarkResponseDto of(List<Bookmark> bookmarkList) {

        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto();

        if (!bookmarkList.isEmpty()) {
            for (Bookmark bookmark : bookmarkList) {
                bookmarkResponseDto.getMarket().add(bookmark.getMarket().getName());
            }
        }

        return bookmarkResponseDto;
    }
}
