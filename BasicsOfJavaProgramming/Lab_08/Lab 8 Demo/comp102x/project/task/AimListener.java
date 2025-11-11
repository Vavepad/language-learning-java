package comp102x.project.task;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class AimListener implements MouseMotionListener {
    private double tilt;
    private double pan;

    public AimListener() {
    }

    public void mouseDragged(MouseEvent e) {
        e.getWhen();
    }

    public void mouseMoved(MouseEvent e) {
        e.getWhen();
        this.pan = (double)e.getX() / 800.0D * 180.0D - 90.0D;
        this.tilt = (double)e.getY() / 600.0D * 90.0D;
    }

    public double getTilt() {
        return this.tilt;
    }

    public double getPan() {
        return this.pan;
    }
}
