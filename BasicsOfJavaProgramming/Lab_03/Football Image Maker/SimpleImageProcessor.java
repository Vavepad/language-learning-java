import comp102x.Canvas;
import comp102x.ColorImage;

public class SimpleImageProcessor {

    public SimpleImageProcessor() {
    }

    public static ColorImage resizeAndSetAlpha(ColorImage image, int boundingWidth, int boundingHeight, int alpha) {

        ColorImage ret = rescale(image, boundingWidth, boundingHeight);
        setImageAlpha(ret, alpha);

        return ret;
    }

    public static void setImageAlpha(ColorImage image, int alpha) {
        int w = image.getWidth();
        int h = image.getHeight();

        for(int row = 0; row < h; ++row) {
            for(int col = 0; col < w; ++col) {
                image.setAlpha(col, row, alpha);
            }
        }

    }

    public static ColorImage rescale(ColorImage originalImage, int boundingWidth, int boundingHeight) {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int targetWidth;
        int targetHeight;
        double scale;
        if(originalHeight >= originalWidth) {
            scale = (double)boundingHeight / (double)originalHeight;
            targetWidth = (int)((double)originalWidth * scale);
            targetHeight = boundingHeight;
        } else {
            scale = (double)boundingWidth / (double)originalWidth;
            targetWidth = boundingWidth;
            targetHeight = (int)((double)originalHeight * scale);
        }

        ColorImage image = new ColorImage(targetWidth, targetHeight);

        for(int x = 0; x < targetWidth; ++x) {
            for(int y = 0; y < targetHeight; ++y) {
                int[] rgb = originalImage.getRGB((int)((double)x / scale), (int)((double)y / scale));
                int alpha = originalImage.getAlpha((int)((double)x / scale), (int)((double)y / scale));
                image.setRGB(x, y, rgb[0], rgb[1], rgb[2]);
                image.setAlpha(x, y, alpha);
            }
        }
        return image;
    }
}

