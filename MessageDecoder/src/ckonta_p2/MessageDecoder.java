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
public class MessageDecoder {
    /**
     * the list node class class is use to assign variable that will be
     * use in linked list
     */
    private static class listNode {
        //variable to store characters on the list
        char charValue;
        //variable to store number on the list
        int val;
        //store the next value on the list
        listNode next;

        /**
         * constructor is use to assign variable
         *
         * @param num the num on file
         * @param letters characters in file
         */
        public listNode(int num, char letters) {
            //assigning number variable
            val = num;
            //assign characters variables
            charValue = letters;
            //assigning next to null
            next = null;
        }
    }

    /**
     * the function will read file contains and store characters in a linked list
     *
     * @param file file name
     * @return function with root
     * @throws IOException throws exception
     */
    public String scanFile(File file)throws IOException {
        //assigning number
        int num;
        //store character in file
        char inLine;
        //store value in line
        String line;

        //create a scanner object
        Scanner inFile = new Scanner(file);
        //create an empty list
        listNode root = null;
        //while loop to read file
        while (inFile.hasNextLine()) {
            //reading the entire line
            line = inFile.nextLine();

            //create scanner to read line
            Scanner readLine = new Scanner(line);
            //check file line for char
            if (readLine.hasNext()) {
                //assigning char in file
                inLine = line.charAt(0);
                //check for not characters
                if(inLine != ' '){
                    readLine.next();
                }
                //check for int in file
                if (readLine.hasNextInt()) {
                    //assigning int in file
                    num = readLine.nextInt();
                    //passing variable check in sortInOrder function to sort based on numbers
                    root = sortInOrder(num, inLine, root);
                }
            }
        }
        //close file reader
        inFile.close();
        //return getPlainTextMessage with root as an argument
        return getPlainTextMessage(root);

    }

    /**
     * the sort function is use to sort file contain based on numbers associate
     * with spaces and characters
     *
     * @param value number in file
     * @param charValue characters in file
     * @param root linked list root
     * @return root
     */
    public listNode sortInOrder(int value, char charValue, listNode root) {
        //check if root equal to nothing
        if (root == null) {
            //return linked list
            return new listNode(value, charValue);
        }
        //check if root value is greater than value
        if (root.val > value) {
            //create a linked list object
            listNode newRood = new listNode(value, charValue);
            //assign new next root to root
            newRood.next = root;
            //retunr new root
            return newRood;
        }
        //assign next root to sortInOrder function with char, num, next root as argument
        root.next = sortInOrder(value, charValue ,root.next);
        //return root
        return root;
    }
    /**
     * this function will print file contain in plain text
     *
     * @param root list node root
     * @return string
     */
    public String getPlainTextMessage(listNode root) {
        //create a string builder object
        StringBuilder str = new StringBuilder();
        while (root != null) {
            //append char to make string
            str.append(root.charValue);
            //move through the file content
            root = root.next;
        }
        //return string
        return str.toString();
    }
}
