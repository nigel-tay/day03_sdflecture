package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        // Retrieving arguments when running java code from command line
        String directoryPath = args[0];
        String fileName = args[1];
        String dirPathFileName = directoryPath + File.separator + fileName; // File.separator allows Java to determine the separator, \\ for MacOS and \ for Windows
        // java sg.edu.nus.iss/App \\data myfile.txt
        // if (args.length > 0) {
        //     for(int i = 0; i < args.length; i++) {
        //         if (i == 0) {

        //             System.out.println(args[i]);
        //         }
        //     }
        // }
        // else {
        //     System.out.println("No arguments passed");
        // }

        // Checking folder and creating folder
        File newDirectory = new File(directoryPath);
        if (!newDirectory.exists()) {
            System.out.println("This directory does not exist proceeding to create directory...");
            newDirectory.mkdir();
        }
        else {
            System.out.println("Directory exists/has already been created.");
        }

        // Checking for file and creating file
        File newFile = new File(dirPathFileName);
        if (newFile.exists()) {
            System.out.println("This file already exists.");
        }
        else {
            newFile.createNewFile();
        }

        String content = "\nGood morning";
        String content2 = "\nGood afternoon";

        // fileWrite append argument, if true will append, if flase will overwrite all data in the file
        FileWriter fileWriter = new FileWriter(dirPathFileName, true);
        fileWriter.write(content);
        fileWriter.write(content2);
        fileWriter.flush();
        fileWriter.close();


        // Example 2
        String content3 = "\nGood night";
        FileWriter fileWriter2 = new FileWriter(dirPathFileName, true);
        BufferedWriter bw = new BufferedWriter(fileWriter2);
        bw.append(content3); // Appends eadch individual character in the string
        bw.flush();
        bw.close();
        fileWriter2.close(); // Remember to close in sequence

        // Example 3
        String content4 = "\nGood evening";
        FileOutputStream fos = new FileOutputStream(dirPathFileName, true);
        byte[] byteContent = content4.getBytes(); // Because fos.write() takes in a byte array as param, we have to convert it first
        fos.write(byteContent);
        fos.flush();
        fos.close();

        // Example 4 - With decorator pattern
        String content5 = "\nZao an";
        FileOutputStream fos2 = new FileOutputStream(dirPathFileName, true);
        DataOutputStream dos2 = new DataOutputStream(fos2);
        dos2.writeBytes(content5);
        dos2.flush();
        dos2.close();
        fos2.close();

        /**
         * Reading from file example
         */

        /**
         * We can also do it like this
         * File file2 = new File(dirPath + File.separator + fileName);
         * FileReader fr = new FileReader(file2);
         */
        FileReader fr = new FileReader(dirPathFileName);
        int dataRead = fr.read();
        while(dataRead != -1) {
            System.out.print((char) dataRead);
            dataRead = fr.read();
        }
        fr.close();
        
        // Example 2 - Using BufferedReader
        FileReader fr2 = new FileReader(dirPathFileName);
        BufferedReader br = new BufferedReader(fr2);
        String line = "";
        line = br.readLine();
        
        while(line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
        fr2.close();

        // Example 3 - FileInputStream and DataInputStream - decorator
        FileInputStream fis = new FileInputStream(dirPathFileName);
        DataInputStream dis = new DataInputStream(fis);
        // String result = dis.readUTF();
        // System.out.println(result);
        int disData = dis.read();

        // -1 because integer, null only for string
        while(disData != -1) { 
            System.out.println((char) disData);
            disData = dis.read();
        }
        dis.close();
        fis.close();
    }
}
