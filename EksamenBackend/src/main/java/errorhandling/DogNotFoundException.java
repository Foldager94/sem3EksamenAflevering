/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author ckfol
 */
public class DogNotFoundException extends Exception {
    int errorCode;

    public DogNotFoundException(String message,int errCode,Throwable course) {
        super(message,course);
        this.errorCode = errCode;
    }

    public DogNotFoundException(String message,int errCode) {
        super(message);
        this.errorCode = errCode;
    }
    public DogNotFoundException(String message) {
        super(message);
        this.errorCode = 400;
    }
    
        public int getErrorCode() {
        return errorCode;
    } 
}
