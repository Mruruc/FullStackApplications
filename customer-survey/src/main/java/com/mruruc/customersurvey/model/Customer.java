package com.mruruc.customersurvey.model;

import jakarta.persistence.*;

@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName="student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator="student_sequence",
            strategy= GenerationType.SEQUENCE
    )
    private int id;
    @Column
    private String name;
    @Column(name="last_name")
    private String last_name;
    @Column
    private Integer age;
    @Column
    private String email;


    public Customer(String name, String last_name, Integer age, String email) {
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.email = email;
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
