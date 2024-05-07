import java.util.ArrayList;

public class Battle {
    private ArrayList<Character> team, enemies;
    public Battle(ArrayList<Character> t,ArrayList<Character> e) {
        team=t;
        enemies=e;
        while(!team.isEmpty()&& !enemies.isEmpty()) {

            for(int i=0; i<team.size(); i++) {
                updateHP();
                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(1);
                if(!team.isEmpty() && !enemies.isEmpty()) {
                    team.get(i).tickDownStats();
                    GameBoard.sPrintln(team.get(i).getName()+" Turn");
                    team.get(i).attack(team,enemies);

                }

            }
            System.out.println("round over");

            for(int i=0; i<enemies.size(); i++) {
                updateHP();
                GameBoard.setChoices(new ArrayList<>());
                GameBoard.setCardsInDisplay(1);
                if(!team.isEmpty() && !enemies.isEmpty()) {
                    enemies.get(i).tickDownStats();
                    GameBoard.sPrintln(enemies.get(i).getName()+" Turn");
                    enemies.get(i).attack(enemies,team);
                }



            }
        }
    }
    public void updateHP() {
        for(int i=0; i<team.size(); i++) {
            GameBoard.updateCharacterDisplays(team.get(i));
            if(team.get(i).getDamage()>=team.get(i).getDefense()) {
                team.remove(i);
            }

        }

        for(int i=0; i<enemies.size(); i++) {
            GameBoard.updateCharacterDisplays(enemies.get(i));
            if(enemies.get(i).getDamage()>=enemies.get(i).getDefense()) {
                GameBoard.sPrintln(enemies.get(i).getName()+" died");
                GameBoard.removeFromLoot(enemies.get(i));
                enemies.remove(i);
            }


        }
    }
}
