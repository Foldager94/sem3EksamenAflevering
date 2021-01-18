/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import dtos.DogDTO;
import errorhandling.DogNotFoundException;
import errorhandling.UserDoesNotExistException;
import facades.DogFacade;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author ckfol
 */
@Path("dogendpoint")
public class DogResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final DogFacade FACADE = DogFacade.getDogFacade(EMF);
    
    @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;

    /**
     * Creates a new instance of DogResource
     */
    public DogResource() {
    }

    @POST
    @RolesAllowed("user")
    @Path("/addDog")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addDog(String dog) throws UserDoesNotExistException {
        String thisuser = securityContext.getUserPrincipal().getName();
        DogDTO dogDto = GSON.fromJson(dog, DogDTO.class);
        DogDTO newDogDto = FACADE.addDog(dogDto, thisuser);
        return GSON.toJson(newDogDto);
    }
    
    @POST
    @RolesAllowed("user")
    @Path("/editDog")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editDog(String dog) throws DogNotFoundException {
        String thisuser = securityContext.getUserPrincipal().getName();
        DogDTO dogDto = GSON.fromJson(dog, DogDTO.class);
        DogDTO newDogDto = FACADE.editDog(dogDto);
        return GSON.toJson(newDogDto);
    }
    
    @POST
    @RolesAllowed("user")
    @Path("/deleteDog")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String deleteDog(String dog) throws DogNotFoundException {
        String thisUser = securityContext.getUserPrincipal().getName();
        DogDTO dogDto = GSON.fromJson(dog, DogDTO.class);      
        FACADE.deleteDog(dogDto, thisUser);
        return GSON.toJson("Dog with id:" + dogDto.getId() + ". delete");
    }
    
    @GET
    @RolesAllowed("user")
    @Path("/myDogs/")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String getDogsByUser() throws UserDoesNotExistException {   
        String user = securityContext.getUserPrincipal().getName();
        return GSON.toJson(FACADE.getDogsByUser(user));
    }
    
    @GET
    @Path("/dog-breed")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAllBreeds() throws IOException, ParseException {     
        return GSON.toJson(FACADE.getListOfBreeds());
    }
    
    @GET
    @Path("/dog-breed/{breed}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String getBreedDetails(@PathParam("breed") String breed) throws IOException, ParseException {     
        return GSON.toJson(FACADE.getBreedDetails(breed));
    }
    
}
