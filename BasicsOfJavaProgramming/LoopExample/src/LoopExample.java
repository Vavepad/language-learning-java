import comp102x.IO;

public class LoopExample {

    public static int factorial(int number) {
        int t = 1; // initialize t to 1
        int counter = 1; // initialize counter to 1
        while(counter <= number){
            t = t * counter;
            counter = counter + 1;
        }
        return t;
    }

    public static int powerTwo(int number) {
        int t = 1; // initialize t to 1
        int counter = 1; // initialize counter to 1
        while(counter <= number) {
            t = t * 2;
            counter = counter + 1;
        }
        return t;
    }

    public static void main(String[] args){
        int number=1;

        while(number!=0){
IO.outputln("Enter a number: ");
number=IO.inputInteger();

int f = factorial(number);
int p = powerTwo(number);
IO.outputln("n!= "+f+" 2 to the n = "+p);
        }
    }
}
