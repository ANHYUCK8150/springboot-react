package com.study.www.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.www.auth.entity.AuthProvider;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column
    private Boolean emailVerified = false;

    @JsonIgnore
    @Column
    private String password;

    @NotNull
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column
    private String providerId;
    
    @Column
    private String latitude;
    
    @Column 
    private String longitude;

    @Builder
    public User(String name, String email, String imageUrl, Boolean emailVerified, String password, AuthProvider provider, String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}
