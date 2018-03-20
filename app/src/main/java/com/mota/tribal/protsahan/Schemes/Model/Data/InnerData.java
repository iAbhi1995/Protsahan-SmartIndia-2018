package com.mota.tribal.protsahan.Schemes.Model.Data;

public class InnerData {

    public final String title;
    public final String name;
    public final String address;
    public final String avatarUrl;
    public final int age;

    public InnerData(String title, String name, String address, String avatarUrl, int age) {
        this.title = title;
        this.name = name;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.age = age;

    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getAge() {
        return age;
    }

}
