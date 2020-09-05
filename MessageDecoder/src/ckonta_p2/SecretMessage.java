package ckonta_p2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * his program reads an encoded message from a file supplied by the user and
 * displays the contents of the message before it was encoded.
 *
 * @author cheick
 * @version 1.0
 */
public class SecretMessage {
    /**
     * the main function print the the coded message in plain text by calling other
     * function.
     *
     * @param args contain command line argument
     * @throws IOException after opening file
     */
    public static void main(String[] args) throws IOException {

        //declaring variable for user inout
        String line;
        //declaring variable to store user input
        char choice;

        //create a scanner object
        Scanner input = new Scanner(System.in);

        //call welcome function
        welcome();
        //while loop to repeat the program
        do {

            //calling function to decode file
            getDecodFile();

            //print new line
            System.out.println();
            //prompt if user will like to try again
            System.out.print("Would you like to try again? (Y or N): ");
            //store user input
            line = input.next();
            //store input char
            choice = line.charAt(0);

        } while (choice == 'y' || choice == 'Y');

        //print new line
        System.out.println();

        //calling thank you function
        thanks();
        //close Scanner
        input.close();


    }

    /**
     * this function will collect file name from user and pass it into
     * MessageDecoder class to decrypt file content
     *
     * @throws IOException after file reading complete
     */
    public static void getDecodFile() throws IOException {

        //create a scanner object
        Scanner input = new Scanner(System.in);
        //assigning return value from isFileValid
        boolean exists;
        //store user input
        String fileName;

        do {

            //prompt user to enter file name
            System.out.print("\nEnter secret file name: ");
            //store user input
            fileName = input.next();
            //consuming line
            input.nextLine();

            //check if file exist
            exists = isValideFile(fileName);
            //check if file exist
            if (exists) {
                //create a file object
                File file = new File(fileName);
                //create class object
                MessageDecoder decoder = new MessageDecoder();
                //print coded message
                System.out.println("Decoder: " + decoder.scanFile(file));
            }
            //if file doesn't exist repeat program
        } while (!exists);

    }

    /**
     * /**
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     *
     * @param filename file name string to check
     * @return true if file exists on disk and is not a directory
     */
    private static boolean isValideFile(String filename) {
        //create a scanner object
        Scanner input = new Scanner(System.in);
        //check file path
        File path = new File(filename);
        //assigning value return
        boolean isValid = path.exists() && !path.isDirectory();
        //check if file not exist
        if (!isValid) {
            //prompt user to re-enter the file name
            System.out.print("Please enter a correct file name: ");
            filename = input.next();
            isValid = isValideFile(filename);
        }
        //return is valid after checking file path
        return isValid;
    }

    /**
     * output welcome message
     */
    public static void welcome() {
        //print new line
        System.out.println();
        //print program title
        System.out.println("This program reads an encoded message from a file supplied by the user and\n" +
                "displays the contents of the message before it was encoded.");
    }

    /**
     * Print thank you note
     */
    public static void thanks() {
        //print closing message after user is done using the program
        System.out.println("Thank you for using the message decoder.");
    }
}
