package com.mota.tribal.protsahan.Profile.Model.Data;


import java.util.ArrayList;

public class Profile {


    private String token, name, description, tribe, address, aadharNO, phone, gender, profilephoto, username, state, education;
    private ArrayList<String> skillsSelected;

    public Profile(String token, String name, String description,
                   String tribe, String address, String aadharNO,
                   String phone, String gender, String profilephoto, String username, String state, ArrayList<String> skillsSelected, String education) {
        this.token = token;
        this.name = name;
        this.description = description;
        this.tribe = tribe;
        this.address = address;
        this.aadharNO = aadharNO;
        this.phone = phone;
        this.gender = gender;
        this.profilephoto = profilephoto;
        this.username = username;
        this.state = state;
        this.education = education;
        this.skillsSelected = skillsSelected;
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
        return aadharNO;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getImg() {
        return profilephoto;
    }

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }

    public ArrayList<String> getSkillsSelected() {
        return skillsSelected;
    }

    public String getEducation() {
        return education;
    }
}
