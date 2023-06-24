package com.tutorial.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private CreateAddressRequest address; // nested object // emmbedded class // result {}

    private List<String> hobbies; // result []

    private List<CreateSocialMediaRequest> socialMedia; // nested object bentuk list [{}]

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CreateAddressRequest getAddress() {
        return address;
    }

    public void setAddress(CreateAddressRequest address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<CreateSocialMediaRequest> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<CreateSocialMediaRequest> socialMedia) {
        this.socialMedia = socialMedia;
    }

}


