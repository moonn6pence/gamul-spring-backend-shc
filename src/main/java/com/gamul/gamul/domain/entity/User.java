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

    private String userName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserLocation> userLocations = new ArrayList<>();

    public void addUserLocation(UserLocation userLocation){
        userLocations.add(userLocation);
        userLocation.setUser(this);
    }
}


