import comp102x.IO;

public class BinSearch {
    int[] data;
    int nTimes=0;

    public int binSearch(int[] data, int lower, int upper, int value)
    {
        nTimes++;
        int middle = (lower + upper) / 2;
        if (data[middle] == value)
            return middle;
        else if (lower >= upper)
            return -1;
        else if (value < data[middle])
            return binSearch(data, lower, middle-1, value);
        else
            return binSearch(data, middle+1, upper, value);
    }

    public void demo(int value){
        nTimes=0;
        data = new int[1000];
        int size = data.length;
        for(int i=0; i<size; i++){
            data[i]=i;
        }
        IO.outputln("Value is found at the index: " + binSearch(data, 0, size-1, value));
        IO.outputln("Number of calls: " + nTimes);
    }

    public static void main(String[] args) {
        IO.outputln("Enter value: ");
        int value=IO.inputInteger();
        BinSearch bs = new BinSearch();
        bs.demo(value);
    }
}
