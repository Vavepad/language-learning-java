
import comp102x.Canvas;
import comp102x.ColorImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Hangman implements KeyListener {
    private final char UNKNOWN_CHARACTER = 63;
    private final int MAX_WORD_LIST_SIZE = 128;
    private final int STARTING_LIFE = 6;
    private Canvas canvas = new Canvas(400, 400);
    private JTextArea inputArea = new JTextArea();
    private int life;
    private String secretWord;
    private String displayWord;
    private boolean gameOver;

    public Hangman() throws IOException {
        this.inputArea.addKeyListener(this);
        this.inputArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret)this.inputArea.getCaret();
        caret.setUpdatePolicy(2);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(this.inputArea));
        panel.setBorder(BorderFactory.createEtchedBorder());
        this.canvas.setComponentAtBottom(panel);
        this.life = 6;
        String[] wordList = new String[128];
        int wordListSize = this.readWordList("wordList.txt", wordList);
        this.secretWord = this.pickSecretWord(wordList, wordListSize);
        this.gameOver = false;
    }

    public static void main(String[] args) throws IOException {
        Hangman game = new Hangman();
        game.runGameLoop();
    }

    public void runGameLoop() {
        this.displayWord = this.constructInitialDisplayWord(this.secretWord);
        this.showProgress(this.displayWord, this.life, this.canvas);
        this.inputArea.append("Guess a letter: ");

        while(!this.isGameOver()) {
            ;
        }

        this.inputArea.append("\n");
        if(this.life == 0) {
            this.inputArea.append("You failed... :(\n");
        } else {
            this.inputArea.append("You guessed the word correctly! Well done! :)\n");
        }

    }

    public synchronized boolean isGameOver() {
        return this.gameOver;
    }

    public synchronized void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private int readWordList(String fileName, String[] wordList) throws IOException {
        int numberOfWordsLoaded = 0;
        File file = new File(fileName);

        Scanner scanner;
        String line;
        for(scanner = new Scanner(file); scanner.hasNextLine(); wordList[numberOfWordsLoaded++] = line) {
            line = scanner.nextLine();
        }

        scanner.close();
        return numberOfWordsLoaded;
    }

    private String pickSecretWord(String[] wordList, int wordListSize) {
        Random randomNumberGenerator = new Random();
        int randomIndex = randomNumberGenerator.nextInt(wordListSize);
        return wordList[randomIndex];
    }

    private String constructInitialDisplayWord(String secretWord) {
        String displayWord = "";

        for(int i = 0; i < secretWord.length(); ++i) {
            displayWord = displayWord + '?';
        }

        return displayWord;
    }

    private String updateDisplayWord(String displayWord, String secretWord, char guess) {
        String newDisplayWord = "";

        for(int i = 0; i < displayWord.length(); ++i) {
            if(secretWord.charAt(i) == guess) {
                newDisplayWord = newDisplayWord + guess;
            } else {
                newDisplayWord = newDisplayWord + displayWord.charAt(i);
            }
        }

        return newDisplayWord;
    }

    private boolean checkGameOver(String displayWord, String secretWord, int life) {
        return secretWord.equals(displayWord) || life == 0;
    }

    private boolean checkIfDisplayWordChanged(String originalDisplayWord, String displayWord) {
        return !originalDisplayWord.equals(displayWord);
    }

    private void showProgress(String displayWord, int life, Canvas canvas) {
        this.inputArea.append("\n");
        this.inputArea.append("The word: " + displayWord + "\n");
        canvas.removeAll();
        ColorImage image = new ColorImage("images/life" + life + ".png");
        image.setMovable(false);
        canvas.add(image);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        if(!this.isGameOver()) {
            char guess = e.getKeyChar();
            if(e.getKeyChar() >= 65 && e.getKeyChar() <= 90 || e.getKeyChar() >= 97 && e.getKeyChar() <= 122) {
                this.inputArea.append(("" + guess).toLowerCase() + "\n");
                String originalDisplayWord = this.displayWord;
                this.displayWord = this.updateDisplayWord(this.displayWord, this.secretWord, guess);
                if(this.checkIfDisplayWordChanged(originalDisplayWord, this.displayWord)) {
                    this.inputArea.append("Good job.\n");
                } else {
                    this.inputArea.append("Try another.\n");
                    --this.life;
                }

                this.showProgress(this.displayWord, this.life, this.canvas);
                this.setGameOver(this.checkGameOver(this.displayWord, this.secretWord, this.life));
                if(!this.isGameOver()) {
                    this.inputArea.append("Guess a letter: ");
                }

            }
        }
    }
}
