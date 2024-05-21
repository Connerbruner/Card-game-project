import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameBoard {
    public static final Card BLANK_CARD = new Card("Cards/cardBack.png");
    private static final JLabel LABEL = new JLabel();
    private static final JLabel TEXT1 = new JLabel();
    private static final JLabel TEXT2 = new JLabel();
    private static final JLabel[] STAT_DISPLAYS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] INFO_PANEL = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] INDEX_LABELS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] CARD_IMAGES = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] ACTIVE_DISPLAYS = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};
    private static final JLabel[] TARGET_DISPLAY = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),};

    private static final int[][] INDEX_POSITIONS = {{170, 90}, {555, 90}, {960, 90}, {1350, 90}, {1740, 90}, {555, 570}, {960, 570}, {1340, 570}, {1360, 570},};
    private static final int[][] CARD_POSITIONS = {{30, 120}, {410, 120}, {820, 120}, {1200, 120}, {1590, 120}, {410, 600}, {820, 600}, {1200, 600},};
    private static final int[][] CARD_STATS = {{50, 307}, {425, 307}, {835, 307}, {1215, 307}, {1605, 307}, {425, 777}, {835, 805}, {1215, 805},};
    private static final int[] REST_FLOORS = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
    private static final JTextField INPUT = new JTextField(10);
    private static final JFrame SYSTEM = new JFrame("");
    private static final ArrayList<Character> team = new ArrayList<>();
    private static final ArrayList<Character> currentEnemies = new ArrayList<>();
    private static final ArrayList<Integer> currentChoices = new ArrayList<>();
    private static int[] permanentStatChange = new int[]{0, 0, 0};
    private static int currentFloor = 0;
    private static int currentRestIndex = 0;
    private static int currentTargetIndex = 0;
    private static int score = 0;
    private static ArrayList<Card> currentLoot = new ArrayList<>();
    private static Card[] cardsInDisplay = new Card[8];
    private static boolean wantsToKeepGoing = true;


    public GameBoard() {
        wantsToKeepGoing = true;
        //party member shuffle

        //text 1 2 setup

        SYSTEM.add(TEXT1);
        TEXT1.setFont(new Font("Lato", Font.BOLD, 20));
        TEXT1.setHorizontalAlignment(0);
        TEXT1.setBounds(0, 30, 1920, 20);

        SYSTEM.add(TEXT2);
        TEXT2.setFont(new Font("Lato", Font.BOLD, 20));
        TEXT2.setHorizontalAlignment(0);
        TEXT2.setBounds(0, 50, 1920, 20);
//hp display setup
        for (int i = 0; i < INDEX_LABELS.length; i++) {
            SYSTEM.add(INDEX_LABELS[i]);
            INDEX_LABELS[i].setFont(new Font("Arial", Font.BOLD, 20));
            INDEX_LABELS[i].setBounds(INDEX_POSITIONS[i][0], INDEX_POSITIONS[i][1], 200, 30);
            INDEX_LABELS[i].setVisible(false);

        }

        for (int i = 0; i < STAT_DISPLAYS.length; i++) {
            SYSTEM.add(STAT_DISPLAYS[i]);
            STAT_DISPLAYS[i].setIcon(new ImageIcon("ui_images/stats.png"));
            STAT_DISPLAYS[i].setFont(new Font("Arial", Font.BOLD, 20));
            STAT_DISPLAYS[i].setText("100");
            STAT_DISPLAYS[i].setHorizontalTextPosition(JLabel.CENTER);
            STAT_DISPLAYS[i].setBounds(CARD_STATS[i][0], CARD_STATS[i][1], 200, 200);
            STAT_DISPLAYS[i].setForeground(Color.BLACK);
            STAT_DISPLAYS[i].setVisible(false);
        }


        //card system setup
        for (int i = 0; i < CARD_IMAGES.length; i++) {
            SYSTEM.add(CARD_IMAGES[i]);
            CARD_IMAGES[i].setIcon(new ImageIcon(Cards.AVAILABLE_PARTY_MEMBERS[i].getPath()));
            Dimension size = CARD_IMAGES[i].getPreferredSize();
            CARD_IMAGES[i].setBounds(CARD_POSITIONS[i][0], CARD_POSITIONS[i][1], size.width, size.height);
            CARD_IMAGES[i].setVisible(false);
        }
        for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
            SYSTEM.add(ACTIVE_DISPLAYS[i]);
            ACTIVE_DISPLAYS[i].setIcon(new ImageIcon("ui_images/active.png"));
            ACTIVE_DISPLAYS[i].setBounds(CARD_POSITIONS[i][0] - 20, CARD_POSITIONS[i][1] - 10, 350, 451);
            ACTIVE_DISPLAYS[i].setVisible(false);
        }
        for (int i = 0; i < TARGET_DISPLAY.length; i++) {
            SYSTEM.add(TARGET_DISPLAY[i]);
            TARGET_DISPLAY[i].setIcon(new ImageIcon("ui_images/target.png"));
            TARGET_DISPLAY[i].setBounds(CARD_POSITIONS[i][0] - 20, CARD_POSITIONS[i][1] - 10, 350, 451);
            TARGET_DISPLAY[i].setVisible(false);
        }


        //INFO_PANNEL setup
        for (int i = 0; i < INFO_PANEL.length; i++) {
            SYSTEM.add(INFO_PANEL[i]);
            INFO_PANEL[i].setFont(new Font("Arial", Font.BOLD, 20));
            INFO_PANEL[i].setBounds(1600, 600 + (i * 20), 200, 20);
            INFO_PANEL[i].setVisible(false);

        }
        //final setup
        INPUT.setEditable(false);
        SYSTEM.add(INPUT);
        SYSTEM.add(LABEL);
        LABEL.setIcon(new ImageIcon("ui_images/ui.png"));
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);

        SYSTEM.pack();
    }

    public static void setBackground(String path) {
        LABEL.setIcon(new ImageIcon(path));
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
        String s = "";
        for (int i = 0; i < o.length; i++) {
            s += (i + 1) + " == " + o[i].toString() + "   ";
        }
        TEXT2.setText(s);
        int currentPose = -1;
        Object o1 = null;
        boolean noError = false;
        while (formatInput(INPUT.getText()) == 0) {
            while ((!noError || o1 == null)) {
                try {
                    String input = INPUT.getText();
                    for (int i = 0; i < input.length(); i++) {
                        String place = input.charAt(i) + "";
                        if (strIsInt(place)) {
                            try {
                                currentPose = currentChoices.get(Integer.parseInt(place) - 1);
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
                        if (i != currentPose) {
                            ACTIVE_DISPLAYS[i].setVisible(false);
                        } else {
                            ACTIVE_DISPLAYS[currentPose].setVisible(true);
                        }

                    }
                    o1 = o[formatInput(INPUT.getText()) - 1];
                    noError = true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }

        }
        INPUT.setEditable(false);

        for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
            ACTIVE_DISPLAYS[i].setVisible(false);
        }
        INPUT.setEditable(false);
        TEXT1.setText("");

        return o1;
    }

    public static Object choice(String str, Object[] o, String s) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + " (Press number on your keyboard corresponding to the number you want 2 times)");
        TEXT2.setText(s);
        int currentPose = -1;
        Object o1 = null;
        boolean noError = false;
        while (formatInput(INPUT.getText()) == 0) {
            while ((!noError || o1 == null)) {
                try {
                    String input = INPUT.getText();
                    for (int i = 0; i < input.length(); i++) {
                        String place = input.charAt(i) + "";
                        if (strIsInt(place)) {
                            try {
                                currentPose = currentChoices.get(Integer.parseInt(place) - 1);
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
                        if (i != currentPose) {
                            ACTIVE_DISPLAYS[i].setVisible(false);
                        } else {
                            ACTIVE_DISPLAYS[currentPose].setVisible(true);
                        }

                    }
                    o1 = o[formatInput(INPUT.getText()) - 1];
                    noError = true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }

        }
        INPUT.setEditable(false);

        for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
            ACTIVE_DISPLAYS[i].setVisible(false);
        }
        INPUT.setEditable(false);
        TEXT1.setText("");

        return o1;
    }

    public static Object choice(String str, Object[] o, boolean displayTracking, String bottomReadOut) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + " (Press number on your keyboard corresponding to the number you want 2 times)");
        TEXT2.setText(bottomReadOut);
        int currentPose = -1;
        Object o1 = null;
        boolean noError = false;
        while (!noError || o1 == null) {
            try {
                String input = INPUT.getText();
                for (int i = 0; i < input.length(); i++) {
                    String place = input.charAt(i) + "";
                    if (strIsInt(place)) {
                        try {
                            currentPose = currentChoices.get(Integer.parseInt(place) - 1);
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }
                }
                if (displayTracking) {
                    for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
                        if (i != currentPose) {
                            ACTIVE_DISPLAYS[i].setVisible(false);
                        } else {
                            ACTIVE_DISPLAYS[currentPose].setVisible(true);
                        }

                    }
                }

                o1 = o[formatInput(INPUT.getText()) - 1];
                noError = true;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        for (int i = 0; i < ACTIVE_DISPLAYS.length; i++) {
            ACTIVE_DISPLAYS[i].setVisible(false);
        }
        TEXT1.setText("");
        return o1;
    }

    public static void setChoices(int[] choices) {
        currentChoices.clear();
        for (int choice : choices) {
            currentChoices.add(choice);
        }
        for (JLabel indexLabel : INDEX_LABELS) {
            indexLabel.setText("");
        }
        for (int i = 0; i < choices.length; i++) {
            INDEX_LABELS[choices[i]].setText((i + 1) + "");
            currentChoices.add(choices[i]);
        }

    }

    public static void setChoices(ArrayList<Integer> choices) {
        currentChoices.clear();
        for (int choice : choices) {
            currentChoices.add(choice);
        }
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
        return Deck.BASE_DECK;
    }

    public static ArrayList<Character> getTeam() {
        return team;
    }


    public static Card[] getCardsInDisplay() {
        return cardsInDisplay;
    }

    public static void setCardsInDisplay(Card[] c) {

        cardsInDisplay = c;

        for (int i = 0; i < cardsInDisplay.length && i < 8; i++) {
            STAT_DISPLAYS[i].setVisible(false);
            if (cardsInDisplay[i] == null || cardsInDisplay[i].getType() == 0) {
                CARD_IMAGES[i].setIcon(new ImageIcon(BLANK_CARD.getPath()));

            } else {
                CARD_IMAGES[i].setIcon(new ImageIcon(cardsInDisplay[i].getPath()));
                if (cardsInDisplay[i].getType() < 3) {
                    Character character = (Character) cardsInDisplay[i];
                        STAT_DISPLAYS[i].setVisible(true);
                        STAT_DISPLAYS[i].setText("<html> Max HP: " + character.getDefense() + "<br>HP: " + (character.getDefense() - character.getDamage())
                                 +"<br>Damage: " + character.getStrength() + "%<br>Agility: " + character.getAgility()+"</html>");
                }
            }
        }

    }

    public static void setCardsInDisplay(int type) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < currentLoot.size() && i < 5; i++) {
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
        while (cards.size() < 8) {
            cards.add(BLANK_CARD);
        }
        setCardsInDisplay(cards.toArray(new Card[cards.size()]));
    }

    public static void setCardInDisplay(Card c, int i) {
        Card[] cards = new Card[8];
        for (int j = 0; j < cards.length; j++) {
            cards[j] = cardsInDisplay[j];
            if (i == j) {
                cards[j] = c;
            }
        }
        setCardsInDisplay(cards);
    }

    public static void setChoicesToTeam() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < team.size(); i++) {
            indexes.add(i + 5);
        }
        setChoices(indexes);
    }

    public static void setChoicesToEnemies() {
        int[] indexes = new int[currentEnemies.size()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
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
        team.clear();
        setCardsInDisplay(Cards.AVAILABLE_PARTY_MEMBERS);
        while (team.size() < 3) {
            setChoices(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
            Character character = (Character) choice("Pick a team member (1-8)", Cards.AVAILABLE_PARTY_MEMBERS);

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
            int index = -1;
            for (int i = 0; i < Cards.AVAILABLE_PARTY_MEMBERS.length; i++) {
                if (Cards.AVAILABLE_PARTY_MEMBERS[i] == character) {
                    index = i;
                }
            }
            if (index != -1) {
                setCardInDisplay(BLANK_CARD, index);
            }

        }
    }

    public static void addScore(int add) {
        score += add;
        setInfoPanelText(0, "Score: " + score);
        if (score > getHighScore()) {
            setHighScore(score);
            showHighScore();
            sPrintln("NEW HIGHSCORE");
        }
    }


    public static void setFloor(int floor) {
        currentFloor = floor;
        setInfoPanelText(1, "Current Floor: " + currentFloor);
    }

    public static void showHighScore() {
        setInfoPanelText(4, "High Score: " + getHighScore());
    }

    public static void loot() {
        currentLoot.clear();
        currentEnemies.clear();
        Deck.BASE_DECK.shuffle();
        setInfoPanelText(2, "Next Rest Floor: " + REST_FLOORS[currentRestIndex]);
        setTargetDisplay(-1);
        setChoices(new int[0]);
        currentLoot = Deck.BASE_DECK.getRange(0, 5, true);
        System.out.println(currentLoot.size() + "");
        Card[] cards = new Card[]{
                BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD, BLANK_CARD
        };
        for (int i = 0; i < currentLoot.size(); i++) {
            cards[i] = currentLoot.get(i);
        }
        for (int i = 0; i < team.size(); i++) {
            cards[i + 5] = team.get(i);
        }
        setCardsInDisplay(cards);
        sPrintln("Here is what you find on floor: " + currentFloor);
        Deck.BASE_DECK.removeRange(0, 5);
        for (int i = 0; i < currentLoot.size(); i++) {
            System.out.println(currentLoot.get(i).getName());
            setChoices(new int[0]);
            if (currentLoot.get(i).getType() == 2 && currentFloor > 0) {
                currentEnemies.add((Character) currentLoot.get(i));
            } else if (currentFloor <= 0 && currentLoot.get(i).getType() == 2) {
                sPrintln(currentLoot.get(i).getName() + " runs away to a higher floor");
            }
        }
        setCardsInDisplay(4);
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 4) {
                setNextTargetDisplay();
                (currentLoot.get(i)).trigger();
            }
        }

        setCardsInDisplay(2);
        new Battle(team, currentEnemies);


        if (!getTeam().isEmpty()) {
            setTargetDisplay(-1);
            for (int i = 0; i < currentLoot.size(); i++) {

                setChoicesToTeam();
                if (currentLoot.get(i).getType() == 3) {
                    setNextTargetDisplay();
                    setCardsInDisplay(3);
                    Character target = (Character) choice("Which party member should get the " + currentLoot.get(i).getName(), team.toArray());
                    target.addItem((Item) currentLoot.get(i));
                    sPrintln(target.getName() + " got a " + currentLoot.get(i).getName());
                }
            }
        }

    }


    public static void gameLoop() {
        for (currentFloor = 0; currentFloor < 100 && !team.isEmpty() && Deck.BASE_DECK.size() > 5 && wantsToKeepGoing; currentFloor++) {
            setFloor(currentFloor);
            loot();
            if (currentFloor >= REST_FLOORS[currentRestIndex]) {
                rest();
            }
            if (score > getHighScore()) {
                setHighScore(score);
                sPrintln("NEW HIGHSCORE");
            }
        }

        if (team.isEmpty()) {
            score = 0;
        } else {
            sPrintln("Score: " + score);
            sPrintln("Floor bonus: " + currentFloor + " x 100 = " + currentFloor * 100);
            score += currentFloor * 100;
            sPrintln("Team bonus: " + team.size() + " X 1000 = " + team.size() * 1000);
            score += team.size() * 1000;
            sPrintln("Deck Penalty: " + getDeck().size());
            score -= getDeck().size();
            sPrintln("Total Score: " + score);

        }
        if (score > getHighScore()) {
            setHighScore(score);
            showHighScore();
            sPrintln("NEW HIGHSCORE");
        }

    }

    public static int getHighScore() {
        try {
            File txt = new File("HighScore.txt");
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);
            return Integer.parseInt(reader.readLine());


        } catch (IOException e) {
            e.printStackTrace();
            return Integer.MAX_VALUE;
        }
    }

    public static void setHighScore(int score) {
        File fileToBeModified = new File("HighScore.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileToBeModified);
            writer.write(score + "");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void removeFromLoot(Card c) {
        currentLoot.remove(c);
    }

    public static void nextRestFloor() {
        currentRestIndex++;
        setInfoPanelText(2, "Next Rest Floor: " + REST_FLOORS[currentRestIndex]);
    }

    public static void rest() {
        sPrintln("REST FLOOR");
        sPrintln("You see a fire escape");
        if ((int) (choice("Would you like to escape and leave the tower?", new Object[]{1, 2}, false, "1 == Yes  2 == No")) == 1) {
            wantsToKeepGoing = false;
        }
        nextRestFloor();
    }

    public static void setInfoPanelText(int i, String s) {
        INFO_PANEL[i].setText(s);
    }

    public static int getCurrentFloor() {
        return currentFloor;
    }

    public static void setTargetDisplay(int index) {
        currentTargetIndex = index;
        for (int i = 0; i < TARGET_DISPLAY.length; i++) {
            TARGET_DISPLAY[i].setVisible(i == currentTargetIndex);
        }
    }

    public static void setNextTargetDisplay() {
        currentTargetIndex++;
        for (int i = 0; i < TARGET_DISPLAY.length; i++) {
            TARGET_DISPLAY[i].setVisible(i == currentTargetIndex);
        }
    }


    public static void turnOnDisplay() {
        for (int i = 0; i < INFO_PANEL.length; i++) {
            INFO_PANEL[i].setVisible(true);
        }
        for (int i = 0; i < INDEX_LABELS.length; i++) {
            INDEX_LABELS[i].setVisible(true);
        }

        for (int i = 0; i < CARD_IMAGES.length; i++) {
            CARD_IMAGES[i].setVisible(true);
        }


        LABEL.setIcon(new ImageIcon("ui_images/ui.png"));

    }

    public static int[] getPermanentStatChange() {
        return permanentStatChange;
    }

    public static void addToPermanentStatChange(int[] arr) {
        permanentStatChange = new int[]{arr[0] + permanentStatChange[0], arr[1] + permanentStatChange[1], arr[1] + permanentStatChange[1]};
    }
}
