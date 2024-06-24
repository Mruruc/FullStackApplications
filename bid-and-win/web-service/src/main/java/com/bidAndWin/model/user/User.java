package com.bidAndWin.model.user;

import com.bidAndWin.model.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

@Entity
@Table(name = "users",
        uniqueConstraints = {
             @UniqueConstraint(columnNames = "username",name = "unique_constraint_userName")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userId_sequence")
    @SequenceGenerator(name = "userId_sequence",allocationSize = 1)
    private Long userId;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private Boolean isAccountLocked;
    @Column(nullable = false)
    private Long failedLoginAttempts;
    @Column(nullable = false)
    private LocalDate lastModified;

    @JsonBackReference("client-user")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",unique = true)
    private Client client;

    public User(){}
}
