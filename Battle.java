import java.util.ArrayList;

public class Battle {
    private ArrayList<Character> team, enemies;
    public Battle(ArrayList<Character> t,ArrayList<Character> e) {
        team=t;
        enemies=e;
        while(!team.isEmpty() || !enemies.isEmpty()) {
            for(int i=0; i<team.size(); i++) {
                team.get(i).attack(team,enemies);
            }
            for(int i=0; i<enemies.size(); i++) {
                enemies.get(i).attack(enemies,team);
            }
        }
    }
}
