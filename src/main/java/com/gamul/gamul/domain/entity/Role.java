package com.gamul.gamul.domain.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("admin"), ROLE_MEMBER("member");

    private String description;

    Role(String description){
        this.description = description;
    }
}
