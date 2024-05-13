import java.util.ArrayList;

public class Battle {
    private final ArrayList<Character> team;
    private final ArrayList<Character> enemies;

    public Battle(ArrayList<Character> t, ArrayList<Character> e) {
        team = t;
        enemies = e;
        while (!team.isEmpty() && !enemies.isEmpty()) {

            for (int i = 0; i < team.size(); i++) {
                updateHP();
                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(2);
                if (!team.isEmpty() && !enemies.isEmpty()) {
                    if (team.get(i).getDamage() < team.get(i).getDefense()) {
                        team.get(i).tickDownStats();
                        GameBoard.sPrintln(team.get(i).getName() + " Turn");
                        team.get(i).attack(team, enemies);
                    }


                }

            }
            System.out.println("round over");

            for (int i = 0; i < enemies.size(); i++) {

                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(2);
                if (!team.isEmpty() && !enemies.isEmpty()) {
                    if (enemies.get(i).getDamage() < enemies.get(i).getDefense()) {
                        enemies.get(i).tickDownStats();
                        GameBoard.sPrintln(enemies.get(i).getName() + " Turn");
                        enemies.get(i).attack(enemies, team);
                    }
                }
                updateHP();


            }
        }
    }

    public void updateHP() {
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i).getDamage() >= team.get(i).getDefense()) {
                GameBoard.sPrintln(team.get(i).getName() + " died");

                team.remove(i);
            }

        }

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getDamage() >= enemies.get(i).getDefense()) {
                GameBoard.sPrintln(enemies.get(i).getName() + " died");
                GameBoard.removeFromLoot(enemies.get(i));
                enemies.remove(i);
            }


        }
    }
}
