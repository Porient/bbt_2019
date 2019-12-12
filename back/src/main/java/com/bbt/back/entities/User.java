package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 14:49
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userEmail;
    private String nickname;
    private String password;
    private Integer phone;
    private Integer age;
    private String profession;
    private String tag1;
    private String tag2;
    private String tag3;
    private String used;
    private Integer isBan;

    public User(){

    }

    public User(String userEmail, String nickname, String password) {
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
    }

    public User(Integer userId, String userEmail, String nickname, String password, Integer phone, Integer age, String profession, String tag1, String tag2, String tag3, String used, Integer isBan) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.profession = profession;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.used = used;
        this.isBan = isBan;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public Integer getIsBan() {
        return isBan;
    }

    public void setIsBan(Integer isBan) {
        this.isBan = isBan;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", used='" + used + '\'' +
                ", isBan=" + isBan +
                '}';
    }
}
