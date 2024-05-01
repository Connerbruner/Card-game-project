import java.util.ArrayList;

public class Character extends Card {
    private ArrayList<StatChange> statChanges = new ArrayList<>();
    private Effect[] abilities;
    private ArrayList<Item> items= new ArrayList<>();
    private boolean isBoss;
    private int strength;
    private int defense;
    private int agility;
    private int hp;
    private boolean isPlayer = false;

    public Character(String n,String p, boolean r, Effect[] e,int d,int a, int s) {
        super(n,p, r,1);
        abilities =e;
        isBoss=false;
        defense=d;
        agility=a;
        strength=s;
        hp=defense;
        items.clear();

    }
    public Character(String n,String p, boolean r,boolean b, Effect[] e,int d,int a, int s) {
        super(n,p, r,2);
        abilities =e;
        isBoss=b;
        defense=d;
        agility=a;
        strength=s;
        hp=defense;
        items.clear();

    }


    public void setPlayerControlled(boolean player) {
        isPlayer = player;
    }

    public void attack(ArrayList<Character> team,ArrayList<Character> enemies) {
        int attackIndex;
        if(isPlayer) {
             attackIndex = GameBoard.choice("Chose a Attack");
        } else {
             attackIndex = Main.random(0,items.size()+abilities.length);

        }
        if(attackIndex>items.size()) {
            abilities[attackIndex].attack(team,enemies);
        } else {
            Item item = items.get(attackIndex);
            item.attack(team,enemies);
            if(item.isDiscardAfter()) {
                GameBoard.deck.addToBottom(item);
                items.remove(item);
            }

        }

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

    public Effect[] getAbilities() {
        return abilities;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public ArrayList<StatChange> getStatChanges() {
        return statChanges;
    }
    public void addItem(Item item) {
        if(items.size()<1) {
            items.add(item);
        } else {
            if(isPlayer) {
                //needs finish
            } else {
                items.set(0,item);
            }
        }
    }
}
