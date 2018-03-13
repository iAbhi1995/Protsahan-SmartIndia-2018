package com.mota.tribal.protsahan.Profile.Model.Data;


public class Profile {


    private String id, name, description, tribe, address, aadhar, phone, gender, img;


    public Profile(String id, String name, String description,
                   String tribe, String address, String aadhar,
                   String phone, String gender, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tribe = tribe;
        this.address = address;
        this.aadhar = aadhar;
        this.phone = phone;
        this.gender = gender;
        this.img = img;
    }

    public String getId() {
        return id;
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
}
