package com.bootx.entity;

import com.bootx.common.AdSlot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author blackboy
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppAd extends BaseEntity<Long>{

    @NotNull
    @JsonIgnore
    @Column(nullable = false,updatable = false,name = "app_id")
    public Long appId;

    @NotEmpty
    @Column(nullable = false)
    @JsonView({PageView.class,ViewView.class})
    private String name;

    /**
     * 广告id
     */
    @NotEmpty
    @Column(nullable = false)
    @JsonView({PageView.class,ViewView.class})
    public String adUnitId;

    @NotNull
    @Column(nullable = false)
    @JsonView({PageView.class,ViewView.class})
    private AdSlot adSlot;

    /**
     * 状态
     */
    @JsonView({PageView.class,ViewView.class})
    private String status;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdUnitId() {
        return adUnitId;
    }

    public void setAdUnitId(String adUnitId) {
        this.adUnitId = adUnitId;
    }

    public AdSlot getAdSlot() {
        return adSlot;
    }

    public void setAdSlot(AdSlot adSlot) {
        this.adSlot = adSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
