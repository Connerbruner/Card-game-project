import java.util.ArrayList;
import java.util.Arrays;

interface CharacterVoid {
    void run(Character user, ArrayList<Character> team, ArrayList<Character> enemies);
    String toString();
}

public class Character extends Card {
    private final ArrayList<StatChange> statChanges = new ArrayList<>();
    private final CharacterVoid[] abilities;
    private final ArrayList<Item> items = new ArrayList<>();
    private final double strength;
    private final int defense;
    private final int agility;
    private int damage = 0;
    private boolean isPlayer = false;
    private String[] abilityStrings;

    public Character(String n, String p, CharacterVoid[] e, double s, int d, int a) {
        super(n, p, 2);
        abilities = e;
        defense = d;
        agility = a;
        strength = s;
        abilityStrings = new String[]{"", ""};
        damage=0;

    }

    public Character(String n, String p, CharacterVoid[] e, double s, int d, int a, String[] aS) {
        super(n, p, 2);
        abilities = e;
        defense = d;
        agility = a;
        strength = s;
        abilityStrings = aS;
        damage=0;

    }

    public Character(Character c) {
        super(c.getName(), c.getPath(), 2);
        abilities = c.abilities;
        defense = c.defense;
        agility = c.agility;
        strength = c.strength;
        abilityStrings = c.abilityStrings;
        isPlayer = c.isPlayer;
        damage=0;
    }

    public static boolean basicAttack(int damage, Character target, Character user) {
        if (target.evadeCheck(user)) {
            target.changeHp((int) (damage * user.getStrength()));
            GameBoard.sPrintln(target.getName() + " takes " + (int) (damage * user.getStrength()) + " damage");
            return true;
        } else {
            GameBoard.sPrintln("Missed");
            return false;
        }
    }

    public void setPlayerControlled(boolean player) {
        isPlayer = player;
    }

    public void attack(ArrayList<Character> team, ArrayList<Character> enemies) {
        int attackIndex;
        if (isPlayer) {
            Card[] currentDisplay = GameBoard.getCardsInDisplay();
            Card[] itemDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, this};
            for (int i = 0; i < items.size(); i++) {
                itemDisplay[i + 5] = items.get(i);
            }
            GameBoard.setCardsInDisplay(itemDisplay);
            GameBoard.setChoices(new int[]{5, 6, 7, 8});
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                s.append((i + 1)).append(" = ").append(items.get(i).getName()).append("   ");
            }
            attackIndex = (int) GameBoard.choice("Chose a Attack", new Object[]{0, 1, 2, 3}, s + "3 = "+abilityStrings[0]+"    4 = "+abilityStrings[1]);
            if (attackIndex >= items.size()) {
                abilities[attackIndex % 2].run(this, team, enemies);
            } else {
                Item item = items.get(attackIndex);
                item.attack(this, team, enemies);
                if (item.isDiscardAfter()) {
                    items.remove(item);
                    GameBoard.sPrintln("You get a extra turn");
                    this.attack(team,enemies);
                }
            }
        } else {
            abilities[Main.random(abilities)].run(this, team, enemies);
        }


    }

    public int getAgility() {
        return agility + statChangeDiff(2);
    }

    public int getDefense() {
        return defense + statChangeDiff(1);
    }

    public void addStatChange(StatChange s) {
        statChanges.add(s);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public CharacterVoid[] getAbilities() {
        return abilities;
    }

    public void addItem(Item item) {
        if (items.size() < 2) {
            items.add(item);
        } else {
            if (isPlayer) {
                GameBoard.setChoices(new int[]{5, 6, 7});
                items.add(item);
                displayItems();
                Item remove = (Item) GameBoard.choice("Which item would you like to remove: ", items.toArray());
                GameBoard.sPrintln(remove.getName() + " removed");
                items.remove(remove);
            } else {
                items.set(0, item);
            }
        }
    }

    public void changeHp(int change) {
        damage += change;
        if (damage < 0) {
            damage = 0;
        }
        GameBoard.setTargetDisplay(Arrays.asList(GameBoard.getCardsInDisplay()).indexOf(this));
    }

    public int getDamage() {
        return damage;
    }

    public double getStrength() {
        return strength + (double) (statChangeDiff(0)) / 100;
    }

    public boolean evadeCheck(Character enemy) {
        int enemyPower = Main.random(enemy.getAgility(), 100);
        int thisPower = Main.random(getAgility(), 100);

        if (getAgility() <= 0) {
            return true;
        }
        if (getAgility() <= enemy.getAgility()) {
            return true;
        }
        return thisPower < enemyPower;
    }

    public void displayItems() {
        Card[] currentDisplay = GameBoard.getCardsInDisplay();
        Card[] itemDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], null, null, null};
        for (int i = 0; i < items.size(); i++) {
            itemDisplay[i + 5] = items.get(i);
        }
        GameBoard.setCardsInDisplay(itemDisplay);
    }

    public int statChangeDiff(int index) {
        int diff = 0;
        for (StatChange statChange : statChanges) {
            diff += statChange.getStats()[index];
        }
        return diff;

    }

    public void tickDownStats() {
        for (int i = 0; i < statChanges.size(); i++) {
            statChanges.get(i).tickDown();
            if (statChanges.get(i).hasRunOut()) {
                statChanges.remove(i);
            }
        }
    }

    public String[] getAbilityStrings() {
        return abilityStrings;
    }

    public ArrayList<StatChange> getStatChanges() {
        return statChanges;
    }

}
