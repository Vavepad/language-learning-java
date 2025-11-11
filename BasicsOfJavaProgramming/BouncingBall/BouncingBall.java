import comp102x.Canvas;
import comp102x.ColorImage;
import comp102x.IO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BouncingBall {
    private int displayWidth;
    private int displayHeight;
    private int numberOfBalls = 4;
    private int maxDisparity;
    private Canvas canvas;
    private ColorImage background = new ColorImage("images/background.jpg");
    private ColorImage[] balls;
    private Object validator;

    public BouncingBall() {
        this.background.setMovable(false);
        this.displayWidth = this.background.getWidth();
        this.displayHeight = this.background.getHeight();
        this.canvas = new Canvas(this.displayWidth, this.displayHeight);
        this.canvas.add(this.background);
        this.balls = new ColorImage[this.numberOfBalls];

        for(int i = 0; i < this.numberOfBalls; ++i) {
            this.balls[i] = new ColorImage("images/ball " + (i + 1) + ".png");
            this.balls[i].setMovable(false);
            this.balls[i].setX(this.displayWidth / 4 * (i % 2 == 0?1:3) - this.balls[i].getWidth() / 2);
            this.balls[i].setY(this.displayHeight / 4 * (i / 2 == 0?1:3) - this.balls[i].getHeight() / 2);
            this.canvas.add(this.balls[i]);
        }

        try {
            Class validatorClass = Class.forName("Validator");
            this.validator = validatorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var2) {
            var2.printStackTrace();
            System.exit(-1);
        }

    }

    private void setMaxDisparity() {
        IO.output("Enter the disparity of the image: ");
        int temp = IO.inputInteger();

        try {
            Class[] parameterClasses = new Class[]{Integer.TYPE, Integer.TYPE};
            Method method = this.validator.getClass().getMethod("validateDisparity", parameterClasses);
            Integer result = (Integer)method.invoke(this.validator, new Object[]{Integer.valueOf(temp), Integer.valueOf(this.balls[0].getWidth() / 2)});
            this.maxDisparity = result.intValue();
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var5) {
            var5.printStackTrace();
            System.exit(-1);
        }

    }

    public void animate() {
        this.setMaxDisparity();
        if(this.maxDisparity != 0) {
            ColorImage[] previousStereoImages = new ColorImage[this.numberOfBalls];

            int disparity;
            for(disparity = 0; disparity < this.numberOfBalls; ++disparity) {
                previousStereoImages[disparity] = this.balls[disparity];
            }

            disparity = 0;
            int change = -1;

            while(true) {
                for(int i = 0; i < this.numberOfBalls; ++i) {
                    this.scaleImageAtCenter(this.balls[i], 1.0D + (double)disparity / 100.0D);
                    ColorImage stereoImage = this.getStereoImage(this.balls[i], disparity);
                    this.scaleImageAtCenter(this.balls[i], 1.0D);
                    this.canvas.add(stereoImage);
                    this.canvas.remove(previousStereoImages[i]);
                    previousStereoImages[i] = stereoImage;
                }

                if(disparity == 0 || disparity == this.maxDisparity) {
                    change *= -1;
                }

                disparity += change;
                this.pause(5);
            }
        }

    }

    private ColorImage createStereoImage(ColorImage originalImage, int disparity) {
        originalImage.setX(originalImage.getX() - disparity / 2);
        int width = originalImage.getWidth() + disparity;
        int height = originalImage.getHeight();
        ColorImage stereoImage = new ColorImage(width, height);
        stereoImage.setX(originalImage.getX());
        stereoImage.setY(originalImage.getY());

        for(int y = 0; y < height; ++y) {
            int x;
            int foregroundLeftX;
            int backgroundY;
            int backgroundX;
            for(x = 0; x < disparity; ++x) {
                foregroundLeftX = stereoImage.getX() + x;
                backgroundY = stereoImage.getY() + y;
                backgroundX = originalImage.getAlpha(x, y);
                if(backgroundX < 128) {
                    stereoImage.setAlpha(x, y, 0);
                } else {
                    stereoImage.setRGB(x, y, this.background.getRed(foregroundLeftX, backgroundY), originalImage.getGreen(x, y), originalImage.getBlue(x, y));
                }
            }

            for(x = disparity; x < width - disparity; ++x) {
                foregroundLeftX = x - disparity;
                backgroundX = stereoImage.getX() + x;
                backgroundY = stereoImage.getY() + y;
                int foregroundRightAlpha = originalImage.getAlpha(x, y);
                int foregroundLeftAlpha = originalImage.getAlpha(foregroundLeftX, y);
                if(foregroundRightAlpha < 128 && foregroundLeftAlpha < 128) {
                    stereoImage.setAlpha(x, y, 0);
                } else if(foregroundRightAlpha < 128 && foregroundLeftAlpha >= 128) {
                    stereoImage.setRGB(x, y, originalImage.getRed(foregroundLeftX, y), this.background.getGreen(backgroundX, backgroundY), this.background.getBlue(backgroundX, backgroundY));
                } else if(foregroundRightAlpha >= 128 && foregroundLeftAlpha < 128) {
                    stereoImage.setRGB(x, y, this.background.getRed(backgroundX, backgroundY), originalImage.getGreen(x, y), originalImage.getBlue(x, y));
                } else {
                    stereoImage.setRGB(x, y, originalImage.getRed(foregroundLeftX, y), originalImage.getGreen(x, y), originalImage.getBlue(x, y));
                }
            }

            for(x = width - disparity; x < width; ++x) {
                int foregroundX = x - disparity;
                foregroundLeftX = stereoImage.getX() + x;
                backgroundY = stereoImage.getY() + y;
                backgroundX = originalImage.getAlpha(foregroundX, y);
                if(backgroundX < 128) {
                    stereoImage.setAlpha(x, y, 0);
                } else {
                    stereoImage.setRGB(x, y, originalImage.getRed(foregroundX, y), this.background.getGreen(foregroundLeftX, backgroundY), this.background.getBlue(foregroundLeftX, backgroundY));
                }
            }
        }

        originalImage.setX(originalImage.getX() + disparity / 2);
        return stereoImage;
    }

    private ColorImage getStereoImage(ColorImage originalImage, int disparity) {
        BufferedImage ori = this.createBufferedImage(originalImage);
        int w = (int)((double)ori.getWidth() * originalImage.getScale());
        int h = (int)((double)ori.getHeight() * originalImage.getScale());
        new BufferedImage(w, h, 2);
        AffineTransform tx = new AffineTransform();
        tx.scale(originalImage.getScale(), originalImage.getScale());
        AffineTransformOp op = new AffineTransformOp(tx, 2);
        BufferedImage scaled = op.filter(ori, (BufferedImage)null);
        ColorImage temp = new ColorImage(scaled.getWidth(), scaled.getHeight());

        for(int x = 0; x < scaled.getWidth(); ++x) {
            for(int y = 0; y < scaled.getHeight(); ++y) {
                int argb = scaled.getRGB(x, y);
                int a = argb >> 24 & 255;
                int r = argb >> 16 & 255;
                int g = argb >> 8 & 255;
                int b = argb >> 0 & 255;
                temp.setRGB(x, y, r, g, b);
                temp.setAlpha(x, y, a);
            }
        }

        temp.setX(originalImage.getX());
        temp.setY(originalImage.getY());
        ColorImage image = this.createStereoImage(temp, disparity);
        return image;
    }

    private BufferedImage createBufferedImage(ColorImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage ret = new BufferedImage(width, height, 2);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                ret.setRGB(x, y, this.toRGB(image.getAlpha(x, y), image.getRed(x, y), image.getGreen(x, y), image.getBlue(x, y)));
            }
        }

        return ret;
    }

    private int toRGB(int alpha, int red, int green, int blue) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    private void scaleImageAtCenter(ColorImage image, double scale) {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        int currentWidth = (int)((double)image.getWidth() * image.getScale());
        int currentHeight = (int)((double)image.getHeight() * image.getScale());
        int xAdjustment = (currentWidth - originalWidth) / 2;
        int yAdjustment = (currentHeight - originalHeight) / 2;
        image.setX(image.getX() + xAdjustment);
        image.setY(image.getY() + yAdjustment);
        int finalWidth = (int)((double)originalWidth * scale);
        int finalHeight = (int)((double)originalHeight * scale);
        xAdjustment = (originalWidth - finalWidth) / 2;
        yAdjustment = (originalHeight - finalHeight) / 2;
        image.setScale(scale);
        image.setX(image.getX() + xAdjustment);
        image.setY(image.getY() + yAdjustment);
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }
    public static void main(String[] args) {
        BouncingBall bb = new BouncingBall();
        bb.animate();
    }

}
