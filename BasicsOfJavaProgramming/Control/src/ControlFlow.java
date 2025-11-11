
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class ControlFlow extends Panel{

    public static final int CPLUSPLUS = 1;
    public static final int PASCAL = 2;
    public static final int ENGLISH = 1;
    public static final int CHINESETW = 2;
    private TextPanel inputTextPanel;
    private TextPanel displayTextPanel;
    private FlowChartCanvas flowCanvas;
    private ScrollPane flowPanel;
    private ControlAnimatePanel controlAnimatePanel;
    private ControlConvertPanel controlConvertPanel;
    private Label statusLabel;
    private int language;
    public int languageSystem;
    public Locale locale;
    public ResourceBundle resource;

    public static void main(String[] args) throws IOException {
        Frame f = new Frame();
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            };
        });

        ControlFlow main = new ControlFlow();
        main.setSize(800,600); // same size as defined in the HTML APPLET
        f.add(main);
        f.pack();
        main.init();
        f.setSize(800,600 + 20); // add 20, seems enough for the Frame title,
        f.show();
    }

    public void init() throws IOException {
        this.setBackground(Attribute.bgColor);
        this.language = 1;
        this.languageSystem = 1;
        this.locale = new Locale("en", "US");

        this.resource = ResourceBundle.getBundle("ProgramResource", this.locale);
        if (this.language == 1) {
            this.inputTextPanel = new TextPanel(this.resource.getString("inputCodeEditable"), "while (x > 0) {\n   x++;\n   printf(\"x : %d\\n\", x);\n   do x--; while (x > 0);\n   if (x > 0)\n      x = 1;\n   else\n      x = 0;\n}\n", 15, 15, true);
        } else {
            this.inputTextPanel = new TextPanel(this.resource.getString("inputCodeEditable"), "while x > 0 do begin\n   x := x + 1;\n   writeln('x : ', x);\n   repeat\n      x := x - 1;\n   until x = 0;\n   if x > 0 then\n      x := 1\n   else\n      x := 0\nend\n", 15, 15, true);
        }

        this.displayTextPanel = new TextPanel(this.resource.getString("simplifiedCodeNonEditable"), "", 15, 15, false);
        this.flowCanvas = new FlowChartCanvas();
        this.flowPanel = new ScrollPane();
        this.flowPanel.add(this.flowCanvas);
        this.flowPanel.getHAdjustable().setUnitIncrement(5);
        this.flowPanel.getVAdjustable().setUnitIncrement(5);
        DisplayPanel var2 = new DisplayPanel(this, this.flowPanel);
        this.statusLabel = new Label("");
        this.controlAnimatePanel = new ControlAnimatePanel(this,
                this.inputTextPanel,
                this.displayTextPanel,
                this.flowCanvas,
                this.flowPanel,
                var2,
                this.statusLabel);
        this.controlConvertPanel = new ControlConvertPanel(this, this.inputTextPanel, this.displayTextPanel, this.flowCanvas, this.flowPanel, this.statusLabel, this.controlAnimatePanel, this.language);
        this.controlAnimatePanel.setControlConvertPanel(this.controlConvertPanel);
        MainPanel var3 = new MainPanel(this.controlAnimatePanel, this.controlConvertPanel);
        Label var4 = new Label(this.resource.getString("copyright"));
        Panel var5 = new Panel();
        var5.setLayout(new BorderLayout());
        var5.add("Center", this.statusLabel);
        var5.add("East", var4);
        this.setLayout(new BorderLayout());
        this.add("Center", var3);
        this.add("South", var5);
        this.validate();
        this.flowCanvas.findOriginalSize();
    }

    public void paint(Graphics var1) {
        Dimension var2 = this.size();
        Color var3 = this.getBackground();
        var1.setColor(var3);
        var1.clearRect(0, 0, var2.width, var2.height);
    }

}
