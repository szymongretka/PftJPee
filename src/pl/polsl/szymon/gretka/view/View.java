package pl.polsl.szymon.gretka.view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Szymon Gretka
 */
public class View {

    /**
     * Read input string
     *
     * @return string value
     * @throws InputMismatchException thrown if the given coefficient is not an
     * string
     */
    public String readString() throws InputMismatchException {
        /**
         * Scanner used for reading from standard input stream
         */
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Read input long
     *
     * @return long value
     * @throws InputMismatchException thrown if the given coefficient is not an
     * long
     */
    public Long readLong() throws InputMismatchException {
        /**
         * Scanner used for reading from standard input stream
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type ID");
        Long input = scanner.nextLong();
        return input;
    }

    /**
     * Read input integer
     *
     * @return integer value
     * @throws InputMismatchException thrown if the given coefficient is not an
     * integer
     */
    public int readInt() throws InputMismatchException {
        /**
         * Scanner used for reading from standard input stream
         */
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
