package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserDoesNotExistExceptionMapper implements ExceptionMapper<UserDoesNotExistException> 
{
    @Context 
    ServletContext context;
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();   
    @Override
    public Response toResponse(UserDoesNotExistException ex) {
       Logger.getLogger(UserDoesNotExistExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
       ExceptionDTO err = new ExceptionDTO(ex.getErrorCode(),ex.getMessage());
       return Response
               .status(ex.getErrorCode())
               .entity(gson.toJson(err))
               .type(MediaType.APPLICATION_JSON)
               .build();
	}
}