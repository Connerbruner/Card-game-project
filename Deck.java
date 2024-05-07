import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(Card[] cards) {
        deck = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            deck.add(cards[i]);
        }
    }

    public Card getTopCard() {
        return get(0);
    }

    public Card get(int i) {
        return deck.get(i);
    }

    public ArrayList<Card> getRange(int min, int max) {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = min; i < max; i++) {
            temp.add(get(i));
        }
        return temp;
    }
    public ArrayList<Card> getRange(int max) {
        return getRange(0,max);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void remove(int i) {
        deck.remove(i);
    }
    public void removeRange(int min,int max) {
        for (int i = min; i < max; i++) {
            deck.remove(min);
        }
    }

    public void addToBottom(Card card) {
        deck.add(deck.size() - 1, card);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public boolean areRaresLeft() {
        for (int i = 0; i < deck.size(); i++) {
            if (get(i).isRare()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> search(int count,String target) {
        ArrayList<Card> found = new ArrayList<>();
        for(int i=0; i<count; i++) {
            if(deck.get(i).getName().contains(target)) {
                found.add(deck.get(i));
            }
        }
        return found;
    }
    public ArrayList<Card> search(String target) {
        return search(deck.size(),target);
    }
    public ArrayList<Card> search(int count,int type) {
        ArrayList<Card> found = new ArrayList<>();
        if(count>deck.size()) {
            count=deck.size();
        }
        for(int i=0; i<count; i++) {
            if(deck.get(i).getType()==type) {
                found.add(deck.get(i));
            }
        }
        return found;
    }
    public Card searchTop(int type) {
        Card found = null;
        for(int i=0; i<deck.size(); i++) {
            if(deck.get(i).getType()==type) {
                found = deck.get(i);
                break;
            }
        }
        return found;
    }
    public Card searchTop(String name) {
        Card found = null;
        for(int i=0; i<deck.size(); i++) {
            if(deck.get(i).getName().contains(name)) {
                found = deck.get(i);
                break;
            }
        }
        return found;
    }
    public int size() {
        return deck.size();
    }

}
