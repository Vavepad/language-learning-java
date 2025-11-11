import comp102x.Canvas;
import comp102x.ColorImage;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import comp102x.Canvas;
import comp102x.ColorImage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountryMapApp implements ActionListener, PropertyChangeListener {
    private static final double COUNTRYMAP_SCALE = 1.16D;
    private static final int COUNTRYMAP_WIDTH = 800;
    private static final int COUNTRYMAP_HEIGHT = 600;
    private static final int UI_FONT_SIZE = 20;
    private CountryMapModel model = new CountryMapModel();
    private Canvas canvas = this.createCanvas();
    private JLabel statusBar;
    private static final String CMD_PIN = "Pin";
    private static final String CMD_BROWSE = "Browse";
    private static final String CMD_DELETE = "Delete";

    public CountryMapApp() {
        this.model.setCanvas(this.canvas);
        this.model.addPropertyChangeListener(this);
        this.statusBar = new JLabel("Status: Now in browse mode.");
        this.statusBar.setFont(this.statusBar.getFont().deriveFont(20.0F));
        this.initComponents();
    }

    private void initComponents() {
        JComponent top = this.createTopComponent();
        this.canvas.setComponentAtTop(top);
        this.canvas.setComponentAtBottom(this.statusBar);
    }

    private Canvas createCanvas() {
        Canvas c = new Canvas(927, 696);
        c.setResizable(false);
        ColorImage countrymap = new ColorImage("images/map.jpg");
        countrymap.setMovable(false);
        countrymap.setScale(1.16D);
        c.add(countrymap);
        CountryMapMouseListener mlistener = new CountryMapMouseListener();
        mlistener.setModel(this.model);
        c.addMouseListener(mlistener);
        return c;
    }

    private JComponent createTopComponent() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Browse mode");
        button.setActionCommand("Browse");
        button.addActionListener(this);
        Font font = button.getFont().deriveFont(20.0F);
        button.setFont(font);
        panel.add(button);
        button = new JButton("Pin mode");
        button.setActionCommand("Pin");
        button.addActionListener(this);
        button.setFont(font);
        panel.add(button);
        button = new JButton("Delete mode");
        button.setActionCommand("Delete");
        button.addActionListener(this);
        button.setFont(font);
        panel.add(button);
        return panel;
    }

    public void actionPerformed(ActionEvent event) {
        String var2 = event.getActionCommand();
        byte var3 = -1;
        switch(var2.hashCode()) {
            case 80245:
                if(var2.equals("Pin")) {
                    var3 = 0;
                }
                break;
            case 1998230186:
                if(var2.equals("Browse")) {
                    var3 = 2;
                }
                break;
            case 2043376075:
                if(var2.equals("Delete")) {
                    var3 = 1;
                }
        }

        switch(var3) {
            case 0:
                this.model.setMode(1);
                break;
            case 1:
                this.model.setMode(2);
                break;
            case 2:
            default:
                this.model.setMode(0);
        }

    }

    public void propertyChange(PropertyChangeEvent event) {
        if("mode".equals(event.getPropertyName())) {
            int mode = ((Integer)event.getNewValue()).intValue();
            String s = "Status: ";
            s = s + (new String[]{"Now in browse mode.", "Now in pin mode.", "Now in delete mode."})[mode];
            this.statusBar.setText(s);
        }

    }

    public static void main(String[] args) {
        new CountryMapApp();
    }
}
