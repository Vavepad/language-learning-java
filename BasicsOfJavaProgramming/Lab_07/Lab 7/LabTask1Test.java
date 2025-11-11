
import comp102x.ColorImage;
import comp102x.Canvas;

public class LabTask1Test
{
   public static void test() {
    
       System.out.println("Transition effect starts.");
       
       int width = 800;
       int height = 450;
       int stripeSize = 15;
       
       ColorImage image = new ColorImage(width, height);
       Canvas canvas = new Canvas(width, height);
       canvas.add(image);
       
       LabTask1 labTask1 = new LabTask1();
       labTask1.changeScreen(image, 0, 0, width, height, stripeSize);
       
       System.out.println("Transition effect ends.");
   }
}
