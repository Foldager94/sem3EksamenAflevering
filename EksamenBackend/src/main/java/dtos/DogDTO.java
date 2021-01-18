/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Dog;
import entities.User;

/**
 *
 * @author ckfol
 */
public class DogDTO {
    private int id;
    private String name;
    private String dateOfBirth;
    private String info;
    private String breed;
    private String user;

    public DogDTO(String name, String dateOfBirth, String info, String breed, String user) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.breed = breed;
        this.user = user;
    }
    
        public DogDTO(String name, String dateOfBirth, String info, String breed) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.breed = breed;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public DogDTO(Dog dog){
        this.id = dog.getId();
        this.name = dog.getName();
        this.dateOfBirth = dog.getDateOfBirth();
        this.info = dog.getInfo();
        this.breed = dog.getBreed();
        this.user = dog.getUser().toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInfo() {
        return info;
    }

    public String getBreed() {
        return breed;
    }

    public String getUser() {
        return user;
    }
    
    
    
}
