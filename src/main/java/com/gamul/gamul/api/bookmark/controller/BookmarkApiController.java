package com.gamul.gamul.api.bookmark.controller;

import com.gamul.gamul.api.bookmark.domain.dto.BookmarkRequestDto;
import com.gamul.gamul.api.bookmark.service.BookmarkApiService;
import com.gamul.gamul.api.web.DefaultResponse;
import com.gamul.gamul.api.web.ResponseMessage;
import com.gamul.gamul.api.web.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/bookmark")
@RequiredArgsConstructor
public class BookmarkApiController {

    private final BookmarkApiService bookmarkApiService;

    @GetMapping("")
    public ResponseEntity getBookmark(){
        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.READ_BOOKMARK, bookmarkApiService.getBookmarkList()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addBookmark(@RequestBody @Valid BookmarkRequestDto bookmarkRequestDto){

        bookmarkApiService.addBookmark(bookmarkRequestDto);

        return new ResponseEntity(DefaultResponse.res(StatusCode.CREATED, true, ResponseMessage.CREATED_BOOKMARK), HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity deleteBookmark(@RequestBody @Valid BookmarkRequestDto bookmarkRequestDto){

        bookmarkApiService.deleteBookmark(bookmarkRequestDto);

        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.DELETED_BOOKMARK), HttpStatus.OK);
    }



}
