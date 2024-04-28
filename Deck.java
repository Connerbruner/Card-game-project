import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    public Deck(Card[] cards) {
        deck= (ArrayList<Card>) Arrays.stream(cards).toList();
    }
    public Card getTopCard() {
        return get(0);
    }
    public Card get(int i) {
        return deck.get(i);
    }
    public ArrayList<Card> getRange(int min,int max) {
        ArrayList<Card> temp = new ArrayList<>();
        for(int i=min; i<max; i++) {
            temp.add(get(i));
        }
        return temp;
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
    public void remove(int i) {
        deck.remove(i);
    }
    public void discard(Card card) {
        deck.add(deck.size()-1,card);
    }
    public boolean areRaresLeft() {
        for(int i=0; i<deck.size(); i++) {
            if(get(i).isRare()) {
                return true;
            }
        }
        return false;
    }




}
