import comp102x.Canvas;
import java.util.Random;

public class GameBoard {
    public static final int BOARD_MARGIN = 50;
    public static final int CARD_WIDTH = 200;
    public static final int CARD_HEIGHT = 200;
    public static final int NUMBER_OF_CARDS_PER_ROW = 4;
    public static final int NUMBER_OF_ROWS = 2;
    private Card[][] cards = new Card[2][4];

    public GameBoard() {

        int i;
        for(i = 0; i < 2; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.cards[i][j] = new Card((i * 4 + i) % 4 + 1);
            }
        }

        Random randomNumberGenerator = new Random();

        for(i = 0; i < 100; ++i) {
            int randomRowA = randomNumberGenerator.nextInt(2);
            int randomColumnA = randomNumberGenerator.nextInt(4);
            int randomRowB = randomNumberGenerator.nextInt(2);
            int randomColumnB = randomNumberGenerator.nextInt(4);
            this.swapCards(randomRowA, randomColumnA, randomRowB, randomColumnB);
        }

    }

    private void swapCards(int cardARowIndex, int cardAColumnIndex, int cardBRowIndex, int cardBColumnIndex) {
        Card originalCardA = this.cards[cardARowIndex][cardAColumnIndex];
        this.cards[cardARowIndex][cardAColumnIndex] = this.cards[cardBRowIndex][cardBColumnIndex];
        this.cards[cardBRowIndex][cardBColumnIndex] = originalCardA;
    }

    public void flipCardUp(int cardRowIndex, int cardColumnIndex) {
        this.cards[cardRowIndex][cardColumnIndex].setFacingUp(true);
    }

    public void flipCardDown(int cardRowIndex, int cardColumnIndex) {
        this.cards[cardRowIndex][cardColumnIndex].setFacingUp(false);
    }

    public boolean checkCardMatch(int firstCardRowIndex, int firstCardColumnIndex, int secondCardRowIndex, int secondCardColumnIndex) {
        return this.cards[firstCardRowIndex][firstCardColumnIndex].getValue() == this.cards[secondCardRowIndex][secondCardColumnIndex].getValue();
    }

    public boolean checkAllMatchesFound() {
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 4; ++j) {
                if(!this.cards[i][j].isFacingUp()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void draw(Canvas canvas) {
        canvas.removeAll();

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.cards[i][j].draw(canvas, 50 + j * 200, 50 + i * 200);
            }
        }

    }
}
