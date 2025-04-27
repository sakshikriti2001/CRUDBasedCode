package com.crud.code.hospital.dto;

public class UpdatePatientDto {

    private Integer age;
    private String illness;
    private String modifiedBy;

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
}
