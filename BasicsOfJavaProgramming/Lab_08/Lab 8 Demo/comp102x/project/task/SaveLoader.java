package comp102x.project.task;

import comp102x.project.model.GameRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveLoader {
    public SaveLoader() {
    }

    public void saveGameRecords(GameRecord[] records, String filename) throws FileNotFoundException {
        filename.toLowerCase();
        PrintWriter writer = new PrintWriter(new File(filename));

        for(int i = 0; i < records.length; ++i) {
            writer.println(records[i].getName() + "\t" + records[i].getLevel() + "\t" + records[i].getScore());
        }

        writer.close();
    }

    public GameRecord[] loadGameRecords(String filename) throws FileNotFoundException {
        filename.toLowerCase();
        GameRecord[] temp = new GameRecord[30];
        int numberOfRecords = 0;

        Scanner scanner;
        int i;
        for(scanner = new Scanner(new File(filename)); scanner.hasNextLine(); ++numberOfRecords) {
            String record = scanner.nextLine();
            String name = record.split("\t")[0];
            i = Integer.parseInt(record.split("\t")[1]);
            int score = Integer.parseInt(record.split("\t")[2]);
            temp[numberOfRecords] = new GameRecord(name, i, score);
        }

        scanner.close();
        GameRecord[] ret = new GameRecord[numberOfRecords];

        for(i = 0; i < numberOfRecords; ++i) {
            ret[i] = temp[i];
        }

        return ret;
    }
}
