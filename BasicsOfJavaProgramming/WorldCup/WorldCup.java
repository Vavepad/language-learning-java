import comp102x.Canvas;
import comp102x.ColorImage;
import comp102x.Text;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WorldCup implements MouseListener {
    private final int nBall = 10;
    private int tries = 10;
    private int scores = 0;
    private Canvas canvas = new Canvas(1200, 700);
    private ColorImage[] images;
    private ColorImage goal;
    private ColorImage blast;
    private ColorImage pitch;
    private Text text;
    private Random random = new Random();
    private ArrayDeque<ColorImage> iQueue = new ArrayDeque();
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    private final int KICK_SOUND = 0;
    private final int GOAL_SOUND = 1;
    private Boolean isPlaying = Boolean.valueOf(false);
    private Object shooter;

    private WorldCup() {
        BufferedImage temp;
        try {
            temp = ImageIO.read(WorldCup.class.getResource("/images/goal.jpg"));
            this.goal = this.createColorImage(temp);
            this.goal.setMovable(false);
            temp = ImageIO.read(WorldCup.class.getResource("/images/blast2.png"));
            this.blast = this.createColorImage(temp);
            this.blast.setMovable(false);
            temp = ImageIO.read(WorldCup.class.getResource("/images/pitch2.png"));
            this.pitch = this.createColorImage(temp);
            this.pitch.setMovable(false);
        } catch (IOException var9) {
            System.err.println("Error in loading image.");
        }

        this.images = new ColorImage[10];

        for(int i = 0; i < 10; ++i) {
            try {
                int j = i + 1;
                temp = ImageIO.read(WorldCup.class.getResource("/images/b" + j + ".png"));
                this.images[i] = this.createColorImage(temp);
            } catch (IOException var8) {
                System.err.println("Error in loading image.");
            }
        }

        this.text = new Text("");
        this.text.setMovable(false);
        this.canvas.add(this.pitch);
        this.canvas.add(this.goal);
        this.canvas.add(this.blast);
        this.canvas.add(this.text);
        JPanel controlPanel = new JPanel();
        JButton button1 = new JButton("Shoot");
        button1.setActionCommand("shoot");
        button1.addMouseListener(this);
        JButton button2 = new JButton("Spinning Shoot");
        button2.setActionCommand("spin shoot");
        button2.addMouseListener(this);
        JButton button3 = new JButton("Reset");
        button3.addMouseListener(new WorldCup.ImageListener2());
        controlPanel.add(button1);
        controlPanel.add(button2);
        controlPanel.add(button3);
        this.canvas.setComponentAtTop(controlPanel);

        try {
            Class shooterClass = Class.forName("Shooter");
            this.shooter = shooterClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var7) {
            var7.printStackTrace();
            System.exit(-1);
        }

        this.init();
    }

    private void init() {
        this.text.setText("");
        Iterator var1 = this.iQueue.iterator();

        while(!this.iQueue.isEmpty()) {
            ColorImage image = (ColorImage)this.iQueue.removeFirst();
            this.canvas.remove(image);
        }

        this.tries = 10;
        this.scores = 0;

        for(int i = 0; i < this.tries; ++i) {
            ColorImage image = new ColorImage(this.images[this.random.nextInt(10)]);
            this.iQueue.addLast(image);
            this.canvas.add(image);
            image.setMovable(false);
        }

        this.blast.setScale(0.0D);
        this.updateQueueDisplay();
        this.startGame();
    }

    private void moveStraight(final boolean isSpinning) {
        Runnable r = new Runnable() {
            public void run() {
                if(!WorldCup.this.iQueue.isEmpty()) {
                    ColorImage image = (ColorImage)WorldCup.this.iQueue.removeFirst();
                    WorldCup.this.updateQueueDisplay();
                    WorldCup.this.playSound(0);
                    WorldCup.this.moveBall(image, 0, isSpinning);
                    WorldCup.this.tries--;
                }

            }
        };
        this.threadPool.execute(r);
    }

    private void updateQueueDisplay() {
        Runnable r = new Runnable() {
            public void run() {
                Iterator<ColorImage> itr = WorldCup.this.iQueue.iterator();

                for(int i = 0; itr.hasNext(); ++i) {
                    ColorImage image = (ColorImage)itr.next();
                    image.setX(200 - i * 120);
                    image.setY(350 - image.getHeight() / 2);
                }

            }
        };
        this.threadPool.execute(r);
    }

    private void moveBall(ColorImage ballImage, int dist, boolean isSpinning) {
        int times = 360;
        Class[] paramaterClasses;
        Method spinningShootMethod;
        if(isSpinning) {
            paramaterClasses = new Class[]{ColorImage.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};

            try {
                spinningShootMethod = this.shooter.getClass().getMethod("spinningShoot", paramaterClasses);
                spinningShootMethod.invoke(this.shooter, new Object[]{ballImage, Integer.valueOf(times), Integer.valueOf(3), Integer.valueOf(1)});
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var8) {
                var8.printStackTrace();
                System.exit(-1);
            }
        } else {
            paramaterClasses = new Class[]{ColorImage.class, Integer.TYPE, Integer.TYPE};

            try {
                spinningShootMethod = this.shooter.getClass().getMethod("shoot", paramaterClasses);
                spinningShootMethod.invoke(this.shooter, new Object[]{ballImage, Integer.valueOf(times), Integer.valueOf(3)});
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var7) {
                var7.printStackTrace();
                System.exit(-1);
            }
        }

        int pos = this.goal.getY();
        this.canvas.remove(ballImage);
        if(pos == 240 && dist == 0 && this.goal.getScale() > 0.1D) {
            this.blast.setScale(1.5D);
            this.blast.setX(1050);
            this.blast.setY(240);
            this.playSound(1);
            ++this.scores;
            this.pause(200);
            this.blast.setScale(0.0D);
        }

        if(pos == 80 && dist == -1 && this.goal.getScale() > 0.1D) {
            this.blast.setScale(1.5D);
            this.blast.setX(1050);
            this.blast.setY(80);
            this.playSound(1);
            ++this.scores;
            this.pause(200);
            this.blast.setScale(0.0D);
        }

        if(pos == 400 && dist == 1 && this.goal.getScale() > 0.1D) {
            this.blast.setScale(1.5D);
            this.blast.setX(1050);
            this.blast.setY(400);
            this.playSound(1);
            ++this.scores;
            this.pause(200);
            this.blast.setScale(0.0D);
        }

    }

    private void pause(int sleepTime) {
        try {
            Thread.sleep((long)sleepTime);
        } catch (InterruptedException var3) {
            System.exit(-1);
        }

    }

    public void mouseClicked(MouseEvent e) {
        JButton component = (JButton)e.getSource();
        boolean isSpinning = !component.getActionCommand().equals("shoot");
        this.moveStraight(isSpinning);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void startGame() {
        if(!this.isPlaying.booleanValue()) {
            this.isPlaying = Boolean.valueOf(true);
            Runnable r = new Runnable() {
                public void run() {
                    Random random = new Random();

                    while(WorldCup.this.tries > 0) {
                        int n = random.nextInt(3);
                        WorldCup.this.goal.setX(1063);
                        WorldCup.this.goal.setY(240);
                        double scale = random.nextBoolean()?1.0D:0.0D;
                        WorldCup.this.goal.setScale(scale);
                        WorldCup.this.pause(1000);
                    }

                    WorldCup.this.endGame();
                }
            };
            this.threadPool.execute(r);
        }
    }

    private void endGame() {
        this.isPlaying = Boolean.valueOf(false);
        this.showTextOnScreen("You scored " + this.scores + " goal(s).", 120, 350);
    }

    private void showTextOnScreen(String message, int x, int y) {
        this.text.setText(message);
        this.text.setSize(100);
        this.text.setX(x);
        this.text.setY(y);
        this.canvas.add(this.text);
    }

    private ColorImage createColorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        ColorImage ret = new ColorImage(width, height);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                int argb = image.getRGB(x, y);
                int a = argb >> 24 & 255;
                int r = argb >> 16 & 255;
                int g = argb >> 8 & 255;
                int b = argb >> 0 & 255;
                ret.setRGB(x, y, r, g, b);
                ret.setAlpha(x, y, a);
            }
        }

        return ret;
    }

    private void playSound(int soundType) {
        try {
            AudioInputStream audioIn;
            switch(soundType) {
                case 0:
                    audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/sounds/kick.wav")));
                    break;
                case 1:
                    audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream("/sounds/goal.wav")));
                    break;
                default:
                    return;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener(new WorldCup.SoundHandler(clip));
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var4) {
            var4.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new WorldCup();
    }

    class SoundHandler implements LineListener {
        Clip clip;

        public SoundHandler(Clip clip) {
            this.clip = clip;
        }

        public void update(LineEvent event) {
            if(event.getType() == Type.STOP) {
                this.clip.close();
            }

        }
    }

    class ImageListener2 implements MouseListener {
        ImageListener2() {
        }

        public void mouseClicked(MouseEvent e) {
            WorldCup.this.init();
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
