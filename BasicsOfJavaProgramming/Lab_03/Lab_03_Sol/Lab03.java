import comp102x.ColorImage;
import comp102x.Canvas;

public class Lab03
{  
    
    public void loadAnImage() 
    {
        ColorImage imageA = new ColorImage(); // Load the image using the default constructor.
        int width = imageA.getWidth(); // Getting the width of the image.
        int height = imageA.getHeight(); // Getting the height of the image.
        Canvas canvas = new Canvas(width, height); // Create a canvas with the size "width x height".
        canvas.add(imageA); // Add the image to the canvas for showing on screen. If no position is specified, the add method by default add the image to (0, 0).
        //canvas.add(imageA, 0, 0); // Alternatively, we can specify the exact position to be (0, 0).
    }
    
    public void loadTwoImagesOnTheSameCanvas()
    {
        ColorImage imageA = new ColorImage();
        ColorImage imageB = new ColorImage();
        int width = imageA.getWidth();
        int height = imageA.getHeight();
        Canvas canvas = new Canvas(width * 2, height); // Notice that the width of the canvas should be twice the width of the
        canvas.add(imageA); 
        canvas.add(imageB, width, 0); // Add imageB to the canvas on the right side of imageA.
    }
    
    public void imageAddition() 
    {    
        ColorImage imageA = new ColorImage();
        ColorImage imageB = new ColorImage();
        ColorImage result = imageA.add(imageB); // Call the instance method colorImage_object1.add(colorImage_object2). 
        
        //ColorImage result = ColorImage.add(imageA, imageB); // An alternative for adding the images by using the static method ColorImage.add(colorImage_object1, colorImage_object2).
        
        int width = imageA.getWidth();
        int height = imageA.getHeight();
        Canvas canvas = new Canvas(width + 10 + width + 10 + width, height); // The width of the canvas should be 3 times the width of the image plus 20 units of space for margin.
        canvas.add(imageA);
        canvas.add(imageB, width + 10, 0); // Add image B on the right of imageA, with 10 unit of space between them.
        canvas.add(result, width + 10 + width + 10 , 0); // Add image C on the right of image B, with 10 unit of space between them.
    }
   
    public void imageMultiplication() 
    {
        ColorImage imageA = new ColorImage();
        ColorImage imageB = new ColorImage();
        ColorImage result = imageA.multiply(imageB); // instead of calling the add method, we call the multiply method
        
        //ColorImage result = ColorImage.multiply(imageA, imageB);
        
        int width = imageA.getWidth();
        int height = imageA.getHeight();
        Canvas canvas = new Canvas(width + 10 + width + 10 + width, height);
        canvas.add(imageA, 0, 0);
        canvas.add(imageB, width + 10, 0);
        canvas.add(result, width + 10 + width + 10 , 0);
    }
    
    public void changeColor()
    {
        ColorImage image = new ColorImage();
        image.increaseRed(60); // 
        image.save(); // Show the pop up dialog for saving the image.
        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        canvas.add(image);
    }
}
