import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

class ControlAnimatePanel extends Panel implements ActionListener {
    private ControlFlow main;
    private TextPanel inputTextPanel;
    private TextPanel displayTextPanel;
    private FlowChartCanvas flowCanvas;
    private ScrollPane flowPanel;
    private ControlConvertPanel controlConvertPanel;
    private Label messageLabel;
    private Label statusLabel;
    public ImageButton backButton;
    public ImageButton nextButton;
    public ImageButton restartButton;
    public ImageButton trueButton;
    public ImageButton falseButton;
    private Panel labelPanel;
    private Panel truthPanel;
    private Panel labelTruthPanel;
    private Panel buttonPanel;
    private Panel controlPanel;
    private RunFlowChart Run;
    private ProgramObj programObj;

    public ControlAnimatePanel(ControlFlow var1, TextPanel var2, TextPanel var3, FlowChartCanvas var4, ScrollPane var5, DisplayPanel var6, Label var7) throws IOException {
        this.main = var1;
        this.inputTextPanel = var2;
        this.displayTextPanel = var3;
        this.flowCanvas = var4;
        this.flowPanel = var5;
        this.statusLabel = var7;
        this.setBackground(Attribute.bgColor);
        this.messageLabel = new Label("                                                            ");
        this.messageLabel.setForeground(Attribute.messageColor);
        this.backButton = this.createImageButton("images/back.gif", "images/back_disable.gif", "back");
        this.nextButton = this.createImageButton("images/next.gif", "images/next_disable.gif", "next");
        this.restartButton = this.createImageButton("images/restart.gif", "images/restart_disable.gif", "restart");
        this.trueButton = this.createImageButton("images/true.gif", "True");
        this.falseButton = this.createImageButton("images/false.gif", "False");
        this.labelPanel = new Panel();
        this.labelPanel.setLayout(new FlowLayout(0));
        this.labelPanel.add(this.messageLabel);
        this.labelTruthPanel = new Panel();
        this.labelTruthPanel.setLayout(new BorderLayout());
        this.labelTruthPanel.add("Center", this.labelPanel);
        this.buttonPanel = new Panel();
        this.buttonPanel.setLayout(new FlowLayout(2));
        this.buttonPanel.add(this.backButton);
        this.buttonPanel.add(this.nextButton);
        this.buttonPanel.add(this.restartButton);
        this.truthPanel = new Panel();
        this.truthPanel.setLayout(new FlowLayout(2));
        this.truthPanel.setBackground(Attribute.highlightColor);
        this.truthPanel.add(this.trueButton);
        this.truthPanel.add(this.falseButton);
        this.controlPanel = new Panel();
        this.controlPanel.setLayout(new BorderLayout());
        this.controlPanel.add("Center", this.labelTruthPanel);
        this.setLayout(new BorderLayout());
        this.add("Center", var6);
        this.add("South", this.controlPanel);
        this.validate();
    }

    private ImageButton createImageButton(String var1, String var2, String var3) throws IOException {
        URL url1 = ControlAnimatePanel.class.getResource(var1);
        Image var4 = ImageIO.read(url1);
        URL url2 = ControlAnimatePanel.class.getResource(var2);
        Image var5 = ImageIO.read(url2);
        ImageButton var6 = new ImageButton(var4, var5, this.statusLabel);
        var6.addActionListener(this);
        var6.setBubbleHelp(this.main.resource.getString(var3));
        return var6;
    }

    private ImageButton createImageButton(String var1, String var2) throws IOException {
        URL url1 = ControlAnimatePanel.class.getResource(var1);
        Image var3 = ImageIO.read(url1);
        ImageButton var4 = new ImageButton(var3, this.statusLabel);
        var4.addActionListener(this);
        var4.setBubbleHelp(var2);
        return var4;
    }

    public void setControlConvertPanel(ControlConvertPanel var1) {
        this.controlConvertPanel = var1;
        this.Run = new RunFlowChart(this.main, this.flowCanvas, this.flowPanel, this.displayTextPanel, this.messageLabel, var1, this);
    }

