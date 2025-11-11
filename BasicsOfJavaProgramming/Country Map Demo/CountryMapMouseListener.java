import comp102x.ColorImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CountryMapMouseListener implements MouseListener {
    private static final int THUMBNAIL_WIDTH_MAX = 100;
    private static final int THUMBNAIL_HEIGHT_MAX = 75;
    private static final int REGULAR_WIDTH_MAX = 400;
    private static final int REGULAR_HEIGHT_MAX = 300;
    private CountryMapModel model;

    public CountryMapMouseListener() {
    }

    public void setModel(CountryMapModel model) {
        this.model = model;
    }

    public void mouseClicked(MouseEvent e) {
        switch(this.model.getMode()) {
            case 0:
                this.doBrowsePhoto(e);
                break;
            case 1:
                this.doPinNewPhoto(e);
                break;
            case 2:
                this.doRemovePhoto(e);
        }

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    private void doBrowsePhoto(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        boolean leftClicked = e.getButton() == 1;
        boolean rightClicked = e.getButton() == 3;
        ColorImage[] photos = this.model.getPhotos();

        for(int i = 0; i < photos.length; ++i) {
            ColorImage photo = photos[i];
            if(this.isInsideImage(x, y, photo)) {
                double scale1 = this.findThumbnailScale(photo);
                double scale2 = this.findRegularScale(photo);
                if(rightClicked) {
                    photo.setScale(scale1);
                } else if(leftClicked) {
                    photo.setScale(scale2);
                }
                break;
            }
        }

    }

    private void doPinNewPhoto(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ColorImage photo = new ColorImage();
        photo.setScale(this.findThumbnailScale(photo));
        this.model.pinPhoto(photo, x, y);
        this.model.setMode(0);
    }

    private void doRemovePhoto(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ColorImage[] arr$ = this.model.getPhotos();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ColorImage photo = arr$[i$];
            if(this.isInsideImage(x, y, photo)) {
                this.model.removePhoto(photo);
                break;
            }
        }

        this.model.setMode(0);
    }

    private double findThumbnailScale(ColorImage image) {
        return this.findTargetScale(image.getWidth(), image.getHeight(), 100, 75);
    }

    private double findRegularScale(ColorImage image) {
        return this.findTargetScale(image.getWidth(), image.getHeight(), 400, 300);
    }

    private double findTargetScale(int imgWidth, int imgHeight, int targetWidth, int targetHeight) {
        double scaleW = (double)targetWidth / (double)imgWidth;
        double scaleH = (double)targetHeight / (double)imgHeight;
        return Math.min(scaleW, scaleH);
    }

    private boolean isInsideImage(int x, int y, ColorImage image) {
        double scale = image.getScale();
        int imgX = image.getX();
        int imgY = image.getY();
        int imgW = (int)((double)image.getWidth() * scale);
        int imgH = (int)((double)image.getHeight() * scale);
        return imgX <= x && x <= imgX + imgW && imgY <= y && y <= imgY + imgH;
    }
}
