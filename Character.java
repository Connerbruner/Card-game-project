import java.util.ArrayList;

interface CharacterVoid {
    void run(Character user, ArrayList<Character> team, ArrayList<Character> enemies);
}

public class Character extends Card {
    private final ArrayList<StatChange> statChanges = new ArrayList<>();
    private final CharacterVoid[] abilities;
    private final ArrayList<Item> items = new ArrayList<>();
    private final boolean isBoss;
    private final int strength;
    private final int defense;
    private final int agility;
    private int damage = 0;
    private boolean isPlayer = false;

    public Character(String n, String p, CharacterVoid[] e, int s, int d, int a) {
        super(n, p, 1);
        abilities = e;
        isBoss = false;
        defense = d;
        agility = a;
        strength = s;
        items.clear();

    }

    public Character(String n, String p, boolean b, CharacterVoid[] e, int d, int a, int s) {
        super(n, p, 1);
        abilities = e;
        isBoss = b;
        defense = d;
        agility = a;
        strength = s;
        items.clear();

    }

    public Character(Character c) {
        super(c.getName(), c.getPath(), 2);
        abilities = c.abilities;
        isBoss = c.isBoss;
        defense = c.defense;
        agility = c.agility;
        strength = c.strength;
        items.clear();
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
            attackIndex = (int) GameBoard.choice("Chose a Attack", new Object[]{0, 1, 2, 3});
        } else {
            attackIndex = Main.random(0, items.size() - 1 + abilities.length - 1);
        }
        if (attackIndex >= items.size()) {
            abilities[attackIndex % 2].run(this, team, enemies);
        } else {
            Item item = items.get(attackIndex);
            item.attack(this, team, enemies);
            if (item.isDiscardAfter()) {
                items.remove(item);
            }

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

    public boolean isBoss() {
        return isBoss;
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
                GameBoard.setCardsInDisplay(2);
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
    }

    public int getDamage() {
        return damage;
    }

    public int getStrength() {
        return strength + statChangeDiff(0);
    }

    public boolean evadeCheck() {
        return agility < Main.random(0, 100);
    }

    public void displayItems() {
        Card[] currentDisplay = GameBoard.getCardsInDisplay();
        Card[] itemDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], null, null, null};
        for (int i = 0; i < items.size(); i++) {
            itemDisplay[i + 5] = items.get(i);
        }
        GameBoard.setCardsInDisplay(itemDisplay);
    }

    public void removeItem(int i) {
        items.remove(i);
    }

    public void removeItem(Item i) {
        items.remove(i);
    }

    public double getMul() {
        return getStrength() / 100;
    }

    public int statChangeDiff(int index) {
        int diff = 0;
        for (int i = 0; i < statChanges.size(); i++) {
            diff += statChanges.get(i).getStats()[index];
        }
        return diff;

    }

    public void tickDownStats() {
        for (int i = 0; i < statChanges.size(); ) {
            statChanges.get(i).tickDown();
            if (statChanges.get(i).hasRunout()) {
                statChanges.remove(i);
            }
        }
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public ArrayList<StatChange> getStatChanges() {
        return statChanges;
    }
}
