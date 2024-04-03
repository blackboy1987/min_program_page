package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * @author black
 */
@Entity
public class AccessToken extends BaseEntity<Long>{

    @NotEmpty
    @Column(nullable = false,updatable = false)
    private String token;

    @NotNull
    @Min(0)
    @Column(nullable = false,updatable = false)
    private Integer expiresIn;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({PageView.class})
    private Date expireDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,updatable = false)
    private App app;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Transient
    @JsonView({PageView.class})
    public String getAccessToken(){
        return token.substring(0,20);
    }
}
