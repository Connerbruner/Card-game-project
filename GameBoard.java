import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class GameBoard {
    Thread print;
    static int tSpeed;
    static final JLabel LABEL = new JLabel();
    static final JTextPane TEXT1 = new JTextPane();
    static final JTextField INPUT = new JTextField(10);
    static final JFrame SYSTEM = new JFrame("NBES (Non Binary Entertainment System)");
    static final ImageIcon UI = new ImageIcon("ui_images/ui.png");
    String lastsPrint = "";
    volatile boolean keyButton = false;

    private Deck deck;
    private Character[] team = new Character[3];

    public GameBoard(Deck cards) throws MalformedURLException {
        deck = cards;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Files/Retro Gaming.ttf")));
            TEXT1.setFont(new Font("Retro Gaming", Font.BOLD, 12));
        } catch (IOException | FontFormatException ignored) {
        }
        LABEL.setLayout(new FlowLayout(FlowLayout.CENTER));
        StyledDocument style = TEXT1.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
        style.setParagraphAttributes(0, style.getLength(), align, false);
        TEXT1.setEditable(false);
        TEXT1.setBorder(null);
        TEXT1.setOpaque(false);
        TEXT1.setForeground(Color.BLACK);
        TEXT1.setSize(UI.getIconWidth() , UI.getIconHeight());
        LABEL.add(TEXT1);
        INPUT.setEditable(false);
        SYSTEM.setResizable(false);
        SYSTEM.add(INPUT, BorderLayout.SOUTH);
        SYSTEM.add(LABEL);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);
        LABEL.setIcon(new ImageIcon("ui_images/ui.png"));
        SYSTEM.pack();
    }
    public void sPrintln(String str) {
        TEXT1.setText("");
        AtomicReference<String> text = new AtomicReference<>("");
        String finalStr = textFormat(str);;
        print = new Thread(() -> {
            for (int i = 0; i < finalStr.length(); i++) {
                int finalI = i;
                text.updateAndGet(v -> v + finalStr.charAt(finalI));
                TEXT1.setText(text.get());
            }
            TEXT1.setText(text + "\n>Click<");
        });

        SYSTEM.requestFocusInWindow();
        keyButton = false;
        print.start();
        while (!keyButton) ;
        print.stop();

        keyButton = false;
        TEXT1.setText("");
        lastsPrint = "";
    }
    public String textFormat(String str) {
        StringBuilder string = new StringBuilder();
        char[] charArr = str.toCharArray();
        int reset = 0;
        for (char c : charArr) {
            if ((c + "").equals("\n")) {
                reset = 0;
            } else if (reset == UI.getIconWidth()/10) {
                string.append("\n");
                reset = 0;
            }
            string.append(c);
            reset++;
        }
        return string.toString();
    }
    public int choice(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        keyButton = false;
        TEXT1.setText(str+" (Press number on your keyboard corresponding to the number you want 2 times");
        while(formatInput(INPUT.getText())==0);
        TEXT1.setText("");
        return formatInput(INPUT.getText());
    }
    public int formatInput(String str) {
        char[] chars = str.toCharArray();
        if(str.length()>2 && strIsInt(str) && chars[chars.length-1]==chars[chars.length-2] ) {
            return chars[chars.length-1];
        }
        return 0;
    }
    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
