import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static final Deck BASE_DECK = new Deck(new Card[]{
            GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD,
            new Document(Cards.DIFFERENT_DOCUMENTS[0]), new Document(Cards.DIFFERENT_DOCUMENTS[0]),
            new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]),
            new Document(Cards.DIFFERENT_DOCUMENTS[2]),
            new Document(Cards.DIFFERENT_DOCUMENTS[3]), new Document(Cards.DIFFERENT_DOCUMENTS[3]), new Document(Cards.DIFFERENT_DOCUMENTS[3]),

            new Chest((Chest) Cards.DIFFERENT_EVENTS[0]),new Chest((Chest) Cards.DIFFERENT_EVENTS[0]),new Chest((Chest) Cards.DIFFERENT_EVENTS[0]),
            new Chest((Chest) Cards.DIFFERENT_EVENTS[1]),new Chest((Chest) Cards.DIFFERENT_EVENTS[1]),
            new Chest((Chest) Cards.DIFFERENT_EVENTS[2]),
            new Chest((Chest) Cards.DIFFERENT_EVENTS[3]),new Chest((Chest) Cards.DIFFERENT_EVENTS[3]),
            new Chest((Chest) Cards.DIFFERENT_EVENTS[4]),
            new Event(Cards.DIFFERENT_EVENTS[5]),
            new Event(Cards.DIFFERENT_EVENTS[6]),new Event(Cards.DIFFERENT_EVENTS[6]),new Event(Cards.DIFFERENT_EVENTS[6]),new Event(Cards.DIFFERENT_EVENTS[6]),
            new Event(Cards.DIFFERENT_EVENTS[7]),new Event(Cards.DIFFERENT_EVENTS[7]),

            new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]),new Character(Cards.DIFFERENT_CHARACTERS[0]),
            new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]),
            new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]),
            new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]),
            new Character(Cards.DIFFERENT_CHARACTERS[4]), new Character(Cards.DIFFERENT_CHARACTERS[4]), new Character(Cards.DIFFERENT_CHARACTERS[4]),
            new Character(Cards.DIFFERENT_CHARACTERS[5]), new Character(Cards.DIFFERENT_CHARACTERS[5]), new Character(Cards.DIFFERENT_CHARACTERS[5]),
            new Character(Cards.DIFFERENT_CHARACTERS[6]), new Character(Cards.DIFFERENT_CHARACTERS[6]), new Character(Cards.DIFFERENT_CHARACTERS[6]),
            new Character(Cards.DIFFERENT_CHARACTERS[7]),new Character(Cards.DIFFERENT_CHARACTERS[7]),new Character(Cards.DIFFERENT_CHARACTERS[7]),

            new Item(Cards.DIFFERENT_ITEMS[0]), new Item(Cards.DIFFERENT_ITEMS[0]), new Item(Cards.DIFFERENT_ITEMS[0]), new Item(Cards.DIFFERENT_ITEMS[0]),
            new Item(Cards.DIFFERENT_ITEMS[1]), new Item(Cards.DIFFERENT_ITEMS[1]), new Item(Cards.DIFFERENT_ITEMS[1]), new Item(Cards.DIFFERENT_ITEMS[1]),
            new Item(Cards.DIFFERENT_ITEMS[2]), new Item(Cards.DIFFERENT_ITEMS[2]), new Item(Cards.DIFFERENT_ITEMS[2]), new Item(Cards.DIFFERENT_ITEMS[2]),
            new Item(Cards.DIFFERENT_ITEMS[3]), new Item(Cards.DIFFERENT_ITEMS[3]), new Item(Cards.DIFFERENT_ITEMS[3]), new Item(Cards.DIFFERENT_ITEMS[3]),
            new Item(Cards.DIFFERENT_ITEMS[4]), new Item(Cards.DIFFERENT_ITEMS[4]), new Item(Cards.DIFFERENT_ITEMS[4]), new Item(Cards.DIFFERENT_ITEMS[4]),
            new Item(Cards.DIFFERENT_ITEMS[5]), new Item(Cards.DIFFERENT_ITEMS[5]), new Item(Cards.DIFFERENT_ITEMS[5]), new Item(Cards.DIFFERENT_ITEMS[5]),
            new Item(Cards.DIFFERENT_ITEMS[6]), new Item(Cards.DIFFERENT_ITEMS[6]), new Item(Cards.DIFFERENT_ITEMS[6]), new Item(Cards.DIFFERENT_ITEMS[5]),
            new Item(Cards.DIFFERENT_ITEMS[7]), new Item(Cards.DIFFERENT_ITEMS[7]), new Item(Cards.DIFFERENT_ITEMS[7]), new Item(Cards.DIFFERENT_ITEMS[7]),
            new Item(Cards.DIFFERENT_ITEMS[8]), new Item(Cards.DIFFERENT_ITEMS[8]), new Item(Cards.DIFFERENT_ITEMS[8]), new Item(Cards.DIFFERENT_ITEMS[8]),

            new Item(Cards.DIFFERENT_ITEMS[9]),
            new Item(Cards.DIFFERENT_ITEMS[10]),
            new Item(Cards.DIFFERENT_ITEMS[11]),
            new Item(Cards.DIFFERENT_ITEMS[12]),

            new Item(Cards.DIFFERENT_ITEMS[13]), new Item(Cards.DIFFERENT_ITEMS[13]),
            new Item(Cards.DIFFERENT_ITEMS[14]), new Item(Cards.DIFFERENT_ITEMS[14]), new Item(Cards.DIFFERENT_ITEMS[14]), new Item(Cards.DIFFERENT_ITEMS[14]),
            new Item(Cards.DIFFERENT_ITEMS[15]), new Item(Cards.DIFFERENT_ITEMS[15]), new Item(Cards.DIFFERENT_ITEMS[15]), new Item(Cards.DIFFERENT_ITEMS[15]),
            new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]),});
    private final ArrayList<Card> deck;

    public Deck(Card[] cards) {
        deck = new ArrayList<>();
        Collections.addAll(deck, cards);
    }

    public Card getTopCard() {
        return get(0);
    }

    public Card get(int i) {
        return deck.get(i);
    }

    public ArrayList<Card> getRange(int min, int max) {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = min; i < max && i<deck.size(); i++) {
            temp.add(get(i));
        }
        return temp;
    }

    public ArrayList<Card> getRange(int max) {
        return getRange(0, max);
    }

    public void shuffle() {
        GameBoard.setInfoPanelText(3, "Cards left: " + deck.size());
        Collections.shuffle(deck);
    }

    public void remove(int i) {
        GameBoard.setInfoPanelText(3, "Cards left: " + deck.size());
        deck.remove(i);
    }

    public void removeRange(int min, int max) {
        for (int i = min; i < max && i<deck.size(); i++) {
            deck.remove(min);
        }
    }

    public void removeRange(int min, int max, int notType) {
        for (int i = min; i < max && i<deck.size(); i++) {
            if (deck.get(min).getType() != notType) {
                deck.remove(min);
            }
        }
    }


    public void add(Card card) {
        GameBoard.setInfoPanelText(3, "Cards left: " + deck.size());

        deck.add(card);
    }


    public ArrayList<Card> search(int count, String target) {
        ArrayList<Card> found = new ArrayList<>();
        for (int i = 0; i < count && i<deck.size(); i++) {
            if (deck.get(i).getName().contains(target)) {
                found.add(deck.get(i));
            }
        }
        return found;
    }

    public ArrayList<Card> search(String target) {
        return search(deck.size(), target);
    }

    public ArrayList<Card> search(int count, int type) {
        ArrayList<Card> found = new ArrayList<>();
        if (count > deck.size()) {
            count = deck.size();
        }
        for (int i = 0; i < count && i<deck.size(); i++) {
            if (deck.get(i).getType() == type) {
                found.add(deck.get(i));
            }
        }
        return found;
    }

    public Card searchTop(int type) {
        Card found = null;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getType() == type) {
                found = deck.get(i);
                break;
            }
        }
        return found;
    }

    public Card searchTop(String name) {
        Card found = null;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getName().contains(name)) {
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
