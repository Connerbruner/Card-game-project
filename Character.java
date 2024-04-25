import java.util.ArrayList;

public class Character extends Card {
    private Effect[] effects;
    private ArrayList<Item> items= new ArrayList<>();
    private boolean isBoss;
    private int strength;
    private int defense;
    private int agility;

    public Character(String p, boolean r, Effect[] e,boolean b,int d,int a, int s) {
        super(p, r,1);
        effects=e;
        isBoss=b;
        defense=d;
        agility=a;
        strength=s;
        items.clear();

    }
    public void attack() {
        //needs to finish
    }

    public int getAgility() {
        return agility;
    }

    public int getDefense() {
        return defense;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Effect[] getEffects() {
        return effects;
    }

    public boolean isBoss() {
        return isBoss;
    }
}
