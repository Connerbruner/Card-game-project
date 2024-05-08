import java.util.ArrayList;

public class Cards {
    public static final Document[] DIFFERENT_DOCUMENTS = new Document[]{
            new Document("F35 cad", "Cards/F35 cad.png", 3000),
            new Document("Password sticker", "Cards/passwordSticker.png", 100),
            new Document("Woke agenda", "Cards/woke agenda.png", 1),
            new Document("Joe Bidens shopping list", "Cards/shopping list.png", 1000)
    };
    public static final Item[] DIFFERENT_ITEMS = new Item[]{
            new Item("Laser Sword", "Cards/laserSword.png", 20, 25, false),
            new Item("Plasma Sword", "Cards/plasmaSword.png", 30, 35, false),
            new Item("Laser Bow", "Cards/laserBow.png", 3,20, 20, false),
            new Item("Cursed Cube","Cards/cursedCube.png",-100,100,false)
    };
    public static final Character[] DIFFERENT_CHARACTERS = new Character[]{
            new Character("Prototype", "Cards/prototype.png", new CharacterVoid[]{(user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{-10, 0, 5}, 3));
                GameBoard.sPrintln("Prototype Gained 5 Agility but lost 10 Strength");
            }, (user, team, enemies) -> {
                int index = Main.random(0, team.size() - 1);
                if (team.get(index).evadeCheck()) {
                    GameBoard.sPrintln(team.get(index).getName() + " takes " + user.getAgility());
                    team.get(index).changeHp(user.getAgility());
                }
            },}, 60, 30, 60)
    };
    public static final Deck deck = new Deck(new Card[]{
            new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]),
            GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD,
            new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]),
            new Document(DIFFERENT_DOCUMENTS[0]), new Document(DIFFERENT_DOCUMENTS[0]),
            new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]),
            new Document(DIFFERENT_DOCUMENTS[2]),
            new Document(DIFFERENT_DOCUMENTS[3]), new Document(DIFFERENT_DOCUMENTS[3]), new Document(DIFFERENT_DOCUMENTS[3]),
            new Item(DIFFERENT_ITEMS[1]),new Item(DIFFERENT_ITEMS[1]),
            new Item(DIFFERENT_ITEMS[2]),new Item(DIFFERENT_ITEMS[2]),new Item(DIFFERENT_ITEMS[2]),
            new Item(DIFFERENT_ITEMS[3]),new Item(DIFFERENT_ITEMS[3]),
    });

    public static final Character[] AvailablePartyMembers = new Character[]{new Character("Mir", "Cards/mir.png", true, new CharacterVoid[]{(user, team, enemies) -> {
        for (int i = 0; i < team.size(); i++) {
            team.get(i).changeHp(-30);
            GameBoard.sPrintln(team.get(i).getName() + " healed 30 damage");
        }
    }, (user, team, enemies) -> {
        GameBoard.setChoices(new int[]{5, 6, 7});
        Character character = (Character) GameBoard.choice("Which Teammate would you like to heal",team.toArray());
        character.changeHp(-100);
        GameBoard.sPrintln(character.getName() + " healed 100 Damage");

    },}, 90, 125, 0), new Character("Leo", "Cards/Leo.png", new CharacterVoid[]{(user, team, enemies) -> {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).addStatChange(new StatChange(new int[]{0, 0, -100}, 3));
        }
        GameBoard.sPrintln("Enemies no longer can evade");
    }, (user, team, enemies) -> {
        Character target = (Character) GameBoard.choice("Who would you like to attack? ",enemies.toArray());
        if (target.getAgility() > 0) {
            GameBoard.sPrintln("Missed");
        } else {
            target.changeHp(100);
            GameBoard.sPrintln("dealt 100 damage");
        }

    },}, 100, 50, 35), new Character("Arrokoth", "Cards/Arrokoth.png", new CharacterVoid[]{(user, team, enemies) -> {
        user.addStatChange(new StatChange(new int[]{-50, 20, 15}, 3));
        GameBoard.sPrintln("Stats changed to 50,80,45");

    }, (user, team, enemies) -> {
        user.addStatChange(new StatChange(new int[]{-5, 60, -30}, 3));
        GameBoard.sPrintln("Stats changed to 95,120,0");


    },}, 100, 60, 30), new Character("Gliese", "Cards/Gliese.png", new CharacterVoid[]{(user, team, enemies) -> {
        GameBoard.setChoicesToEnemies();
        Character target = (Character) GameBoard.choice("Who would you like to attack? ",enemies.toArray());
        target.changeHp(50);
        GameBoard.sPrintln("damage dealt 50");

    }, (user, team, enemies) -> {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).addStatChange(new StatChange(new int[]{-10, -10, -10}, 3));
        }
        GameBoard.sPrintln("Enemies stats lowered 10");
    },}, 105, 70, 20), new Character("Baidam", "Cards/baidam card.png", new CharacterVoid[]{(user, team, enemies) -> {
        GameBoard.setChoicesToEnemies();
        Character target = (Character) GameBoard.choice("Who would you like to attack? ",enemies.toArray());
        int count = (int) GameBoard.choice("How Much damage would you like to take up to 9",new Object[] {1,2,3,4,5,6,7,8,9});
        int damage = (int) (count * 12 * user.getMul());
        if (target.evadeCheck()) {
            target.changeHp(damage);
            GameBoard.sPrintln(damage + " damage dealt");
            user.changeHp(count);
            GameBoard.sPrintln("Baidam took " + count + " damage");
        } else {
            GameBoard.sPrintln("attack missed");
        }
    }, (user, team, enemies) -> {
        for (int i = 0; i < team.size(); i++) {
            team.get(i).addStatChange(new StatChange(new int[]{user.getDamage(), 0, 0}, 3));
        }
        GameBoard.sPrintln("Teams strength raised by " + user.getDamage());
    },}, 155, 60, 8), new Character("Orion", "Cards/Orion.png", new CharacterVoid[]{(user, team, enemies) -> {
        GameBoard.setChoices(new int[]{5, 6, 7});
        GameBoard.setCardsInDisplay(2);
        Character character = (Character) GameBoard.choice("Who's ability would you like to use",team.toArray());
        Card[] currentDisplay = GameBoard.getCardsInDisplay();
        Card[] targetDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, character};
        GameBoard.setCardsInDisplay(targetDisplay);
        GameBoard.setChoices(new int[]{7, 8});
        CharacterVoid attack = (CharacterVoid) GameBoard.choice("Which ability 1 or 2 would you like to use",character.getAbilities());
        attack.run(user, team, enemies);
    }, (user, team, enemies) -> {
        GameBoard.setChoices(new int[]{5, 6, 7});

        Character character = (Character) GameBoard.choice("Who's item would you like to move",team.toArray());
        character.displayItems();
        GameBoard.setChoices(new int[]{6, 7});
        Item item = (Item) GameBoard.choice("Which item?",character.getItems().toArray());
        user.addItem(item);
        character.removeItem(character.getItems().indexOf(item));
        GameBoard.setCardsInDisplay(2);
    },}, 125, 50, 15), new Character("Velorum", "Cards/velorum.png", new CharacterVoid[]{(user, team, enemies) -> {
        for (int i = 0; i < enemies.size(); i++) {
            int damage = (int) (40 * user.getMul());
            if (enemies.get(i).evadeCheck()) {
                GameBoard.sPrintln(enemies.get(i).getName() + " took " + damage + " damage");
                enemies.get(i).changeHp(damage);
            } else {
                GameBoard.sPrintln("Missed");
            }
        }
    }, (user, team, enemies) -> {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).evadeCheck()) {
                int damage = enemies.get(i).getDamage() * user.getStrength();
                GameBoard.sPrintln(enemies.get(i).getName() + " took " + damage + " damage");
                enemies.get(i).changeHp(damage);
            } else {
                GameBoard.sPrintln("Missed");
            }
        }
    },}, 60, 55, 55), new Character("Vela", "Cards/Vela.png", new CharacterVoid[]{(user, team, enemies) -> {
        Card[] display = new Card[]{GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD};
        ArrayList<Card> loot = deck.search(7, 3);
        GameBoard.setChoicesToTeam();
        for (int i = 0; i < loot.size() && i < 5; i++) {
            display[i] = loot.get(i);
        }
        for (int i = 0; i < team.size(); i++) {
            display[i + 5] = team.get(i);
        }
        GameBoard.setCardsInDisplay(display);
        for (int i = 0; i < loot.size(); i++) {
            Character character = (Character) GameBoard.choice("Which party member should get the " + loot.get(i).getName(),team.toArray());
            GameBoard.sPrintln(character.getName() + " got a " + loot.get(i).getName());
            character.addItem((Item) loot.get(i));
        }
    }, (user, team, enemies) -> {
        GameBoard.setChoicesToItems();
        Item item = (Item) GameBoard.choice("Which Item would you like to steal?",GameBoard.getCurrentLoot().toArray());
        GameBoard.getCurrentLoot().remove(item);
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getItems().contains(item)) {
                enemies.get(i).removeItem(item);
            }
        }
    },}, 50, 100, 30),


    };
}
