import java.io.*;
import java.util.Scanner;
import comp102x.IO;

public class ScannerForConsole {

    public void readStudentNamesFromFile() throws IOException {
        // 1. Create a File and Scanner objects
        File inputFile = new File("studentnames.txt");
        Scanner input = new Scanner(inputFile);

        // 2. read the content using a loop
        for (int i=0; input.hasNextLine(); i++) {
            String inputStudentName = input.nextLine();
            IO.outputln("Student #" + i + ": " + inputStudentName);
        }
        //3. close the file and print the result
        input.close();

    }

     public void readNamesFromConsole() throws IOException {
        // 1. Create a Scanner object for standard input
        Scanner input = new Scanner(System.in);
        int nStudents = 0;
        // 2. read the content from standard input using a loop
        while (true) {  
              String inputName = input.nextLine();  

              if (inputName.equals("")) break;
              
              IO.outputln("Student #" + nStudents + ": " + inputName);                           
              nStudents++;
        }    
     }
    public static void main(String args[])
    {
        ScannerForConsole sc = new ScannerForConsole();
        try {
            sc.readNamesFromConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}