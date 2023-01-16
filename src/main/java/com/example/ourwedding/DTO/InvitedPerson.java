package com.example.ourwedding.DTO;

import jakarta.persistence.*;


@Entity
@Table(name = "invited_person")
public class InvitedPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="fullName")
    private String fullName;
    @Column(name="contact")
    private String  contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public InvitedPerson(String fullName, String contact) {
        this.fullName = fullName;
        this.contact = contact;
    }

    public InvitedPerson() {
    }

    @Override
    public String toString() {
        return "InvitedPerson{" +
                "fullName='" + fullName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

