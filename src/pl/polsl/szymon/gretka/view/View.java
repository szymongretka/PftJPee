package pl.polsl.szymon.gretka.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.szymon.gretka.exception.SimpleException;

/**
 *
 * @author Szymek
 */
public class View {
    /**
     * Scanner used for reading from standard input stream
     */
    //private final Scanner scanner = new Scanner(System.in);

    /**
     * Read input number
     * @return coefficient value
     * @throws InputMismatchException thrown if the given coefficient is not an integer
     */
    public String readString() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public Long readLong() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type ID");
        Long input = scanner.nextLong();
        return input;
    }
    
    public int readInt() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type number");
        int input = scanner.nextInt();
        return input;
    }

    /**
     * Displays message on standard output
     *
     * @param message message to be displayed
     */
    public void print(String message) {
        System.out.println(message);
    }
}
