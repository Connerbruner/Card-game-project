public class Chest extends Event {
    public Chest(String path, Item[] items) {
        super("Chest", path, () -> {
            Card[] display = new Card[]{GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD};
            GameBoard.setChoicesToTeam();
            for (int i = 0; i < items.length && i < 5; i++) {
                display[i] = items[i];
            }
            for (int i = 0; i < GameBoard.getTeam().size(); i++) {
                display[i + 5] = GameBoard.getTeam().get(i);
            }
            GameBoard.setCardsInDisplay(display);
            GameBoard.sPrintln("Found a chest");

            GameBoard.setTargetDisplay(-1);
            for (Item item : items) {
                GameBoard.setCardsInDisplay(display);
                GameBoard.setChoicesToTeam();
                if (item.getType() == 3) {
                    GameBoard.setNextTargetDisplay();
                    GameBoard.setCardsInDisplay(display);
                    Character target = (Character) GameBoard.choice("Which party member should get the " + item.getName(), GameBoard.getTeam().toArray());
                    target.addItem(item);
                    GameBoard.sPrintln(target.getName() + " got a " + item.getName());
                }
            }
        });
    }
    public Chest(String path,int count,int rarity) {
        super("Chest",path,()-> {
            Item[] arr = new Item[count];
            for(int i=0; i<count; i++) {
                arr[i]=Cards.DIFFERENT_ITEMS[Main.random(rarity,Cards.DIFFERENT_ITEMS.length-1)];
            }
            new Chest(path,arr);
        });

    }


}
