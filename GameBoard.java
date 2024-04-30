import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GameBoard {
    static final JLabel LABEL = new JLabel();
    static final JTextPane TEXT1 = new JTextPane();
    static final JTextPane SCORE = new JTextPane();
    static final JTextField INPUT = new JTextField(10);
    static final JFrame SYSTEM = new JFrame("");
    static final ImageIcon UI = new ImageIcon("ui_images/ui.png");
    private final Character[] AVIBLE_PARTY_MEMBERS = new Character[] {

    };
    private Deck deck;
    private Character[] team = new Character[3];
    private String partyName = "";
    private int score = 0;

    public GameBoard(Deck cards)  {
        deck = cards;
        TEXT1.setFont(new Font("Arial", Font.BOLD, 20));
        SCORE.setFont(new Font("Arial", Font.BOLD, 12));
        //Score Setup
        StyledDocument styleScore = SCORE.getStyledDocument();
        SimpleAttributeSet alignScore = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignScore, StyleConstants.ALIGN_RIGHT);
        styleScore.setParagraphAttributes(0, styleScore.getLength(), alignScore, false);
        SCORE.setEditable(true);
        SCORE.setBorder(null);
        SCORE.setOpaque(false);
        SCORE.setForeground(Color.BLACK);
        SCORE.setSize(UI.getIconWidth() , UI.getIconHeight());
        LABEL.add(SCORE);
        //Text 1 setup
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
        INPUT.setText(" ");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + "\n>Press any key<");

        while (INPUT.getText().equals(" ")) ;
        INPUT.setEditable(false);
        SYSTEM.requestFocusInWindow();
    }

    public int choice(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
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

    public void setTeam() {
        for(int i=0; i<team.length; i++) {
            int choice=choice("Pick a team member (0-9)");
            boolean inParty = false;
            for(int j=0; j<team.length; j++) {
                if(team[j].equals(AVIBLE_PARTY_MEMBERS[choice])) {
                    inParty=true;
                }
            }
            if(inParty) {
                sPrintln("You already have this member in the party");
            } else {
                team[i]=(AVIBLE_PARTY_MEMBERS[choice]);
                sPrintln(team[i].getName()+" joined the party");
            }
        }
    }
    public void addRemainingPartyMembers() {
        for (int i=0; i<AVIBLE_PARTY_MEMBERS.length; i++) {
            boolean inTeam=false;
            for (int j=0; i<team.length; j++) {
                if(AVIBLE_PARTY_MEMBERS[i].equals(team[j])) {
                    inTeam=true;
                }
            }
            if(!inTeam) {
                deck.add(AVIBLE_PARTY_MEMBERS[i]);
            }
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void setPartyName() {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        while(INPUT.getText().length()<4) {
            TEXT1.setText("Type out a 4 letter name for your team\n"+INPUT.getText());
        }
        TEXT1.setText("");
        this.partyName = INPUT.getText();
    }

    public void addScore(int add) {
        this.score +=add;
        SCORE.setText("Score: "+score);
    }
}
