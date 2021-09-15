package com.example.shumazhi;

public class User {


    String fullName, email,address,phoneNumber, dob,userName;

    public User(){}

    public User(String fullName,String address, String phoneNumber, String dob, String userName, String email)
    {
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;

    }

}
