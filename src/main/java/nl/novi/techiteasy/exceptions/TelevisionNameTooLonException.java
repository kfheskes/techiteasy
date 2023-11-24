package nl.novi.techiteasy.exceptions;

public class TelevisionNameTooLonException extends RuntimeException {
    public TelevisionNameTooLonException (String message){
        super(message);
    }

    public TelevisionNameTooLonException(){
        super();
    }
}
