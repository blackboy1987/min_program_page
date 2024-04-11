package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author black
 */
@Entity
public class Member extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String openId;

    @JsonView({PageView.class})
    private String nickName;

    @JsonView({PageView.class})
    private String avatarUrl;

    @JsonView({PageView.class})
    private Boolean isAuth=true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member parent;

    @OneToMany(mappedBy = "parent",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<Member> children = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    private Integer grade;

    /**
     * 树路径
     */
    @Column(nullable = false)
    private String treePath;

    @NotNull
    @Column(updatable = false,nullable = false)
    private Long appId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public Member getParent() {
        return parent;
    }

    public void setParent(Member parent) {
        this.parent = parent;
    }

    public Set<Member> getChildren() {
        return children;
    }

    public void setChildren(Set<Member> children) {
        this.children = children;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}
