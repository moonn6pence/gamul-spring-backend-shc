package com.gamul.gamul.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void addMemberBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
        bookmark.setMember(this);
    }

    @Builder
    public Member(String name, String email, String password, Authority authority) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}


