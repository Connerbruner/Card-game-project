import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    public static final Card BLANK_CARD = new Card("Cards/cardBack.png");
    private static final JLabel LABEL = new JLabel();
    private static final JLabel TEXT1 = new JLabel();
    private static final JLabel TEXT2 = new JLabel();
    private static final JLabel[] STAT_DISPLAYS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] INFO_PANEL = {new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] INDEX_LABELS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] HP_DISPLAYS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] CARD_IMAGES = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final int[][] INDEX_POSITIONS = {{170, 90}, {555, 90}, {960, 90}, {1350, 90}, {1740, 90}, {555, 570}, {960, 570}, {1340, 570}, {1360, 570},};
    private static final int[][] CARD_POSITIONS = {{30, 120}, {410, 120}, {820, 120}, {1200, 120}, {1590, 120}, {410, 600}, {820, 600}, {1200, 600},};
    private static final int[][] CARD_HP = {{0, 80}, {410, 80}, {820, 80}, {1200, 80}, {1590, 80}, {410, 560}, {820, 560}, {1200, 560},};
    private static final int[][] CARD_STATS = {{40, 80}, {450, 80}, {860, 80}, {1240, 80}, {1650, 80}, {450, 560}, {850, 560}, {1240, 560},};
    private static final int[] REST_FLOORS = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
    private static final JTextField INPUT = new JTextField(10);
    private static final JFrame SYSTEM = new JFrame("");

    private static final ArrayList<Character> team = new ArrayList<>();
    private static int currentFloor = 0;
    private static int currentRestIndex = 0;
    private static int score = 0;
    private static ArrayList<Card> currentLoot = new ArrayList<>();
    private static final ArrayList<Character> currentEnemies = new ArrayList<>();
    private static Card[] cardsInDisplay = new Card[8];


    public GameBoard() {

        //party member shuffle
        for (int i = 0; i < Cards.AvailablePartyMembers.length; i++) {
            int randomIndexToSwap = Main.random(0, Cards.AvailablePartyMembers.length - 1);
            Character temp = Cards.AvailablePartyMembers[randomIndexToSwap];
            Cards.AvailablePartyMembers[randomIndexToSwap] = Cards.AvailablePartyMembers[i];
            Cards.AvailablePartyMembers[i] = temp;
        }
        //text 1 2 setup

        SYSTEM.add(TEXT1);
        TEXT1.setFont(new Font("Arial", Font.BOLD, 20));
        TEXT1.setHorizontalAlignment(0);
        TEXT1.setBounds(0, 30, 1920, 20);

        SYSTEM.add(TEXT2);
        TEXT2.setFont(new Font("Arial", Font.BOLD, 20));
        TEXT2.setHorizontalAlignment(0);
        TEXT2.setBounds(0, 50, 1920, 20);
        //hp display setup
        for (int i = 0; i < HP_DISPLAYS.length; i++) {
            SYSTEM.add(HP_DISPLAYS[i]);
            HP_DISPLAYS[i].setFont(new Font("Arial", Font.BOLD, 15));
            HP_DISPLAYS[i].setText("100");
            HP_DISPLAYS[i].setHorizontalTextPosition(JLabel.CENTER);
            HP_DISPLAYS[i].setIcon(new ImageIcon("ui_images/damage.png"));
            HP_DISPLAYS[i].setBounds(CARD_HP[i][0], CARD_HP[i][1], 128, 128);
            HP_DISPLAYS[i].setVisible(false);
        }
        for (int i = 0; i < STAT_DISPLAYS.length; i++) {
            SYSTEM.add(STAT_DISPLAYS[i]);
            STAT_DISPLAYS[i].setFont(new Font("Arial", Font.BOLD, 15));
            STAT_DISPLAYS[i].setText("100");
            STAT_DISPLAYS[i].setHorizontalTextPosition(JLabel.CENTER);
            STAT_DISPLAYS[i].setIcon(new ImageIcon("ui_images/stats.png"));
            STAT_DISPLAYS[i].setBounds(CARD_STATS[i][0], CARD_STATS[i][1], 128, 128);
            STAT_DISPLAYS[i].setVisible(false);
        }

        //card system setup
        for (int i = 0; i < CARD_IMAGES.length; i++) {
            SYSTEM.add(CARD_IMAGES[i]);
            CARD_IMAGES[i].setIcon(new ImageIcon(Cards.AvailablePartyMembers[i].getPath()));
            Dimension size = CARD_IMAGES[i].getPreferredSize();
            CARD_IMAGES[i].setBounds(CARD_POSITIONS[i][0], CARD_POSITIONS[i][1], size.width, size.height);
        }
        for (int i = 0; i < INDEX_LABELS.length; i++) {
            SYSTEM.add(INDEX_LABELS[i]);
            INDEX_LABELS[i].setFont(new Font("Arial", Font.BOLD, 20));
            INDEX_LABELS[i].setBounds(INDEX_POSITIONS[i][0], INDEX_POSITIONS[i][1], 200, 30);
        }

        //INFO_PANNEL setup
        for (int i = 0; i < INFO_PANEL.length; i++) {
            SYSTEM.add(INFO_PANEL[i]);
            INFO_PANEL[i].setFont(new Font("Arial", Font.BOLD, 20));
            INFO_PANEL[i].setBounds(1600, 600 + (i * 20), 200, 20);
        }
        //final setup
        INPUT.setEditable(false);
        SYSTEM.add(INPUT, BorderLayout.SOUTH);
        SYSTEM.add(LABEL);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);
        LABEL.setIcon(new ImageIcon("ui_images/ui.png"));
        SYSTEM.pack();


    }

    public static void sPrintln(String str) {
        INPUT.setText(" ");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str);
        TEXT2.setText(">Press any key<");

        while (INPUT.getText().equals(" ")) ;
        INPUT.setEditable(false);
        SYSTEM.requestFocusInWindow();
    }

    public static Object choice(String str, Object[] o) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + " (Press number on your keyboard corresponding to the number you want 2 times)");
        Object o1 = null;
        boolean noError = false;
        while (!noError || o1 == null) {
            try {
                o1 = o[formatInput(INPUT.getText()) - 1];
                noError = true;
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
        }
        TEXT1.setText("");
        return o1;
    }

    public static void setChoices(int[] choices) {
        for (JLabel indexLabel : INDEX_LABELS) {
            indexLabel.setText("");
        }
        for (int i = 0; i < choices.length; i++) {
            INDEX_LABELS[choices[i]].setText((i + 1) + "");
        }
    }

    public static void setChoices(ArrayList<Integer> choices) {
        for (JLabel indexLabel : INDEX_LABELS) {
            indexLabel.setText("");
        }
        for (int i = 0; i < choices.size(); i++) {
            INDEX_LABELS[choices.get(i)].setText((i + 1) + "");
        }
    }

    public static int formatInput(String str) {
        if (str.length() > 1) {
            String input = str.charAt(str.length() - 1) + "";
            String confirm = str.charAt(str.length() - 2) + "";
            if (strIsInt(confirm) && input.equals(confirm)) {
                return Integer.parseInt(confirm);
            }
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

    public static Deck getDeck() {
        return Cards.deck;
    }

    public static ArrayList<Character> getTeam() {
        return team;
    }


    public static Card[] getCardsInDisplay() {
        return cardsInDisplay;
    }

    public static void setCardsInDisplay(Card[] cardsInDisplay) {
        GameBoard.cardsInDisplay = cardsInDisplay;
        for (int i = 0; i < cardsInDisplay.length; i++) {
            if (cardsInDisplay[i] == null || cardsInDisplay[i].getType() == 0) {
                CARD_IMAGES[i].setIcon(new ImageIcon(BLANK_CARD.getPath()));
            } else {
                CARD_IMAGES[i].setIcon(new ImageIcon(cardsInDisplay[i].getPath()));
            }
        }
    }


    public static void setCardsInDisplay(int type) {
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == type) {
                cards.add(currentLoot.get(i));
            }
        }
        while (cards.size() < 5) {
            cards.add(BLANK_CARD);
        }
        for (int i = 0; i < team.size(); i++) {
            cards.add(team.get(i));
        }
        setCardsInDisplay(cards.toArray(new Card[cards.size()]));
    }

    public static void setChoicesToTeam() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < team.size(); i++) {
            indexes.add(i + 5);
        }
        setChoices(indexes);
    }

    public static void setChoicesToEnemies() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i) != null) {
                if (currentLoot.get(i).getType() == 1 || currentLoot.get(i).getType() == 2) {
                    indexes.add(i);
                }
            }

        }
        setChoices(indexes);
    }

    public static void setChoicesToItems() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 3) {
                indexes.add(i);
            }
        }
        setChoices(indexes);
    }

    public static void setTeam() {
        while (team.size() < 3) {
            setChoices(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
            Character character = (Character) choice("Pick a team member (1-8)", Cards.AvailablePartyMembers);
            boolean inParty = false;
            for (Character value : team) {
                if (value.equals(character)) {
                    inParty = true;
                    break;
                }
            }
            if (inParty) {
                sPrintln("You already have this member in the party");
            } else {
                team.add(character);
                character.setPlayerControlled(true);
                sPrintln(character.getName() + " joined the party");
            }
        }
    }

    public static void addScore(int add) {
        score += add;
        INFO_PANEL[0].setText("Score: " + score);
    }

    public static void setFloor(int floor) {
        currentFloor = floor;
        INFO_PANEL[1].setText("Current Floor: " + currentFloor);
    }

    public static void loot() {
        currentLoot = Cards.deck.getRange(0, 5);
        Cards.deck.removeRange(0, 5);
        for (int i = 0; i < currentLoot.size(); i++) {
            setCardsInDisplay(4);
            if (currentLoot.get(i).getType() == 1) {
                currentEnemies.add((Character) currentLoot.get(i));
                System.out.println("found");
            }
            if (currentLoot.get(i).getType() == 4) {
                (currentLoot.get(i)).trigger();
            }

        }
        System.out.println(currentEnemies.size());
        setCardsInDisplay(1);
        if (!currentEnemies.isEmpty()) {
            int j = 0;
            for (Card card : currentLoot) {
                if (card.getType() == 3) {
                    if (currentEnemies.size() <= j) {
                        j = 0;
                    }
                    currentEnemies.get(j).addItem((Item) card);
                    j++;
                }

            }
            new Battle(team, currentEnemies);
        }
        for (Card card : currentLoot) {
            setCardsInDisplay(3);
            setChoicesToTeam();
            if (card.getType() == 3) {
                Character target = (Character) choice("Which party member should get the " + card.getName(), team.toArray());
                target.addItem((Item) card);
                sPrintln(target.getName() + " got a " + card.getName());
            }
        }
    }


    public static void gameLoop() {
        for (currentFloor = 0; currentFloor < 100 && !team.isEmpty() && Cards.deck.size() > 5; currentFloor++) {
            setFloor(currentFloor);
            loot();
            if (currentFloor >= REST_FLOORS[currentRestIndex]) {
                rest();
            }
        }
    }

    public static void updateCharacterDisplays(Character character) {
        int index = 0;
        for (int i = 0; i < cardsInDisplay.length; i++) {
            if (cardsInDisplay[i] == character) {

                index = i;
            }
        }
        if (character.getDamage() == 0) {
            HP_DISPLAYS[index].setVisible(false);
        } else {
            HP_DISPLAYS[index].setVisible(true);
            HP_DISPLAYS[index].setText(character.getDamage() + "");
        }
        if (character.getStatChanges().isEmpty()) {
            STAT_DISPLAYS[index].setVisible(false);
        } else {
            STAT_DISPLAYS[index].setVisible(true);
            STAT_DISPLAYS[index].setText(character.statChangeDiff(0) + " , " + character.statChangeDiff(1) + " , " + character.statChangeDiff(2));
        }


    }

    public static void removeFromLoot(Card c) {
        currentLoot.remove(c);
    }

    public static void nextRestFloor() {
        currentRestIndex++;
        INFO_PANEL[2].setText("Next Rest Floor: " + REST_FLOORS[currentRestIndex]);
    }

    public static ArrayList<Card> getCurrentLoot() {
        return currentLoot;
    }

    public static void rest() {

        nextRestFloor();
    }

    public void updateCharacterDisplay(Character character) {

    }


}
