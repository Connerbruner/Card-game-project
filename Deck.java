import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static Deck BASE_DECK = new Deck(new Card[]{

            GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD,
            new Document(Cards.DIFFERENT_DOCUMENTS[0]),
            new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]), new Document(Cards.DIFFERENT_DOCUMENTS[1]),
            new Document(Cards.DIFFERENT_DOCUMENTS[2]), new Document(Cards.DIFFERENT_DOCUMENTS[2]),
            new Document(Cards.DIFFERENT_DOCUMENTS[3]), new Document(Cards.DIFFERENT_DOCUMENTS[3]),


            new Event(Cards.DIFFERENT_EVENTS[0]), new Event(Cards.DIFFERENT_EVENTS[0]), new Event(Cards.DIFFERENT_EVENTS[1]),
            new Event(Cards.DIFFERENT_EVENTS[1]), new Event(Cards.DIFFERENT_EVENTS[1]), new Event(Cards.DIFFERENT_EVENTS[1]),
            new Event(Cards.DIFFERENT_EVENTS[2]), new Event(Cards.DIFFERENT_EVENTS[2]), new Event(Cards.DIFFERENT_EVENTS[2]), new Event(Cards.DIFFERENT_EVENTS[2]),
            new Event(Cards.DIFFERENT_EVENTS[3]), new Event(Cards.DIFFERENT_EVENTS[3]), new Event(Cards.DIFFERENT_EVENTS[3]), new Event(Cards.DIFFERENT_EVENTS[3]),

            new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]), new Character(Cards.DIFFERENT_CHARACTERS[0]),
            new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]), new Character(Cards.DIFFERENT_CHARACTERS[1]),
            new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]), new Character(Cards.DIFFERENT_CHARACTERS[2]),
            new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]), new Character(Cards.DIFFERENT_CHARACTERS[3]),
            new Character(Cards.DIFFERENT_CHARACTERS[4]), new Character(Cards.DIFFERENT_CHARACTERS[4]), new Character(Cards.DIFFERENT_CHARACTERS[4]),
            new Character(Cards.DIFFERENT_CHARACTERS[5]), new Character(Cards.DIFFERENT_CHARACTERS[5]), new Character(Cards.DIFFERENT_CHARACTERS[5]),
            new Character(Cards.DIFFERENT_CHARACTERS[6]), new Character(Cards.DIFFERENT_CHARACTERS[6]), new Character(Cards.DIFFERENT_CHARACTERS[6]), new Character(Cards.DIFFERENT_CHARACTERS[6]),
            new Character(Cards.DIFFERENT_CHARACTERS[7]), new Character(Cards.DIFFERENT_CHARACTERS[7]), new Character(Cards.DIFFERENT_CHARACTERS[7]),
            new Character(Cards.DIFFERENT_CHARACTERS[8]), new Character(Cards.DIFFERENT_CHARACTERS[8]), new Character(Cards.DIFFERENT_CHARACTERS[8]), new Character(Cards.DIFFERENT_CHARACTERS[8]),

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
            new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]), new Item(Cards.DIFFERENT_ITEMS[16]),
            new Item(Cards.DIFFERENT_ITEMS[17]), new Item(Cards.DIFFERENT_ITEMS[17]), new Item(Cards.DIFFERENT_ITEMS[17]), new Item(Cards.DIFFERENT_ITEMS[17]),
            new Item(Cards.DIFFERENT_ITEMS[18]), new Item(Cards.DIFFERENT_ITEMS[18]), new Item(Cards.DIFFERENT_ITEMS[18]), new Item(Cards.DIFFERENT_ITEMS[18]),
            new Item(Cards.DIFFERENT_ITEMS[19]), new Item(Cards.DIFFERENT_ITEMS[19]), new Item(Cards.DIFFERENT_ITEMS[19]),
            new Item(Cards.DIFFERENT_ITEMS[20]), new Item(Cards.DIFFERENT_ITEMS[20]),
    });
    private final ArrayList<Card> deck;
    private final ArrayList<Double> data;
    private final int startingSize;
    private final int maxHands;

    /**
     * data order:
     * 0 enemy
     * 1 item
     * 2 event
     * 3 blank
     * 4 laser
     * 5 plasma
     * 6 maddox
     * 7 hand count
     **/

    public Deck(Card[] cards) {
        deck = new ArrayList<>();
        Collections.addAll(deck, cards);
        startingSize = deck.size();
        data = read("data.txt");
        maxHands=startingSize/5;

    }

    public static ArrayList<Double> read(String file) {

        try {
            File txt = new File(file);
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);
            ArrayList<Double> arr = new ArrayList<>();

            for (int r = 0; r < 8; r++) {
                arr.add(Double.parseDouble(reader.readLine()));
            }
            reader.close();
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static void edit( String filePath , ArrayList<Double> arr ) {
        File       fileToBeModified = new File( filePath );
        FileWriter writer           = null;
        try {
            writer = new FileWriter( fileToBeModified );
            for ( Object o : arr ) {
                if ( o != null ) {
                    String print = o + "\n";
                    writer.write( print+"" );
                }
            }

        } catch ( IOException e ) {
            e.printStackTrace( );
        } finally {
            try {
                //Closing the resources
                assert writer != null;
                writer.close( );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }

    public int getStartingSize() {
        return startingSize;
    }

    public Card get(int i) {
        return deck.get(i);
    }

    public ArrayList<Card> getRange(int min, int max) {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = min; i < max && i < deck.size(); i++) {
            temp.add(get(i));
        }
        return temp;
    }

    public ArrayList<Card> getRange(int min, int max, boolean maddox) {
        ArrayList<Card> temp = new ArrayList<>();

        if (maddox) {
            if (Main.random(GameBoard.getCurrentFloor() ^ 2, maxHands ^ 2) == (maxHands ^ 2)) {
                temp.add(Cards.MADDOX_BOSSES[0]);
                tickUpData(6);
                return temp;
            }
        }
        boolean[] alreadyTicked = {false, false, false, false, false, false};
        for (int i = min; i < max && i < deck.size(); i++) {
            temp.add(get(i));
            if (!alreadyTicked[0] && get(i).getType() == 2) {
                tickUpData(0);
                alreadyTicked[0] = true;
            }  else if (get(i).getType() == 3) {
                if (!alreadyTicked[4] && get(i).getName().contains("Laser")) {
                    tickUpData(4);
                    alreadyTicked[4] = true;
                } else if (!alreadyTicked[5] && get(i).getName().contains("Plasma")) {
                    tickUpData(5);
                    alreadyTicked[5] = true;
                } else if (!alreadyTicked[1]) {
                    tickUpData(1);
                    alreadyTicked[1] = true;

                }
            } else if (!alreadyTicked[2] && get(i).getType() == 4) {
                tickUpData(2);
                alreadyTicked[2] = true;
            } else if (!alreadyTicked[3] && get(i).getType() == 0) {
                tickUpData(3);
                alreadyTicked[3] = true;
            }
        }
        tickUpData(7);
        edit("data.txt",data);

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
        for (int i = min; i < max && i < deck.size(); i++) {
            deck.remove(min);
        }
    }

    public void removeRange(int min, int max, int notType) {
        for (int i = min; i < max && i < deck.size(); i++) {
            if (deck.get(min).getType() != notType) {
                deck.remove(min);
            }
        }
    }


    public ArrayList<Card> search(int count, String target) {
        ArrayList<Card> found = new ArrayList<>();
        for (int i = 0; i < count && i < deck.size(); i++) {
            if (deck.get(i).getName() != null) {
                if (deck.get(i).getName().contains(target)) {
                    found.add(deck.get(i));
                }
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
        for (int i = 0; i < count && i < deck.size(); i++) {
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

    public void tickUpData(int i) {
        data.set(i, data.get(i) + 1);

    }

}
