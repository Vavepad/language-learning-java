import comp102x.IO;

public class HandShakeDemo {
    public static int handShake(int n){
        if(n <= 2)
            return n - 1;
        else
            return handShake(n-1) + (n-1);
    }

    public static void main(String[] args){
        IO.outputln("Enter n: ");
        int n = IO.inputInteger();
        IO.outputln(handShake(n));
    }

}
