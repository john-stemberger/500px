package com.john.px500.data.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    private long id;
    private String username;
    private String fullname;
//    private String firstname;
//    private String lastname;
//    private String city;
//    private String country;
    // NOTE: I should use the pics in the avatar key but I am not going to
    @SerializedName("userpic_url")
    private String userpicUrl;
//    @SerializedName("upgrade_status")
//    private int upgradeStatus;


}
