package com.gamul.gamul.api.bookmark.service;

import com.gamul.gamul.api.auth.util.SecurityUtil;
import com.gamul.gamul.api.bookmark.domain.dto.BookmarkRequestDto;
import com.gamul.gamul.api.bookmark.domain.dto.BookmarkResponseDto;
import com.gamul.gamul.domain.entity.Bookmark;
import com.gamul.gamul.domain.entity.Market;
import com.gamul.gamul.domain.entity.Member;
import com.gamul.gamul.exception.DuplicateBookmarkException;
import com.gamul.gamul.exception.NoBookmarkException;
import com.gamul.gamul.exception.NoUserException;
import com.gamul.gamul.repository.BookmarkRepository;
import com.gamul.gamul.repository.MarketRepository;
import com.gamul.gamul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkApiService {

    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;

    private final MarketRepository marketRepository;

    @Transactional(readOnly = true)
    public BookmarkResponseDto getBookmarkList() {

        Member member = getMember();

        return BookmarkResponseDto.of(bookmarkRepository.findAllByMember(member));

    }

    @Transactional
    public void addBookmark(BookmarkRequestDto bookmarkRequestDto){
        Member member = getMember();
        Market market = getMarket(bookmarkRequestDto);

        if (bookmarkRepository.existsByMarket(market)) {
            throw new DuplicateBookmarkException("이미 추가한 북마크입니다.");
        }

        Bookmark bookmark = Bookmark.createBookmark(member,market);

        bookmarkRepository.save(bookmark);

        log.info("addBookmark bookmark:{}",bookmark.getMarket().getName());
    }

    @Transactional
    public void deleteBookmark(BookmarkRequestDto bookmarkRequestDto){
        Member member = getMember();
        Market market = getMarket(bookmarkRequestDto);

        if (!bookmarkRepository.existsByMarket(market)) {
            throw new NoBookmarkException("존재하지 않는 북마크입니다.");
        }

        bookmarkRepository.deleteBookmarkByMemberAndMarket(member, market);
    }

    private Member getMember() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(()->new NoUserException("유저 정보가 없습니다."));

        log.info("getMember username {}", member.getName());

        return member;
    }

    private Market getMarket(BookmarkRequestDto bookmarkRequestDto) {

        Market market = marketRepository.findByName(bookmarkRequestDto.getMarket()).get();

        log.info("getMarket market name {}",market.getName());

        return market;
    }
}
