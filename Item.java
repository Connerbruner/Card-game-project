import java.util.ArrayList;

public class Item extends Card {
    private ItemVoid effect;
    private boolean discardAfter = false;
    public Item(String n, String p, boolean r, ItemVoid e, boolean d) {
        super(n, p, r, 3);
        discardAfter = d;
        effect = e;

    }
    public void attack(Character user, ArrayList<Character> team, ArrayList<Character> enemies) {
        effect.run(user, team, enemies);
    }

    public boolean isDiscardAfter() {
        return discardAfter;
    }
}
interface ItemVoid {
    void run(Character user, ArrayList<Character> team, ArrayList<Character> enemies );
}
