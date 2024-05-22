public class Chest extends Event {
    public Chest(String path, Item[] items) {
        super("Chest", path, () -> {
            Item[] loot = new Item[items.length];
            for (int i = 0; i < items.length; i++) {
                loot[i] = items[Main.randomExponential(0, items.length - 1, 25)];
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
            GameBoard.sPrintln("Found a chest");

            GameBoard.setTargetDisplay(-1);
            for (Item item : loot) {
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


}
