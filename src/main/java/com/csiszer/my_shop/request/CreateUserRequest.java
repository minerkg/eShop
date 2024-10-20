package com.csiszer.my_shop.request;


import lombok.Data;

@Data
public class CreateUserRequest {

    private Long userId;
    private String firstName;
    private String lastName;
    private String emil;
    private String password;
}
