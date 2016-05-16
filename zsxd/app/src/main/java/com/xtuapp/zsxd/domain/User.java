package com.xtuapp.zsxd.domain;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class User {
    private int id;
    private boolean sex;
    private String password;
    private String email;
    private String studentId;
    private String studentPassword;
    private String institude;
    private String major;
    private String nickname;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getInstitude() {
        return institude;
    }

    public void setInstitude(String institude) {
        this.institude = institude;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", institude='" + institude + '\'' +
                ", major='" + major + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
