package com.bidAndWin.model.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "addressId_generator")
    @SequenceGenerator(name = "addressId_generator",allocationSize = 1)
    private Long addressId;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private Integer aptOrHouseNumber;
    @JsonBackReference("client-address")
    @OneToOne
    @JoinColumn(name="client_id",unique = true)
    private Client client;

    public Address(){}
}
