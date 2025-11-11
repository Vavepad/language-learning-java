package comp102x.project.task;

import comp102x.project.model.GameRecord;

public class Util {
    public Util() {
    }

    public static void sort(GameRecord[] records) {
        int remaining = records.length;

        while(remaining > 1) {
            int minIndex = minPos(records, remaining);
            --remaining;
            swap(records, minIndex, remaining);
        }

    }

    private static int minPos(GameRecord[] records, int size) {
        int minIndex;
        if(records.length == size) {
            minIndex = size - records.length;
        } else {
            minIndex = 0;
        }

        for(int i = 1; i < size; ++i) {
            if(records[i].getLevel() < records[minIndex].getLevel()) {
                minIndex = i;
            } else if(records[i].getLevel() == records[minIndex].getLevel() && records[i].getScore() <= records[minIndex].getScore()) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private static void swap(GameRecord[] array, int index1, int index2) {
        GameRecord temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
