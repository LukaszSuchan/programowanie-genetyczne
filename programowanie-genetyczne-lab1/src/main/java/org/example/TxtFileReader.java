package org.example;

import java.io.*;

public class TxtFileReader {

    public static String read(String fileName) throws IOException {
        File file = new File(
                "programowanie-genetyczne-lab\\src\\main\\resources\\" + fileName);

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        StringBuilder txt = new StringBuilder();
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
        {
            txt.append(st);
        }

        return txt.toString();
    }
}
