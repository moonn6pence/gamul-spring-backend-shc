package com.gamul.gamul.api.bookmark.domain.dto;

import com.gamul.gamul.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class BookmarkResponseDto {
    private List<Info> market;

    public BookmarkResponseDto(){
        market = new ArrayList<>();
    }

    public static BookmarkResponseDto of(List<Bookmark> bookmarkList) {

        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto();

        if (!bookmarkList.isEmpty()) {
            for (Bookmark bookmark : bookmarkList) {
                Info info = new Info(bookmark.getMarket().getName(),bookmark.getMarket().getRegion());
                bookmarkResponseDto.getMarket().add(info);
            }
        }

        return bookmarkResponseDto;
    }

    @Getter
    static class Info {
        private String name;
        private String region;

        public Info(String name, String region) {
            this.name = name;
            this.region = region;
        }
    }

}
