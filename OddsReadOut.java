import java.util.ArrayList;

public class OddsReadOut {
    public static void main(String[] args) {
        ArrayList<Double> data = Deck.read("data.txt");
        System.out.println("Enemy hands: "+(data.get(0)/data.get(7)));
        System.out.println("Item hands: "+data.get(1)/data.get(7));
        System.out.println("Event event: "+data.get(2)/data.get(7));
        System.out.println("Blank hands: "+data.get(3)/data.get(7));
        System.out.println("Laser hands: "+data.get(4)/data.get(7));
        System.out.println("Plasma hands: "+data.get(5)/data.get(7));
        System.out.println("Maddox odds: "+data.get(6)/data.get(7));
    }
}
