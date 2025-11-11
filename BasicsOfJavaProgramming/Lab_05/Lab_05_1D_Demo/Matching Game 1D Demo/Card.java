import comp102x.Canvas;
import comp102x.ColorImage;

public class Card {
    private int value;
    private boolean facingUp;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFacingUp() {
        return this.facingUp;
    }

    public void setFacingUp(boolean facingUp) {
        this.facingUp = facingUp;
    }

    public void draw(Canvas canvas, int x, int y) {
        String imageFileName;
        if(!this.facingUp) {
            imageFileName = "images/cardback.png";
        } else {
            imageFileName = "images/card" + this.value + ".png";
        }

        ColorImage image = new ColorImage(imageFileName);
        canvas.add(image, x, y);
    }
}
