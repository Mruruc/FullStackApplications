package com.mruruc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private Long userId;

    private Address(){}
}
