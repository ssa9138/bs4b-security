package at.fhv.epa.gameservice.application.exception;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
