import java.util.ArrayList;

public class Battle {
    private ArrayList<Character> team, enemies;
    GameBoard gameBoard;
    public Battle(ArrayList<Character> t,ArrayList<Character> e) {
        team=t;
        enemies=e;
        while(!team.isEmpty() || !enemies.isEmpty()) {
            for(int i=0; i<team.size(); i++) {
                team.get(i).attack(team,enemies);
                updateHP();
            }
            for(int i=0; i<enemies.size(); i++) {
                enemies.get(i).attack(enemies,team);
                updateHP();
            }
        }
    }
    public void updateHP() {
        for(int i=0; i<team.size(); i++) {
            gameBoard.updateCharacterHP(team.get(i));
            if(team.get(i).getHp()<=0) {
                team.remove(i);
            }
        }

        for(int i=0; i<enemies.size(); i++) {
            gameBoard.updateCharacterHP(enemies.get(i));
            if(enemies.get(i).getHp()<=0) {
                enemies.remove(i);
            }
        }
    }
}
