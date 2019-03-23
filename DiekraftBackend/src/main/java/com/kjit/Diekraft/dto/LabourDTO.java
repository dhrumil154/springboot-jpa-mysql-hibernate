package com.kjit.Diekraft.dto;

import com.kjit.Diekraft.entity.User;

public class LabourDTO extends UserDTO {

    private String profession;

    private Integer experiance;

    private String availabilityStatus;

    private Boolean isHindi = false;

    private Boolean isGujarati = false;

    private Boolean isEnglish = false;

    private String department;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getExperiance() {
        return experiance;
    }

    public void setExperiance(Integer experiance) {
        this.experiance = experiance;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Boolean isHindi() {
        return isHindi;
    }

    public void setHindi(Boolean hindi) {
        isHindi = hindi;
    }

    public Boolean isGujarati() {
        return isGujarati;
    }

    public void setGujarati(Boolean gujarati) {
        isGujarati = gujarati;
    }

    public Boolean isEnglish() {
        return isEnglish;
    }

    public void setEnglish(Boolean english) {
        isEnglish = english;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
