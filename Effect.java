import java.util.ArrayList;

public class Effect {
    private int type;
    private Void effectVoid;
    public Effect(int t,Void e) {
        type=t;
        effectVoid=e;
    }

    public int getType() {
        return type;
    }

    public void run() {
        effectVoid.run();
    }
    public void attack(ArrayList<Character> team,ArrayList<Character> enemies) {
        effectVoid.attack(team, enemies);
    }
}
interface Void {
    void run();
    void attack(ArrayList<Character> team,ArrayList<Character> enemies);
}
