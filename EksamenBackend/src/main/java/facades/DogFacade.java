/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.nimbusds.jose.shaded.json.parser.ParseException;
import dtos.BreedDTO;
import dtos.DogDTO;
import entities.Dog;
import entities.User;
import errorhandling.DogNotFoundException;
import errorhandling.UserDoesNotExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ckfol
 */
public class DogFacade {
    
    private static EntityManagerFactory emf;
    private static DogFacade instance;

    private DogFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static DogFacade getDogFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();
        }
        return instance;
    }
    
    public DogDTO addDog(DogDTO dogDTO, String user) throws UserDoesNotExistException {
        EntityManager em = emf.createEntityManager();
        Dog dog = new Dog(dogDTO);
        try{
            User userObject = em.find(User.class, user);
            if(userObject == null)
            throw new UserDoesNotExistException("User does not exist in the Database", 500);
            dog.setUser(userObject);
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        return new DogDTO(dog);
    }
    
    public List<DogDTO> getDogsByUser(String userName) throws UserDoesNotExistException{
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userName);
        if(user == null)
            throw new UserDoesNotExistException("User does not exist in the Database", 500);
        List<DogDTO> dogList = new ArrayList<>();
        user.getDogList().forEach(dog -> {
            dogList.add(new DogDTO(dog));
        });
        em.close();
        
        return dogList;
    }
    
    public DogDTO editDog(DogDTO dogDTO) throws DogNotFoundException{
        EntityManager em = emf.createEntityManager();
        Dog dog = em.find(Dog.class, dogDTO.getId());
        if(dog == null)
            throw new DogNotFoundException("dog object is null. Problem is in the front end", 500);
        try{
        em.getTransaction().begin();
        dog.setName(dogDTO.getName());
        dog.setBreed(dogDTO.getBreed());
        dog.setInfo(dogDTO.getInfo());
        dog.setDateOfBirth(dogDTO.getDateOfBirth());
        em.getTransaction().commit();
        }finally{
            em.close();
        }
        return new DogDTO(dog);
    }
    
    public void deleteDog(DogDTO dogDTO, String thisUser) throws DogNotFoundException{
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, thisUser);
        Dog dog = em.find(Dog.class, dogDTO.getId());
        if(dog == null)
            throw new DogNotFoundException("dog object is null. Problem is in the front end", 500);
        try{
            em.getTransaction().begin();
            em.remove(dog);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    public List<BreedDTO> getListOfBreeds() throws IOException, ParseException{
        DataFetcherFacade facade = DataFetcherFacade.getDataFetcherFacade();
        return facade.getBreeds();
    }
   
    public BreedDTO getBreedDetails(String breed) throws IOException{
        DataFetcherFacade facade = DataFetcherFacade.getDataFetcherFacade();
        return facade.getBreedDetails(breed);
    }
}

