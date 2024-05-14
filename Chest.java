import java.util.ArrayList;

public class Chest extends Event{
    public Chest(String path,Item[] items, int amount) {
        super("Chest", path, () -> {
            GameBoard.sPrintln("Found a chest");
            Item[] loot = new Item[amount];
            for(int i=0; i<amount; i++) {
                loot[i]=items[Main.random(0,items.length-1)];
            }
            Card[] display = new Card[]{GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD};
            GameBoard.setChoicesToTeam();
            for (int i = 0; i < loot.length && i < 5; i++) {
                display[i] = loot[i];
            }
            for (int i = 0; i < GameBoard.getTeam().size(); i++) {
                display[i + 5] = GameBoard.getTeam().get(i);
            }
            GameBoard.setCardsInDisplay(display);
            for (Card card : loot) {
                Character character = (Character) GameBoard.choice("Which party member should get the " + card.getName(), GameBoard.getTeam().toArray());
                GameBoard.sPrintln(character.getName() + " got a " + card.getName());
                character.addItem((Item) card);
            }
        });


    }
    public Chest(String path,Item[] items) {
        super("Chest", path, () -> {
            GameBoard.sPrintln("Chest Found");
            Card[] display = new Card[]{GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD};
            GameBoard.setChoicesToTeam();
            for (int i = 0; i < items.length && i < 5; i++) {
                display[i] = items[i];
            }
            for (int i = 0; i < GameBoard.getTeam().size(); i++) {
                display[i + 5] = GameBoard.getTeam().get(i);
            }
            GameBoard.sPrintln("Found a chest");
            GameBoard.setCardsInDisplay(display);
            for (Card card : items) {
                Character character = (Character) GameBoard.choice("Which party member should get the " + card.getName(), GameBoard.getTeam().toArray());
                GameBoard.sPrintln(character.getName() + " got a " + card.getName());
                character.addItem((Item) card);
            }
        });
    }
    public Chest(String path,int start, int end, int amount) {
        super("Chest", path, () -> {
            GameBoard.sPrintln("Chest Found");
            Item[] loot = new Item[amount];
            for(int i=0; i<amount; i++) {
                loot[i]=Cards.DIFFERENT_ITEMS[Main.random(start,end)];
            }
            Card[] display = new Card[]{GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD};
            GameBoard.setChoicesToTeam();
            for (int i = 0; i < loot.length && i < 5; i++) {
                display[i] = loot[i];
            }
            for (int i = 0; i < GameBoard.getTeam().size(); i++) {
                display[i + 5] = GameBoard.getTeam().get(i);
            }
            GameBoard.setCardsInDisplay(display);
            for (Card card : loot) {
                Character character = (Character) GameBoard.choice("Which party member should get the " + card.getName(), GameBoard.getTeam().toArray());
                GameBoard.sPrintln(character.getName() + " got a " + card.getName());
                character.addItem((Item) card);
            }
        });

    }
    public Chest(Chest c) {
        super(c.getName(),c.getPath(),c.effect);
    }


}
