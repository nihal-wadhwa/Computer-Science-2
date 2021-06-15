package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Counter
{
    public static void main (String[] args) throws IOException {
        FileInputStream inStream = null;

        try {
            inStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int value = inStream.read();
    }
}
