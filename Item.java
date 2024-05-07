import java.util.ArrayList;

public class Item extends Card {
    private ItemVoid effect;
    private boolean discardAfter = false;
    public Item(String n, String p, ItemVoid e, boolean d) {
        super(n, p, 3);
        discardAfter = d;
        effect = e;

    }
    public Item(Item item) {
        super(item.getName(), item.getPath(), 3);
        discardAfter = item.discardAfter;
        effect = item.effect;
    }
    public Item(String n, String p,int low,int high, boolean d) {
        super(n, p, 3);
        discardAfter = d;
        effect = (user, team, enemies) -> {
            int index = Main.random(0, enemies.size() - 1);
            if (user.isPlayer()) {
                GameBoard.setChoicesToEnemies();
                enemy = GameBoard.choice("Who would you like to attack",enemies.toArray());
            }
            int damage = Main.random(low, high);
            enemies.get(index).changeHp(damage);
            GameBoard.sPrintln(enemies.get(index).getName() + " took " + d + " damage");
        };

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
