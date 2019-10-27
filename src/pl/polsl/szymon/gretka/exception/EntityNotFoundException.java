package pl.polsl.szymon.gretka.exception;

/**
 * Custom exception for handling EntityNotFoundException
 * @author Szymek
 */
public class EntityNotFoundException extends Exception {
    
    /**
     * Constructor 
     */
    public EntityNotFoundException(){
        super("Entity Not Found!");
    }
}