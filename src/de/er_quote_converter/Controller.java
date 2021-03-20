package de.er_quote_converter;
import java.io.*;


public class Controller {
    public static void main(String[] args) {
        GUI gui = new GUI();
        
    }
}

class FileAccess {
    
    static String readTextFromFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String text = "";
        String line = null;
        while((line = br.readLine()) != null) {
            text = text.concat(line);
        }
        br.close();
        return text;
    }
    
    static void writeTextToFile(String text, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(text);
        bw.close();
        System.out.println("Writing file successfull!");
    }
}