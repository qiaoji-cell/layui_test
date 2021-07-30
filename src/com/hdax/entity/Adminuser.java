package com.hdax.entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Joe on 2021-07-27 0027
 */
public class Adminuser {
    private int aUid;
    private String aUname;
    private String aUpwd;
    private String aUphone;
    private String aIdentityCard;
    private String avatar;
    private int roleId;
    private Timestamp aCreateTime;
    private Integer aSex;
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getaUid() {
        return aUid;
    }

    public void setaUid(int aUid) {
        this.aUid = aUid;
    }

    public String getaUname() {
        return aUname;
    }

    public void setaUname(String aUname) {
        this.aUname = aUname;
    }

    public String getaUpwd() {
        return aUpwd;
    }

    public void setaUpwd(String aUpwd) {
        this.aUpwd = aUpwd;
    }

    public String getaUphone() {
        return aUphone;
    }

    public void setaUphone(String aUphone) {
        this.aUphone = aUphone;
    }

    public String getaIdentityCard() {
        return aIdentityCard;
    }

    public void setaIdentityCard(String aIdentityCard) {
        this.aIdentityCard = aIdentityCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Timestamp getaCreateTime() {
        return aCreateTime;
    }

    public void setaCreateTime(Timestamp aCreateTime) {
        this.aCreateTime = aCreateTime;
    }

    public Integer getaSex() {
        return aSex;
    }

    public void setaSex(Integer aSex) {
        this.aSex = aSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adminuser adminuser = (Adminuser) o;
        return aUid == adminuser.aUid &&
                roleId == adminuser.roleId &&
                Objects.equals(aUname, adminuser.aUname) &&
                Objects.equals(aUpwd, adminuser.aUpwd) &&
                Objects.equals(aUphone, adminuser.aUphone) &&
                Objects.equals(aIdentityCard, adminuser.aIdentityCard) &&
                Objects.equals(avatar, adminuser.avatar) &&
                Objects.equals(aCreateTime, adminuser.aCreateTime) &&
                Objects.equals(aSex, adminuser.aSex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aUid, aUname, aUpwd, aUphone, aIdentityCard, avatar, roleId, aCreateTime, aSex);
    }
}
