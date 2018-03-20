package com.mota.tribal.protsahan.Profile.Model.Data;


public class Profile {


    private String token, name, description, tribe, address, aadhar, phone, gender, img, username, state;


    public Profile(String token, String name, String description,
                   String tribe, String address, String aadhar,
                   String phone, String gender, String img, String username, String state) {
        this.token = token;
        this.name = name;
        this.description = description;
        this.tribe = tribe;
        this.address = address;
        this.aadhar = aadhar;
        this.phone = phone;
        this.gender = gender;
        this.img = img;
        this.username = username;
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTribe() {
        return tribe;
    }

    public String getAddress() {
        return address;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getImg() {
        return img;
    }

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }
}
