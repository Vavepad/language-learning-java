import comp102x.Canvas;
import comp102x.ColorImage;
/**
 * A simple demo on Procedural vs Event Driven Programming
 */
public class HappyTCDemo
{
    private Canvas canvas = new Canvas();
    ColorImage image1 = new ColorImage("happyFace.png");
    ColorImage image2 = new ColorImage("tc.jpg");
    
    public HappyTCDemo() {
        canvas.add(image2, 200, 200);
        image1.setScale(2);
        canvas.add(image1);
    }
    
    public void moveHappyFace(int x, int y){
        image1.setX(x);
        image1.setY(y);
    }

    public static void main(String args[])
    {
        HappyTCDemo hd = new HappyTCDemo();
        hd.moveHappyFace(200,200);
    }
}
