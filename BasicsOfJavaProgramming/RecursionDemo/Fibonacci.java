import comp102x.IO;

//Calculate Fibonacci numbers using recursive method
public class Fibonacci
{
    static int fib(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        return (fib(n-1) + fib(n-2));
    }
    public static void main(String[] args) {
        IO.output("Enter the value n: ");
        int n = IO.inputInteger();
        int fibN = fib(n);
        IO.outputln("Fib(" + n + ") = " + fibN) ;
    }
}
