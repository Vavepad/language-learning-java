import comp102x.IO;

public class Main {
    public static void main(String[] args)
    {
        String owner;
        IO.outputln("Enter name of owner: ");
        owner = IO.inputString();
        Car car1=new Car(owner);
        int dist;
        IO.outputln("Enter dist: ");
        dist=IO.inputInteger();
        car1.moveCar(dist);
        IO.outputln("odometer: "+car1.getOdometer());
        double angle;
        IO.outputln("Enter angle: ");
        angle=IO.inputDouble();
        car1.turnCar(angle);
    }
}
