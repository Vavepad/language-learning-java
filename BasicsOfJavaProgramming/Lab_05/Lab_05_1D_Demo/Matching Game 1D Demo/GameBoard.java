import comp102x.Canvas;
import java.util.Random;

public class GameBoard {
    public static final int BOARD_MARGIN = 50;
    public static final int CARD_WIDTH = 150;
    public static final int CARD_HEIGHT = 200;
    public static final int NUMBER_OF_CARDS = 6;
    private Card[] cards = new Card[6];

    public GameBoard() {
        for(int i = 0; i < 6; ++i) {
            this.cards[i] = new Card(i % 3 + 1);
        }

        Random randomNumberGenerator = new Random();

        for(int i = 0; i < 100; ++i) {
            int randomNumberA = randomNumberGenerator.nextInt(6);
            int randomNumberB = randomNumberGenerator.nextInt(6);
            this.swapCards(randomNumberA, randomNumberB);
        }

    }

    private void swapCards(int cardAIndex, int cardBIndex) {
        Card originalCardA = this.cards[cardAIndex];
        this.cards[cardAIndex] = this.cards[cardBIndex];
        this.cards[cardBIndex] = originalCardA;
    }

    public void flipCardUp(int cardIndex) {
        this.cards[cardIndex].setFacingUp(true);
    }

    public void flipCardDown(int cardIndex) {
        this.cards[cardIndex].setFacingUp(false);
    }

    public boolean checkCardMatch(int firstCardIndex, int secondCardIndex) {
        return this.cards[firstCardIndex].getValue() == this.cards[secondCardIndex].getValue();
    }

    public boolean checkAllMatchesFound() {
        for(int i = 0; i < 6; ++i) {
            if(!this.cards[i].isFacingUp()) {
                return false;
            }
        }

        return true;
    }

    public void draw(Canvas canvas) {
        canvas.removeAll();

        for(int i = 0; i < 6; ++i) {
            this.cards[i].draw(canvas, 50 + i * 150, 50);
        }

    }
}