    private void init() {
        this.programObj.reset();
        this.messageLabel.setText("                                                            ");
        this.normalLabelTruthPanel();
        this.labelTruthPanel.remove(this.truthPanel);
        this.labelTruthPanel.validate();
        this.resetButton();
    }

    public void reset() {
        this.init();
        this.Run.reset();
    }

    public void reset(ProgramObj var1) {
        this.programObj = var1;
        this.init();
        this.Run.reset(var1);
    }

    public void actionPerformed(ActionEvent var1) {
        Object var2 = var1.getSource();
        if (var2 == this.backButton) {
            if (!this.Run.lock) {
                this.Run.back();
                return;
            }
        } else if (var2 == this.nextButton) {
            if (!this.Run.lock) {
                this.nextButton.setRaised();
                this.Run.next();
                if (this.backButton.isDisabled()) {
                    this.backButton.enable();
                }

                if (this.restartButton.isDisabled()) {
                    this.restartButton.enable();
                    return;
                }
            }
        } else if (var2 == this.restartButton) {
            if (!this.Run.lock) {
                this.reset();
                this.Run.redraw();
                return;
            }
        } else if (var2 == this.trueButton) {
            if (!this.Run.lock) {
                this.trueButton.setRaised();
                this.changeToAnimatePanel();
                this.Run.processTruth(true);
                return;
            }
        } else if (var2 == this.falseButton && !this.Run.lock) {
            this.falseButton.setRaised();
            this.changeToAnimatePanel();
            this.Run.processTruth(false);
        }

    }

    private void highlightLabelTruthPanel() {
        this.messageLabel.setBackground(Attribute.highlightColor);
        this.labelPanel.setBackground(Attribute.highlightColor);
    }

    private void normalLabelTruthPanel() {
        this.messageLabel.setBackground(Attribute.bgColor);
        this.labelPanel.setBackground(Attribute.bgColor);
    }

    public void addAnimatePanel() {
        this.controlPanel.add("East", this.buttonPanel);
        this.controlPanel.validate();
    }

    public void removeAnimatePanel() {
        this.controlPanel.remove(this.buttonPanel);
        this.controlPanel.validate();
    }

    public void changeToTruthPanel() {
        this.nextButton.disable();
        this.highlightLabelTruthPanel();
        this.labelTruthPanel.add("East", this.truthPanel);
        this.labelTruthPanel.validate();
    }

    public void changeToAnimatePanel() {
        this.nextButton.enable();
        this.labelTruthPanel.remove(this.truthPanel);
        this.normalLabelTruthPanel();
        this.labelTruthPanel.validate();
    }

    public void clearAll() {
        this.removeAnimatePanel();
        this.labelTruthPanel.remove(this.truthPanel);
        this.labelTruthPanel.validate();
        this.normalLabelTruthPanel();
        this.messageLabel.setText("                                                            ");
    }

    private void resetButton() {
        this.backButton.setEnabled(false);
        this.nextButton.setEnabled(true);
        this.restartButton.setEnabled(false);
    }

    private void enableButtonNextFast() {
        this.nextButton.setEnabled(true);
    }

    public void disableButtonNextFast() {
        this.nextButton.setEnabled(false);
    }

    private void enableButtonBackRestart() {
        this.backButton.setEnabled(true);
        this.restartButton.setEnabled(true);
    }

    public void disableButtonBackRestart() {
        this.backButton.setEnabled(false);
        this.restartButton.setEnabled(false);
    }

    private void enableAllExceptPauseButton() {
        this.backButton.setEnabled(true);
        this.nextButton.setEnabled(true);
        this.restartButton.setEnabled(true);
    }

    private void disableAllExceptPauseButton() {
        this.backButton.setEnabled(false);
        this.nextButton.setEnabled(false);
        this.restartButton.setEnabled(false);
    }

    private void setSTEPLASTButton() {
        this.disableButtonNextFast();
        this.enableButtonBackRestart();
    }

    public void paint(Graphics var1) {
        Dimension var2 = this.size();
        Color var3 = this.getBackground();
        var1.setColor(var3);
        var1.clearRect(0, 0, var2.width, var2.height);
    }
}

