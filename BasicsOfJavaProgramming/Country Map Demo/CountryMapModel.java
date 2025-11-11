import comp102x.Canvas;
import comp102x.ColorImage;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.event.SwingPropertyChangeSupport;

public class CountryMapModel {
    public static final int MODE_BROWSE = 0;
    public static final int MODE_PIN = 1;
    public static final int MODE_DELETE = 2;
    private int mode = 0;
    private Canvas canvas;
    private Collection<ColorImage> photos = new ArrayList();
    private final SwingPropertyChangeSupport pcs = new SwingPropertyChangeSupport(this, true);

    public CountryMapModel() {
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int m) {
        final int modeFirst = MODE_BROWSE;
        final int modeLast = MODE_DELETE;
        if ((m < modeFirst) || (m > modeLast)) {
            throw new IllegalArgumentException("Illegal mode value.");
        }
        final int oldMode=this.mode;
        this.mode=m;
        pcs.firePropertyChange("mode", oldMode, mode);
    }

    public void setCanvas(Canvas c) {
        this.canvas = c;
    }

    public ColorImage[] getPhotos() {
        return (ColorImage[])this.photos.toArray(new ColorImage[0]);
    }

    public void pinPhoto(ColorImage photo, int geoX, int geoY) {
        photo.setX(geoX);
        photo.setY(geoY);
        if(!this.photos.contains(photo)) {
            this.photos.add(photo);
            this.canvas.add(photo);
        }

    }

    public void removePhoto(ColorImage photo) {
        this.canvas.remove(photo);
        this.photos.remove(photo);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.pcs.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        this.pcs.removePropertyChangeListener(l);
    }
}
