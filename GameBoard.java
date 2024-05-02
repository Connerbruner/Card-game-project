import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    static final JLabel LABEL = new JLabel();
    static final JLabel TEXT1 = new JLabel();
    static final JLabel TEXT2 = new JLabel();
    static final JLabel[] INFO_PANNEL = {
            new JLabel("Paycheck:"),
            new JLabel("Floor:"),
            new JLabel("Next Rest Floor:"),
            new JLabel(""),
    };
    static final JLabel[] INDEX_LABELS = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };

    static final JLabel[] CARD_IMAGES = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };
    static final int[][] INDEX_POSITIONS = {
            {190,425},
            {555,425},
            {957,425},
            {1350,425},
            {1745,425},
            {555,925},
            {957,925},
            {1340,925},
    };
    static final int[][] CARD_POSITIONS = {
            {40,0},
            {410,0},
            {820,0},
            {1200,0},
            {1600,0},
            {410,500},
            {820,500},
            {1200,500},
    };
    static final JTextField INPUT = new JTextField(10);
    static final JFrame SYSTEM = new JFrame("");
    private final Character[] AVAILABLE_PARTY_MEMBERS = new Character[] {

    };
    private final int[] ESCAPE_FLOORS = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
    private int currentFloor = 0;

    public static Deck deck;
    private ArrayList<Character> team = new ArrayList<>();
    private int score = 0;

    public GameBoard(Deck cards) {
        deck = cards;
        //text 1 2 setup

        SYSTEM.add(TEXT1);
        TEXT1.setFont(new Font("Arial", Font.BOLD, 20));
        TEXT1.setHorizontalAlignment(0);
        TEXT1.setBounds(0,1000, 1920, 20);

        SYSTEM.add(TEXT2);
        TEXT2.setFont(new Font("Arial", Font.BOLD, 20));
        TEXT2.setHorizontalAlignment(0);
        TEXT2.setBounds(0,1020, 1920, 20);


        //card system setup
        for(int i=0; i<CARD_IMAGES.length; i++) {
            SYSTEM.add(CARD_IMAGES[i]);
            CARD_IMAGES[i].setIcon(new ImageIcon("Cards/mir.png"));
            Dimension size = CARD_IMAGES[i].getPreferredSize();
            CARD_IMAGES[i].setBounds(CARD_POSITIONS[i][0], CARD_POSITIONS[i][1], size.width, size.height);
        }
        for(int i=0; i<INDEX_LABELS.length; i++) {
            SYSTEM.add(INDEX_LABELS[i]);
            INDEX_LABELS[i].setText(i+1+"");
            Dimension size = INDEX_LABELS[i].getPreferredSize();
            INDEX_LABELS[i].setFont(new Font("Arial", Font.BOLD, 12));
            INDEX_LABELS[i].setBounds(INDEX_POSITIONS[i][0], INDEX_POSITIONS[i][1], size.width, size.height);
        }
        //INFO_PANNEL setup
        for(int i=0; i<INFO_PANNEL.length; i++) {
            SYSTEM.add(INFO_PANNEL[i]);
            INFO_PANNEL[i].setFont(new Font("Arial", Font.BOLD, 20));
            INFO_PANNEL[i].setBounds(1600, CARD_POSITIONS[i][1], 200,20);
        }
        //final setup
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
        TEXT1.setText(str);
        TEXT2.setText(">Press any key<");

        while (INPUT.getText().equals(" ")) ;
        INPUT.setEditable(false);
        SYSTEM.requestFocusInWindow();
    }

    public static int choice(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + " (Press number on your keyboard corresponding to the number you want 2 times");
        while (formatInput(INPUT.getText()) == 0) ;
        TEXT1.setText("");
        return formatInput(INPUT.getText());
    }

    public static int formatInput(String str) {
        char[] chars = str.toCharArray();
        if (str.length() > 2 && strIsInt(str) && chars[chars.length - 1] == chars[chars.length - 2]) {
            return chars[chars.length - 1];
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
        for (int i = 0; i < 3; i++) {
            int choice = choice("Pick a team member (0-9)");
            boolean inParty = false;
            for (int j = 0; j < team.size(); j++) {
                if (team.get(j).equals(AVAILABLE_PARTY_MEMBERS[choice])) {
                    inParty = true;
                }
            }
            if (inParty) {
                sPrintln("You already have this member in the party");
            } else {
                team.set(i, (AVAILABLE_PARTY_MEMBERS[choice]));
                sPrintln(team.get(i).getName() + " joined the party");
            }
        }
    }

    public void addRemainingPartyMembers() {
        for (int i = 0; i < AVAILABLE_PARTY_MEMBERS.length; i++) {
            boolean inTeam = false;
            for (int j = 0; i < team.size(); j++) {
                if (AVAILABLE_PARTY_MEMBERS[i].equals(team.get(j))) {
                    inTeam = true;
                }
            }
            if (!inTeam) {
                deck.add(AVAILABLE_PARTY_MEMBERS[i]);
            }
        }
    }

    public Deck getDeck() {
        return deck;
    }


    public void addScore(int add) {
        this.score += add;
        INFO_PANNEL[0].setText("Score: " + score);
    }

    public ArrayList<Card> resolveLootEffects(ArrayList<Card> loot) {
        boolean bossFight = false;
        ArrayList<Character> enemies  = new ArrayList<>();
        for (int i = 0; i < loot.size(); i++) {
            if (loot.get(i).getType() == 4) {
                bossFight = true;
                enemies.add((Character) loot.get(i));
            }
            if (loot.get(i).getType() == 3) {
                ((Item) (loot.get(i))).runEvent();
                deck.addToBottom(loot.get(i));
                deck.remove(i);
            }
        }
        for (int i = 0; i < loot.size(); i++) {
            if (loot.get(i).getType() == 1) {
                if (bossFight) {
                    deck.addToBottom(loot.get(i));
                } else {
                    enemies.add((Character) loot.get(i));
                }
            }
        }
        if (!enemies.isEmpty()) {
            int j=0;
            for(int i=0; i<loot.size(); i++) {
                if(enemies.size()<j) {
                    j=0;
                }
                enemies.get(j).addItem((Item) loot.get(i));
                j++;
            }
            new Battle(team,enemies);
        }
        int j=0;
        for(int i=0; i<loot.size(); i++) {
            if(team.size()<j) {
                j=0;
            }
            team.get(j).addItem((Item) loot.get(i));
            j++;
        }

        return loot;
    }


    public ArrayList<Card> getLoot() {
        ArrayList<Card> loot = deck.getRange(0, 5);
        deck.removeRange(0, 5);
        return loot;
    }
}
