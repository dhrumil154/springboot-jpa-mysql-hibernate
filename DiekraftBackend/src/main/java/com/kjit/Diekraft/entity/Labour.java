
package com.kjit.Diekraft.entity;

import javax.persistence.*;

@Entity
@Table(name = "labour")
public class Labour {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer experiance;

    @Column
    private byte[] image;

    @Column
    private Boolean isHindi;

    @Column
    private Boolean isGujarati;

    @Column
    private Boolean isEnglish;

    @Column
    private String availabilityStatus;

    @ManyToOne
    @JoinColumn(referencedColumnName = "name" , updatable = false)
    private Profession profession;

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "name" , updatable = false)
    private Dept dept;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Dept getDept() { return dept; }

    public void setDept(Dept dept) { this.dept = dept; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public Integer getExperiance() {
        return experiance;
    }

    public void setExperiance(Integer experiance) {
        this.experiance = experiance;
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

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

