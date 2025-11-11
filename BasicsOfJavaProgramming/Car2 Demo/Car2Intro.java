import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;

/**
 * Write a description of class Cars here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car2Intro {
    private String owner = "NoName";
    ColorImage carImage = new ColorImage("Car1.png");
    private double gasMileage = 10.0; // Liters for every 100km
    private double gasInTank = 10.0;

    public Car2Intro () {}

    public Car2Intro(String nameOfOwner)
    {
        owner = nameOfOwner;
        carImage = new ColorImage();
    }

    public Car2Intro (String nameOfOwner, double newGasMileage)
    {
        owner = nameOfOwner;
        carImage = new ColorImage( );
        gasMileage = newGasMileage;
    }
    public void moveForward(int dist) {
        // Change the X position of car from current X postion plus dist
        carImage.setX(carImage.getX() + dist); // Update the amount of gas in tank
        double gasUsed = dist / 100.0 * gasMileage;
        gasInTank = gasInTank - gasUsed;
        IO.outputln("Amount of gas used: " + gasUsed + ", gas remained: " + gasInTank);
    }
    public void makeTurn(int angle)
    {
        // Change the orientation of car from current orientation plus angle
        carImage.setRotation(carImage.getRotation() + angle);
    }

    // addGas adds an amount of gas equal to gasToAdd to gasInTank
    public void addGas(double gasToAdd) {
        //gasInTank = gasInTank + gasUsed;
        gasInTank = gasInTank + gasToAdd;
        IO.outputln("Amount of gas: " + gasInTank);
    }

    public void car2Demo()
    {
        Canvas canvas = new Canvas();
        canvas.add(carImage,200,200);
    }
    public static void main(String[] args)
    {
        Car2Intro car = new Car2Intro();
        car.car2Demo();
        car.moveForward(2);
        car.addGas(10);
        car.makeTurn(90);
        car.moveForward(100);
    }
}
