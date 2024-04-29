import java.net.MalformedURLException;

public class Main {
    static Deck deck = new Deck(
            new Card[] {new Card("",false)}
    );
    public static void main(String[] args) throws MalformedURLException {
        new GameBoard(deck);
    }
}
