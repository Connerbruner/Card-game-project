import java.util.ArrayList;

public class Item extends Card {
    private Effect effect;
    private boolean discardAfter = false;

    public Item(String n, String p, boolean r, Effect e) {
        super(n, p, r, 4);
        effect = e;
    }

    public Item(String n, String p, boolean r, Effect e, boolean d) {
        super(n, p, r, 3);
        discardAfter = d;
        effect = e;

    }

    public void runEvent() {
        effect.run();
    }
    public void attack(ArrayList<Character> team,ArrayList<Character> enemies) {
        effect.attack(team, enemies);
    }

    public boolean isDiscardAfter() {
        return discardAfter;
    }
}
