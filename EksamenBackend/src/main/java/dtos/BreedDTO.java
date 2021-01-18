/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author ckfol
 */
public class BreedDTO {
    private int id;
    private String breed;
    private String info;
    private String wikipedia;
    private String Image;
    private String facts;

    public BreedDTO(String breed, String info, String wikipedia, String Image, String facts) {
        this.breed = breed;
        this.info = info;
        this.wikipedia = wikipedia;
        this.Image = Image;
        this.facts = facts;
    }

    public String getBreed() {
        return breed;
    }

    public String getInfo() {
        return info;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getImage() {
        return Image;
    }

    public String getFacts() {
        return facts;
    }
    
    
}
