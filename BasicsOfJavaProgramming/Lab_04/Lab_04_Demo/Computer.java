import comp102x.Canvas;
import java.util.Random;

public class Computer {
    private Choice choice;

    public Computer() {
    }

    public void makeChoice() {
        Random random = new Random();
        int type = random.nextInt(3);
        this.choice = new Choice(type);
    }

    public void showChoice(Canvas canvas) {
        this.choice.draw(canvas, 0, 0, 180);
    }

    public Choice getChoice() {
        return this.choice;
    }
}
