import comp102x.Canvas;
import comp102x.IO;

public class Player {
    private Choice choice;

    public Player() {
    }

    public void makeChoice() {
        IO.outputln("Please input your choice (0=rock, 1=paper, 2=scissors)");
        int type = IO.inputInteger();
        this.choice = new Choice(type);
    }

    public void showChoice(Canvas canvas) {
        this.choice.draw(canvas, 0, 480, 0);
    }

    public Choice getChoice() {
        return this.choice;
    }

    public boolean playAgain() {
        char playAgainAnswer;
        do {
            IO.outputln("Want to play again? (y/n)");
            playAgainAnswer = IO.inputCharacter();
        } while(playAgainAnswer != 110 && playAgainAnswer != 121 && playAgainAnswer != 78 && playAgainAnswer != 89);

        return playAgainAnswer == 121 || playAgainAnswer == 89;
    }
}
