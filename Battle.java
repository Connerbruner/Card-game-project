import java.util.ArrayList;

public class Battle {
    private ArrayList<Character> team;
    private ArrayList<Character> enemies;
    private int turnsLasted;
    private int enemyCount;

    public Battle(ArrayList<Character> t, ArrayList<Character> e) {
        turnsLasted=0;
        team = t;
        enemies = e;
        enemyCount = e.size();
        if(GameBoard.getPermanentStatChange()[0]!=0) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).addStatChange(new StatChange(GameBoard.getPermanentStatChange(),99));
            }
        }
        while (!team.isEmpty() && !enemies.isEmpty()) {
            turnsLasted++;
            for (int i = 0; i < team.size(); i++) {
                updateHP();
                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(2);
                if (!team.isEmpty() && !enemies.isEmpty()) {
                    if (team.get(i).getDamage() < team.get(i).getDefense()) {
                        GameBoard.setTargetDisplay(i+5);
                        team.get(i).tickDownStats();
                        GameBoard.sPrintln(team.get(i).getName() + " Turn");
                        GameBoard.setTargetDisplay(-1);

                        team.get(i).attack(team, enemies);
                    }


                }

            }

            for (int i = 0; i < enemies.size(); i++) {

                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(2);
                if (!team.isEmpty() && !enemies.isEmpty()) {
                    if (enemies.get(i).getDamage() < enemies.get(i).getDefense()) {
                        GameBoard.setTargetDisplay(i);
                        enemies.get(i).tickDownStats();
                        GameBoard.sPrintln(enemies.get(i).getName() + " Turn");
                        enemies.get(i).attack(enemies, team);

                    }
                }
                updateHP();


            }

        }
        int score = 150-((turnsLasted-1)*15);
        for(int i=0; i<enemyCount; i++) {
            score+=Main.random(i*10,100);
        }
        GameBoard.sPrintln("Score +"+score);
        GameBoard.addScore(score);

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
