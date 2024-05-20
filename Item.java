import java.util.ArrayList;

interface ItemVoid {
    void run(Character user, ArrayList<Character> team, ArrayList<Character> enemies);
}

public class Item extends Card {
    private final ItemVoid effect;
    private boolean discardAfter;

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

    public Item(String n, String p, int low, int high, boolean d) {
        super(n, p, 3);
        discardAfter = d;
        effect = (user, team, enemies) -> {

            GameBoard.setChoicesToEnemies();
            Character enemy = (Character) GameBoard.choice("Who would you like to attack", enemies.toArray());

            int damage = (int) (Main.random(low, high) * user.getStrength());
            if (enemy.evadeCheck(user)) {
                enemy.changeHp(damage);
                GameBoard.sPrintln(enemy.getName() + " took " + damage + " damage");
            } else {
                GameBoard.sPrintln("Missed");
            }

        };

    }

    public Item(String n, String p, int times, int high, int low, boolean d) {
        super(n, p, 3);
        discardAfter = d;
        effect = (user, team, enemies) -> {

            GameBoard.setChoicesToEnemies();
            Character enemy = (Character) GameBoard.choice("Who would you like to attack", enemies.toArray());
            int hits=0;
            for (int i = 0; i < times; i++) {
                if (enemy.evadeCheck(user)) {
                  hits++;
                }
            }
            int damage = (int) (hits *Main.random(low, high)*user.getStrength());
            enemy.changeHp(damage);
            GameBoard.sPrintln(enemy.getName() + " got hit " + hits + " times");
            GameBoard.sPrintln(enemy.getName() + " took "+damage+" damage");


        };

    }

    public Item(String n, String p, int high, int low) {
        super(n, p, 3);
        discardAfter = true;
        effect = (user, team, enemies) -> {
            GameBoard.setChoicesToTeam();
            GameBoard.setCardsInDisplay(2);
            Character teamMate = (Character) GameBoard.choice("Who would you like to heal", team.toArray());

            int damage = Main.random(low, high);
            teamMate.changeHp(-damage);
            GameBoard.sPrintln(teamMate.getName() + " healed " + damage + " damage");

        };

    }

    public void attack(Character user, ArrayList<Character> team, ArrayList<Character> enemies) {
        effect.run(user, team, enemies);
    }

    public boolean isDiscardAfter() {
        return discardAfter;
    }
}
