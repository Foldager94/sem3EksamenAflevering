/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.DogDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ckfol
 */
@Entity
@Table(name = "dogs")
public class Dog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String dateOfBirth;
    private String info;
    private String breed;
    @ManyToOne
    @JoinColumn(name="user", nullable=false)
    private User user;

    public Dog() {
    }
    
    public Dog(DogDTO dogDto) {
        this.name = dogDto.getName();
        this.dateOfBirth = dogDto.getDateOfBirth();
        this.info = dogDto.getInfo();
        this.breed = dogDto.getBreed();
    }

    public Dog(String name, String dateOfBirth, String info, String breed, User user) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.breed = breed;
        this.user = user;
    }

 
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getDogList().add(this);
    }



    
}
