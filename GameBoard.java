import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameBoard {
    private static final JLabel LABEL = new JLabel();
    private static final JLabel TEXT1 = new JLabel();
    private static final JLabel TEXT2 = new JLabel();
    private static final JLabel[] INFO_PANNEL = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };
    private static final JLabel[] INDEX_LABELS = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };
    private static final JLabel[] HP_DISPLAYS = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };


    private static final JLabel[] CARD_IMAGES = {
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel(),
    };
    private static final int[][] INDEX_POSITIONS = {
            {170, 90},
            {555, 90},
            {960, 90},
            {1350, 90},
            {1740, 90},
            {555, 570},
            {960, 570},
            {1340, 570},
            {1360, 570},
    };
    private static final int[][] CARD_POSITIONS = {
            {30, 120},
            {410, 120},
            {820, 120},
            {1200, 120},
            {1590, 120},
            {410, 600},
            {820, 600},
            {1200, 600},
    };
    private static final int[][] CARD_HP = {
            {0, 80},
            {410, 80},
            {820, 80},
            {1200, 80},
            {1590, 80},
            {410, 560},
            {820, 560},
            {1200, 560},
    };
    public static  final Card BLANK_CARD = new Card("Cards/cardBack.png", false);
    private static final int[] ESCAPE_FLOORS = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
    private static final JTextField INPUT = new JTextField(10);
    private static final JFrame SYSTEM = new JFrame("");
    private static final ArrayList<Character> team = new ArrayList<>();
    private static int currentFloor = 0;
    private static int score = 0;
    private static ArrayList<Card> currentLoot = new ArrayList<>();
    private static final Card[] DIFFERENT_CARDS = new Card[]{
            new Item("Laser Sword", "Cards/laserSword.png", false,
                    (user, team, enemies) -> {
                        int index = Main.random(0, enemies.size() - 1);
                        if (user.isPlayer()) {
                            setChoicesToEnemies();
                            index = choice("Who would you like to attack") - 1;
                        }
                        int d = Main.random(20, 25);
                        enemies.get(index).changeHp(d);
                        sPrintln(enemies.get(index).getName() + " took " + d + " damage");
                    }
                    , false),
            BLANK_CARD,
            new Character("Prototype","Cards/prototype.png",true,new CharacterVoid[]{
                    (user, team, enemies) -> {
                        user.addStatChange(new StatChange(new int[]{-10,0,5},3));
                        sPrintln("Prototype Gained 5 Agility but lost 10 Strength");
                    },
                    (user, team, enemies) -> {
                        int index = Main.random(0,enemies.size()-1);
                        if(team.get(index).evadeCheck()) {
                            sPrintln(team.get(index).getName()+" takes "+user.getAgility());
                            team.get(index).changeHp(user.getAgility());
                        }


                    },
            },60,30,60)

    };
    private static final Deck deck = new Deck(new Card[]{
            DIFFERENT_CARDS[0], DIFFERENT_CARDS[0], DIFFERENT_CARDS[0], DIFFERENT_CARDS[0], DIFFERENT_CARDS[0],
            DIFFERENT_CARDS[1], DIFFERENT_CARDS[1], DIFFERENT_CARDS[1],
            DIFFERENT_CARDS[2],DIFFERENT_CARDS[2], DIFFERENT_CARDS[2],DIFFERENT_CARDS[2],
    });
    private static Card[] cardsInDisplay = new Card[8];
    private static Character[] AvailablePartyMembers = new Character[]{
            new Character("Mir", "Cards/mir.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (int i = 0; i < team.size(); i++) {
                            team.get(i).changeHp(-30);
                            sPrintln(team.get(i).getName() + " healed 30 damage");
                        }
                    },
                    (user, team, enemies) -> {
                        setChoices(new int[]{5, 6, 7});
                        int index = choice("Which Teammate would you like to heal");
                        team.get(index).changeHp(-100);
                        sPrintln(team.get(index).getName() + " healed 100 Damage");

                    },
            }, 90, 125, 0),
            new Character("Leo", "Cards/Leo.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (int i = 0; i < enemies.size(); i++) {
                            enemies.get(i).addStatChange(new StatChange(new int[]{0, 0, -100}, 3));
                        }
                        sPrintln("Enemies no longer can evade");
                    },
                    (user, team, enemies) -> {
                        setChoices(new int[]{0, 1, 2, 3, 4});
                        int target = choice("Who would you like to attack? ") - 1;
                        if (enemies.get(target).getAgility() > 0) {
                            sPrintln("Missed");
                        } else {
                            enemies.get(target).changeHp(100);
                            sPrintln("dealt 100 damage");
                        }

                    },
            }, 100, 50, 35),
            new Character("Arrokoth", "Cards/Arrokoth.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        sPrintln("Stats changed to 50,80,45");
                        user.addStatChange(new StatChange(new int[]{-50, 20, 15}, 3));
                    },
                    (user, team, enemies) -> {
                        sPrintln("Stats changed to 95,120,0");
                        user.addStatChange(new StatChange(new int[]{-5, 60, -30}, 3));
                    },
            }, 100, 60, 30),
            new Character("Gliese", "Cards/Gliese.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        setChoices(new int[]{0, 1, 2, 3, 4});
                        int target = choice("Who would you like to attack? ") - 1;
                        enemies.get(target).changeHp(50);
                        sPrintln("damage dealt 50");

                    },
                    (user, team, enemies) -> {
                        for (int i = 0; i < enemies.size(); i++) {
                            enemies.get(i).addStatChange(new StatChange(new int[]{-10, -10, -10}, 3));
                        }
                        sPrintln("Enemies stats lowered 10");
                    },
            }, 105, 70, 20),
            new Character("Baidam", "Cards/baidam card.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        setChoices(new int[]{0, 1, 2, 3, 4});
                        int target = choice("Who would you like to attack? ") - 1;
                        int count = (choice("How Much damage would you like to take up to 9") * 12);
                        int damage = (int) (count * user.getMul());
                        if (enemies.get(target).evadeCheck()) {
                            enemies.get(target).changeHp(damage);
                            sPrintln(damage + " damage dealt");
                            user.changeHp(count);
                        } else {
                            sPrintln("attack missed");
                        }


                    },
                    (user, team, enemies) -> {
                        for (int i = 0; i < team.size(); i++) {
                            team.get(i).addStatChange(new StatChange(new int[]{user.getDefense() - user.getHp(), 0, 0}, 3));
                        }
                        sPrintln("Teams strength raised by " + (user.getDefense() - user.getHp()));
                    },
            }, 155, 60, 8),
            new Character("Orion", "Cards/Orion.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        setChoices(new int[]{5, 6, 7});
                        int index = choice("Who's ability would you like to use") - 1;
                        int index2 = choice("Which ability 1 or 2 would you like to use") - 1;
                        team.get(index).getAbilities()[index2].run(user, team, enemies);
                    },
                    (user, team, enemies) -> {
                        setChoices(new int[]{5, 6, 7});

                        int index = choice("Who's item would you like to move") - 1;
                        team.get(index).displayItems(team.get(index));
                        setChoices(new int[]{6, 7});
                        int index2 = choice("Which item?") - 1;
                        if (index2 < team.get(index).getItems().size()) {
                            user.addItem(team.get(index).getItems().get(index2));
                            team.get(index).removeItem(index2);
                        }
                        setDisplayToDefault();
                    },
            }, 125, 50, 15),
            new Character("Velorum", "Cards/velorum.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (int i = 0; i < enemies.size(); i++) {
                            int damage = (int) (40 * user.getMul());
                            if (enemies.get(i).evadeCheck()) {
                                sPrintln(enemies.get(i).getName() + " took " + damage + " damage");
                                enemies.get(i).changeHp(damage);
                            } else {
                                sPrintln("Missed");
                            }
                        }
                    },
                    (user, team, enemies) -> {
                        for (int i = 0; i < enemies.size(); i++) {
                            if (enemies.get(i).evadeCheck()) {
                                int damage = enemies.get(i).getStrength() - enemies.get(i).getHp();
                                sPrintln(enemies.get(i).getName() + " took " + damage + " damage");
                                enemies.get(i).changeHp(damage);
                            } else {
                                sPrintln("Missed");
                            }
                        }
                    },
            }, 60, 55, 55),
            new Character("Vela", "Cards/Vela.png", true, new CharacterVoid[]{
                    (user, team, enemies) -> {
                        ArrayList<Card> loot = deck.search(7, 3);
                        setChoicesToTeam();
                        for (int i = 0; i < loot.size(); i++) {
                            team.get(choice("Which party member should get the " + loot.get(i).getName())).addItem((Item) loot.get(i));
                        }
                    },
                    (user, team, enemies) -> {
                        setChoicesToItems();
                        Item item = (Item) currentLoot.get(choice("Which Item would you like to steal?"));
                        currentLoot.remove(item);
                        for (int i = 0; i < enemies.size(); i++) {
                            if (enemies.get(i).getItems().contains(item)) {
                                enemies.get(i).removeItem(item);
                            }
                        }
                    },
            }, 50, 100, 30),


    };


    public GameBoard() {

        //party member shuffle
        for (int i = 0; i < AvailablePartyMembers.length; i++) {
            int randomIndexToSwap = Main.random(0,AvailablePartyMembers.length-1);
            Character temp = AvailablePartyMembers[randomIndexToSwap];
            AvailablePartyMembers[randomIndexToSwap] = AvailablePartyMembers[i];
            AvailablePartyMembers[i] = temp;
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
        //card system setup
        for (int i = 0; i < CARD_IMAGES.length; i++) {
            SYSTEM.add(CARD_IMAGES[i]);
            CARD_IMAGES[i].setIcon(new ImageIcon(AvailablePartyMembers[i].getPath()));
            Dimension size = CARD_IMAGES[i].getPreferredSize();
            CARD_IMAGES[i].setBounds(CARD_POSITIONS[i][0], CARD_POSITIONS[i][1], size.width, size.height);
        }
        for (int i = 0; i < INDEX_LABELS.length; i++) {
            SYSTEM.add(INDEX_LABELS[i]);
            INDEX_LABELS[i].setFont(new Font("Arial", Font.BOLD, 20));
            INDEX_LABELS[i].setBounds(INDEX_POSITIONS[i][0], INDEX_POSITIONS[i][1], 200, 30);
        }

        //INFO_PANNEL setup
        for (int i = 0; i < INFO_PANNEL.length; i++) {
            SYSTEM.add(INFO_PANNEL[i]);
            INFO_PANNEL[i].setFont(new Font("Arial", Font.BOLD, 20));
            INFO_PANNEL[i].setBounds(1600, 600 + (i * 20), 200, 20);
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

    public static int choice(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str + " (Press number on your keyboard corresponding to the number you want 2 times_");
        while (formatInput(INPUT.getText()) == 0);
        TEXT1.setText("");
        return formatInput(INPUT.getText());
    }

    public static void setChoices(int[] choices) {
        for (int i = 0; i < INDEX_LABELS.length; i++) {
            INDEX_LABELS[i].setText("");
        }
        for (int i = 0; i < choices.length; i++) {
            INDEX_LABELS[choices[i]].setText((i + 1) + "");
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
        return deck;
    }

    public static ArrayList<Character> getTeam() {
        return team;
    }

    public static int getScore() {
        return score;
    }

    public static Card[] getCardsInDisplay() {
        return cardsInDisplay;
    }

    public static void setCardsInDisplay(Card[] cardsInDisplay) {
        GameBoard.cardsInDisplay = cardsInDisplay;
        for (int i = 0; i < cardsInDisplay.length; i++) {
            CARD_IMAGES[i].setIcon(new ImageIcon(cardsInDisplay[i].getPath()));
        }
    }

    public static void setCardsInDisplay(ArrayList<Card> cardsInDisplay) {
        GameBoard.cardsInDisplay = cardsInDisplay.toArray(Card[]::new);
        for (int i = 0; i < cardsInDisplay.size(); i++) {
            CARD_IMAGES[i].setIcon(new ImageIcon(cardsInDisplay.get(i).getPath()));
        }
    }

    public static void setDisplayToDefault() {
        Card[] cards = new Card[8];
        for (int i = 0; i < currentLoot.size(); i++) {
            cards[i] = currentLoot.get(i);
        }
        for (int i = 0; i < team.size(); i++) {
            cards[i + 5] = team.get(i);
        }
        setCardsInDisplay(currentLoot);
    }

    public static void setChoicesToTeam() {
        int[] indexes = new int[3];
        for (int i = 0; i < team.size(); i++) {
            indexes[i] = i + 5;
        }
        setChoices(indexes);
    }

    public static void setChoicesToEnemies() {
        int[] indexes = new int[3];
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 1 && currentLoot.get(i).getType() == 2) {
                indexes[i] = i;
            }
        }
        setChoices(indexes);
    }

    public static void setChoicesToItems() {
        int[] indexes = new int[3];
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 3) {
                indexes[i] = i;
            }
        }
        setChoices(indexes);
    }

    public static void setTeam() {
        while (team.size() < 3) {
            setChoices(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
            int choice = choice("Pick a team member (1-8)") - 1;
            boolean inParty = false;
            for (int j = 0; j < team.size(); j++) {
                if (team.get(j).equals(AvailablePartyMembers[choice])) {
                    inParty = true;
                    break;
                }
            }
            if (inParty) {
                sPrintln("You already have this member in the party");
            } else {
                team.add(AvailablePartyMembers[choice]);
                AvailablePartyMembers[choice].setPlayerControlled(true);
                sPrintln(AvailablePartyMembers[choice].getName() + " joined the party");
            }
        }
    }

    public static void addScore(int add) {
        score += add;
        INFO_PANNEL[0].setText("Score: " + score);
    }

    public static void setFloor(int floor) {
        currentFloor = floor;
        INFO_PANNEL[1].setText("Current Floor: " + currentFloor);
    }

    public static void resolveLootEffects() {
        boolean bossFight = false;
        getLoot();
        ArrayList<Character> enemies = new ArrayList<>();
        setCardsInDisplay(new Card[]{currentLoot.get(0), currentLoot.get(1), currentLoot.get(2), currentLoot.get(3), currentLoot.get(4), team.get(0), team.get(1), team.get(2)});
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 2) {
                bossFight = true;
                enemies.add((Character) currentLoot.get(i));
            }
            if (currentLoot.get(i).getType() == 4) {
                ((Event) (currentLoot.get(i))).runEffect();
                deck.addToBottom(currentLoot.get(i));
                currentLoot.remove(i);
            }
            if (currentLoot.get(i).getType() == 0) {
                currentLoot.remove(i);
            }

        }
        for (int i = 0; i < currentLoot.size(); i++) {
            if (currentLoot.get(i).getType() == 1) {
                if (bossFight) {
                    deck.addToBottom(currentLoot.get(i));
                } else {
                    enemies.add((Character) currentLoot.get(i));
                }
            }

        }
        if (!enemies.isEmpty()) {
            int j = 0;
            for (int i = 0; i < currentLoot.size(); i++) {
                if(currentLoot.get(i).getType()==3) {
                    if (enemies.size() <= j) {
                        j = 0;
                    }
                    enemies.get(j).addItem((Item) currentLoot.get(i));
                    j++;
                }

            }
            new Battle(team, enemies);
        }
        setChoicesToTeam();
        for (int i = 0; i < currentLoot.size(); i++) {
            if(currentLoot.get(i).getType()==3) {
                int index = choice("Which party member should get the " + currentLoot.get(i).getName())-1;
                team.get(index).addItem((Item) currentLoot.get(i));
                sPrintln(team.get(index).getName()+" got a "+currentLoot.get(i).getName());
                INFO_PANNEL[index].setText(team.get(index).getItems().size()+"");
            }
        }
    }

    public static ArrayList<Card> getLoot() {
        ArrayList<Card> loot = deck.getRange(0, 5);
        deck.removeRange(0, 5);
        currentLoot = loot;
        return loot;
    }

    public static void gameLoop() {
        for (currentFloor = 0; currentFloor < 100 && !team.isEmpty(); currentFloor++) {
            setFloor(currentFloor);
            resolveLootEffects();
        }
    }

    public void setEscapeFloor(int index) {
        currentFloor = ESCAPE_FLOORS[index];
        INFO_PANNEL[2].setText("Next Rest Floor: " + currentFloor);
    }

    public void updateCharacterHP(Character character) {
        int index = 0;
        for (int i = 0; i < cardsInDisplay.length; i++) {
            if (cardsInDisplay[i] == character) {
                index = i;
            }
        }
        if (character.getHp() == character.getDefense()) {
            HP_DISPLAYS[index].setVisible(false);
        } else {
            HP_DISPLAYS[index].setVisible(true);
            HP_DISPLAYS[index].setText(character.getHp() + "");
        }

    }


}
