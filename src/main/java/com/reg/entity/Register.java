package com.reg.entity;


import java.security.SecureRandom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Register {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String password;
    private String email;
    private String registrationId;

    @PrePersist
    public void generateRegistrationId() {
        this.registrationId = generateRandomNumber(6);
    }

    private String generateRandomNumber(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Generates a digit from 0 to 9
        }
        return sb.toString();
    }
}
