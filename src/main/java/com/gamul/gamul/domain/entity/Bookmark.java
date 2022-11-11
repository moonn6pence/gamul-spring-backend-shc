package com.gamul.gamul.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketId")
    private Market market;

    public static Bookmark createBookmark(Member member, Market market) {

        Bookmark bookmark = new Bookmark();
        bookmark.setMember(member);
        bookmark.setMarket(market);

        member.addMemberBookmark(bookmark);

        return bookmark;
    }
}
