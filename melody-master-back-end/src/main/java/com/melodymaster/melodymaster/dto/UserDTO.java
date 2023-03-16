package com.melodymaster.melodymaster.dto;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profileImage;
    private LocalDateTime registrationDate;
    private Boolean isPremiumMember;

    // constructors, getters and setters

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, LocalDateTime registrationDate, Boolean isPremiumMember) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.isPremiumMember = isPremiumMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsPremiumMember() {
        return isPremiumMember;
    }

    public void setIsPremiumMember(Boolean isPremiumMember) {
        this.isPremiumMember = isPremiumMember;
    }
}