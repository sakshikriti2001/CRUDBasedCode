package com.crud.code.hospital.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class PatientDTO {

    private String name;
    private Integer age;
    private String illness;
    private String gender;
    public String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PatientDTO(String name, Integer age, String illness, String gender, String email) {
        this.name = name;
        this.age = age;
        this.illness = illness;
        this.gender = gender;
        this.email = email;
    }
}
