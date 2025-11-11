import comp102x.Canvas;
import comp102x.ColorImage;
import comp102x.IO;

public class Car2 {
    String owner = "NoName";
    ColorImage carImage = new ColorImage("Car1.png");
    double gasMileage = 10.0D;
    double gasInTank = 10.0D;

    public Car2() {
    }

    public Car2(String nameOfOwner) {
        this.owner = nameOfOwner;
        this.carImage = new ColorImage();
    }

    public Car2(String nameOfOwner, double newGasMileage) {
        this.owner = nameOfOwner;
        this.carImage = new ColorImage();
        this.gasMileage = newGasMileage;
    }

    public void moveForward(int dist) {
      /*
        int rotationInDegrees = this.carImage.getRotation();
        double rotationInRadian = (double)rotationInDegrees * 3.141592653589793D / 180.0D;
        double distX = Math.cos(rotationInRadian) * (double)dist;
        double distY = Math.sin(rotationInRadian) * (double)dist;
        this.carImage.setX(this.carImage.getX() + (int)distX);
        this.carImage.setY(this.carImage.getY() + (int)distY);
        */
      for(int i=1; i<=dist; i++){
          carImage.setX(carImage.getX()+1);
          pause(10);
      }
        double distKm = (double)dist / 100.0D;
        double gasUsed = distKm / 100.0D * this.gasMileage;
        this.gasInTank -= gasUsed;
        IO.outputln("Amount of gas used: " + gasUsed + ", gas remained: " + this.gasInTank);
    }

    public static void pause(int sleepTime){
        try{
            Thread.sleep(sleepTime);
        }catch(Exception ex){
            System.exit(-1);
        }
    }

    public void makeTurn(int angle) {
        this.carImage.setRotation(this.carImage.getRotation() + angle);
    }

    public void addGas(double gasToAdd) {
        this.gasInTank += gasToAdd;
    }

    public void car2Demo() {
        Canvas canvas = new Canvas();
        canvas.add(this.carImage, 200, 200);
    }

    public static void main(String[] args)
    {
        Car2 car = new Car2();
        car.car2Demo();
        int dist=1;
        while(dist!=0){
            IO.outputln("Enter dist: ");
            dist=IO.inputInteger();
            car.moveForward(dist);
        }

    }
}
