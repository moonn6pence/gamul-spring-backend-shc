package com.gamul.gamul.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long userId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void addUserLocation(Bookmark bookmark){
        bookmarks.add(bookmark);
        bookmark.setUser(this);
    }
}


