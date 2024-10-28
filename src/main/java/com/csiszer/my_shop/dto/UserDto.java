package com.csiszer.my_shop.dto;

import lombok.Data;

import java.util.List;


@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String emil;
    private List<OrderDto> orders;
    private CartDto cart;



}
