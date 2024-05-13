import java.util.ArrayList;

interface CharacterVoid {
    void run(Character user, ArrayList<Character> team, ArrayList<Character> enemies);
}

public class Character extends Card {
    private  ArrayList<StatChange> statChanges = new ArrayList<>();
    private  CharacterVoid[] abilities;
    private  ArrayList<Item> items = new ArrayList<>();
    private final boolean isBoss;
    private double strength;
    private int defense;
    private int agility;
    private int damage = 0;
    private boolean isPlayer = false;

    public Character(String n, String p, CharacterVoid[] e, double s, int d, int a) {
        super(n, p, 1);
        abilities = e;
        isBoss = false;
        defense = d;
        agility = a;
        strength = s;
        items.clear();

    }

    public Character(String n, String p, boolean b, CharacterVoid[] e, double s, int d, int a) {
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
            if (attackIndex >= items.size()) {
                abilities[attackIndex % 2].run(this, team, enemies);
            } else {
                Item item = items.get(attackIndex);
                item.attack(this, team, enemies);
                if (item.isDiscardAfter()) {
                    items.remove(item);
                }

            }
        } else {
            abilities[Main.random(0,  abilities.length - 1)].run(this,team,enemies);
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

    public double getStrength() {
        return strength + (double) (statChangeDiff(0))/100;
    }

    public boolean evadeCheck(Character enemy) {
        int enemyPower = Main.random(enemy.getAgility(),100);
        int thisPower = Main.random(getAgility(),100);
        System.out.println("enemyPower: "+enemyPower);
        System.out.println("thisPower: "+thisPower);
        System.out.println("enemyAGL: "+enemy.getAgility());
        System.out.println("thisAGL: "+getAgility());
        System.out.println((thisPower<enemyPower)+"");
        System.out.println((getAgility() <= enemy.getAgility())+"");
        System.out.println((getAgility() <= 0)+"");
        if(getAgility() <= 0) {
            return true;
        }
        if(getAgility() <= enemy.getAgility()) {
            return true;
        }
        if(thisPower<enemyPower) {
            return true;
        }
        return false;
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

    public int statChangeDiff(int index) {
        int diff = 0;
        for (int i = 0; i < statChanges.size(); i++) {
            diff += statChanges.get(i).getStats()[index];
        }
        return diff;

    }

    public void tickDownStats() {
        for (int i = 0; i < statChanges.size(); i++) {
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
    public int avgStatTime() {
        if(statChanges.isEmpty()) {
            return 0;
        }
        int amount = 0;
        for(int i=0; i<statChanges.size(); i++) {
            amount+=statChanges.get(i).getTime();
        }
        return amount/statChanges.size();
    }
}
