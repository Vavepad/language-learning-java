import comp102x.Canvas;
import comp102x.ColorImage;

public class Choice {
    private int type;
    private ColorImage choiceImage;

    public Choice(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public int compareWith(Choice anotherChoice) {
        return this.type == anotherChoice.getType()?0:(this.type == 0 && anotherChoice.getType() == 1?-1:(this.type == 1 && anotherChoice.getType() == 2?-1:(this.type == 2 && anotherChoice.getType() == 0?-1:1)));
    }

    public void draw(Canvas canvas, int x, int y, int rotation) {
        switch(this.type) {
            case 0:
                this.choiceImage = new ColorImage("rock.png");
                break;
            case 1:
                this.choiceImage = new ColorImage("paper.png");
                break;
            case 2:
                this.choiceImage = new ColorImage("scissors.png");
        }

        this.choiceImage.setRotation(rotation);
        canvas.add(this.choiceImage, x, y);
    }
}
