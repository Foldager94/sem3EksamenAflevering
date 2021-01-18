/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BreedDTO;
import dtos.DogDTO;
import entities.Dog;
import entities.User;
import errorhandling.UserDoesNotExistException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author ckfol
 */
public class DogFacadeTest {
    
    private static EntityManagerFactory emf;
    private static DogFacade facade;
    
    public DogFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = DogFacade.getDogFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Dog").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            User user1 = new User("UserTest1", "TestUser1");
            User user2 = new User("UserTest2", "TestUser2");
            Dog dog1 = new Dog("Fido", "20/11/1999", "Sød Hund", "Blanding", user1);
            Dog dog2 = new Dog("Chess", "15/08/2001", "Sød Hund", "Blanding", user1);
            Dog dog3 = new Dog("Kira", "06/04/2010", "Sød Hund", "Blanding", user2);
            Dog dog4 = new Dog("Oliver", "01/07/2007", "Sød Hund", "Blanding", user2);
            user1.addDog(dog1);
            user1.addDog(dog2);
            user2.addDog(dog3);
            user2.addDog(dog4);
            em.persist(user1);
            em.persist(user2);
            em.persist(dog1);
            em.persist(dog2);
            em.persist(dog3);
            em.persist(dog4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDogFacade method, of class DogFacade.
     */


    /**
     * Test of addDog method, of class DogFacade.
     */
    @Test
    public void testAddDog() throws UserDoesNotExistException {
        System.out.println("addDog");
        DogDTO dogDTO = new DogDTO("Lassy", "09/01/2014", "sød hund", "blanding");
        String user = "UserTest1";
        int expResult = 13;
        DogDTO result = facade.addDog(dogDTO, user);
        assertEquals(expResult, result.getId(), "Id given in the db should be 5");
    }

    /**
     * Test of getDogsByUser method, of class DogFacade.
     */
    @Test
    public void testGetDogsByUser() throws UserDoesNotExistException {
        System.out.println("getDogsByUser");
        String userName = "UserTest2";
        int expResult = 2;
        List<DogDTO> result = facade.getDogsByUser(userName);
        assertEquals(expResult, result.size());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of editDog method, of class DogFacade.
     */
    @Test
    public void testEditDog() throws Exception {

        System.out.println("editDog");
        DogDTO dogDTO = new DogDTO("TestEdit", "TestEdit", "TestEdit", "TestEdit");
        dogDTO.setId(16);
        DogDTO expResult = dogDTO;
        DogDTO result = facade.editDog(dogDTO);
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getBreed(), result.getBreed());

    }

    /**
     * Test of deleteDog method, of class DogFacade.
     */
    @Test
    public void testDeleteDog() throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("deleteDog");
        DogDTO dogDTO = new DogDTO("TestEdit", "TestEdit", "TestEdit", "TestEdit");
        dogDTO.setId(2);
        String thisUser = "UserTest1";
        facade.deleteDog(dogDTO, thisUser);
        Dog result = em.find(Dog.class, 2);
        em.close();
        assertEquals(null, result);
    }

    
}
