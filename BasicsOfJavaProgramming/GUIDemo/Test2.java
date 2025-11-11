import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Test2 implements MouseListener {
    Test2() {
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked in frame");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Entered frame");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Exited frame");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed in frame");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Released in frame");
    }
}
