package com.bidAndWin.model.client;

import com.bidAndWin.model.Item.Item;
import com.bidAndWin.model.bid.Bid;
import com.bidAndWin.model.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email",name = "unique_email_constraint"),
                @UniqueConstraint(columnNames = "phone",name = "unique_phone_constraint")
        }
)

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clientId_sequence")
    @SequenceGenerator(name = "clientId_sequence",allocationSize = 1)
    private Long clientId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String phone;

    @JsonManagedReference("client-address")
    @OneToOne(mappedBy = "client",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;

    @JsonManagedReference("client-user")
    @OneToOne(mappedBy = "client",fetch = FetchType.LAZY)
    private User user;

    @JsonManagedReference("client-items")
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Item> items;

    @JsonManagedReference("client-bids")
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Bid> bids;

    public Client(){}

}
