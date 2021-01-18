/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import dtos.BreedDTO;
import dtos.DogFactDTO;
import dtos.ImageDTO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import utils.HttpUtils;

/**
 *
 * @author ckf
 */
public class DataFetcherFacade {
    
    private static DataFetcherFacade instance;
    private static Gson gson = new Gson();

    public DataFetcherFacade() {

    }
    
    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static DataFetcherFacade getDataFetcherFacade() {
        if (instance == null) {
            instance = new DataFetcherFacade();
        }
        return instance;
    }

    public List<BreedDTO> getBreeds() throws IOException, ParseException{
        String jsonString = HttpUtils.fetchData("https://dog-info.cooljavascript.dk/api/breed");
        Map<String, List<BreedDTO>> objects = gson.fromJson(jsonString,
            new TypeToken<Map<String, List<BreedDTO>>>(){}.getType());
        List<BreedDTO> breeds = objects.get("dogs");
        
        return breeds;
    }
    
    public BreedDTO getBreedDetails(String breed) throws IOException{
        String jsonStringDetails = HttpUtils.fetchData("https://dog-info.cooljavascript.dk/api/breed/" + breed);
        String jsonStringImage = HttpUtils.fetchData("https://dog-image.cooljavascript.dk/api/breed/random-image/" + breed);
        String jsonStringFacts = HttpUtils.fetchData("https://dog-api.kinduff.com/api/facts");
        
        
        BreedDTO breedDTODetails = gson.fromJson(jsonStringDetails, BreedDTO.class);
        ImageDTO imageDTO = gson.fromJson(jsonStringImage, ImageDTO.class);
        DogFactDTO dogFactDTO = gson.fromJson(jsonStringFacts, DogFactDTO.class);
        
        BreedDTO breedDTO = new BreedDTO(breedDTODetails.getBreed(), breedDTODetails.getInfo(),
                                         breedDTODetails.getWikipedia(), imageDTO.getImage(),
                                         dogFactDTO.getFacts().get(0));
        
        return breedDTO;
    }
    
    
    
    
    
    
    


    

}
