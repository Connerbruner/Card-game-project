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
            new Item("Laser Bow", "Cards/laserBow.png", 3, 10, 10, false),
            new Item("Cursed Cube", "Cards/cursedCube.png", -100, 100, false),
            new Item("Potion","Cards/potionB.png",-30,30),
            new Item("Potion","Cards/potionP.png",0,10),
            new Item("Potion","Cards/potion.png",-20,20),
    };
    public static final Character[] DIFFERENT_CHARACTERS = new Character[]{
            new Character("Prototype", "Cards/prototype.png", new CharacterVoid[]{(user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{-10, 0, 5}, 3));
                GameBoard.sPrintln("Prototype Gained 5 Agility but lost 10 Strength");
            }, (user, team, enemies) -> {
                int index = Main.random(0, enemies.size() - 1);
                if (enemies.get(index).evadeCheck(user)) {
                    GameBoard.sPrintln(enemies.get(index).getName() + " takes " + (int)(user.getAgility() * user.getStrength()));
                    enemies.get(index).changeHp((int) (user.getAgility() * user.getStrength()));
                }
            },}, 0.6, 40, 60),
            new Character("Nerdy Nerd", "Cards/nerdy.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (Character enemy : enemies) {
                            enemy.addStatChange(new StatChange(new int[]{0, 0, -20}, 3));
                        }
                        GameBoard.sPrintln("Everyone on your team loses 20 agility");
                    },
                    (user, team, enemies) -> {
                        for (Character enemy : enemies) {
                            enemy.addStatChange(new StatChange(new int[]{-20, 0, 0}, 3));
                        }
                        GameBoard.sPrintln("Everyone on your team loses 20 strength");
                    }}, 0.1, 190, 0),
            new Character("Copilot X", "Cards/copilotX.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (Character enemy : enemies) {
                            if (enemy.evadeCheck(user)) {
                                enemy.changeHp((int) (30 * user.getStrength()));
                                GameBoard.sPrintln(enemy.getName() + " loses " + (int) (30 * user.getStrength()) + " HP");
                            }
                        }
                        for (Character character : team) {
                            if (character.evadeCheck(user)) {
                                character.changeHp((int) (30 * user.getStrength()));
                                GameBoard.sPrintln(character.getName() + " loses " + (int) (30 * user.getStrength()) + " HP");

                            }
                        }

                    },
                    (user, team, enemies) -> {
                        for (Character enemy : enemies) {
                            enemy.changeHp(-30);
                        }
                        for (Character character : team) {
                            character.changeHp(-30);
                        }
                        GameBoard.sPrintln("Everyone heals 30 HP");
                    }}, 1, 85, 15),
            new Character("KHEPRI","Cards/khepri.png",new CharacterVoid[]{
                    (user, team, enemies) -> {
                        int index = Main.random(0, enemies.size() - 1);
                        if (enemies.get(index).evadeCheck(user)) {
                            GameBoard.sPrintln(enemies.get(index).getName() + " takes " + (int) (30 * user.getStrength())+" damage");
                            enemies.get(index).changeHp((int) (30 * user.getStrength()));
                            user.addStatChange(new StatChange(new int[]{0, 0, 5},3));
                            GameBoard.sPrintln("KHEPRI gains 5 agility");
                        } else {
                            GameBoard.sPrintln("Missed");
                        }

                    },
                    (user, team, enemies) -> {
                        int index = Main.random(0, enemies.size() - 1);
                        if (enemies.get(index).evadeCheck(user)) {
                            GameBoard.sPrintln(enemies.get(index).getName() + " takes " + 30 * user.getStrength());
                            enemies.get(index).changeHp((int) (30 * user.getStrength()));
                            user.addStatChange(new StatChange(new int[]{5, 0, 0},3));
                            GameBoard.sPrintln("KHEPRI gains 5 strength");
                        } else {
                            GameBoard.sPrintln("Missed");
                        }

                    }
            },0.9,70,30),
            new Character("Lazer dogo","Cards/lazer dogo.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (int i=0; i<2; i++) {
                            int index = Main.random(0, enemies.size() - 1);
                            if (enemies.get(index).evadeCheck(user)) {
                                GameBoard.sPrintln(enemies.get(index).getName() + " takes " +(int) (30 * user.getStrength())+" damage");
                                enemies.get(index).changeHp((int) (30 * user.getStrength()));
                            }else {
                                GameBoard.sPrintln("Missed");
                            }
                        }



                    },
                    (user, team, enemies) -> {
                        int index = Main.random(0, enemies.size() - 1);
                        if (enemies.get(index).evadeCheck(user)) {
                            GameBoard.sPrintln(enemies.get(index).getName() + " takes " + 50 * user.getStrength());
                            enemies.get(index).changeHp((int) (50 * user.getStrength()));
                        }else {
                            GameBoard.sPrintln("Missed");
                        }
                    }
            },1,85,15)
    };
    public static final Deck deck = new Deck(new Card[]{
            new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]), new Item(DIFFERENT_ITEMS[0]),
            GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, GameBoard.BLANK_CARD,
            new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]), new Character(DIFFERENT_CHARACTERS[0]),
            new Document(DIFFERENT_DOCUMENTS[0]), new Document(DIFFERENT_DOCUMENTS[0]),
            new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]), new Document(DIFFERENT_DOCUMENTS[1]),
            new Document(DIFFERENT_DOCUMENTS[2]),
            new Document(DIFFERENT_DOCUMENTS[3]), new Document(DIFFERENT_DOCUMENTS[3]), new Document(DIFFERENT_DOCUMENTS[3]),
            new Item(DIFFERENT_ITEMS[1]), new Item(DIFFERENT_ITEMS[1]),
            new Item(DIFFERENT_ITEMS[2]), new Item(DIFFERENT_ITEMS[2]), new Item(DIFFERENT_ITEMS[2]),
            new Item(DIFFERENT_ITEMS[3]), new Item(DIFFERENT_ITEMS[3]),
            new Character(DIFFERENT_CHARACTERS[1]), new Character(DIFFERENT_CHARACTERS[1]), new Character(DIFFERENT_CHARACTERS[1]), new Character(DIFFERENT_CHARACTERS[1]),
            new Character(DIFFERENT_CHARACTERS[2]), new Character(DIFFERENT_CHARACTERS[2]), new Character(DIFFERENT_CHARACTERS[2]), new Character(DIFFERENT_CHARACTERS[2]),
            new Character(DIFFERENT_CHARACTERS[3]),new Character(DIFFERENT_CHARACTERS[3]),new Character(DIFFERENT_CHARACTERS[3]),new Character(DIFFERENT_CHARACTERS[3]),
            new Character(DIFFERENT_CHARACTERS[4]),new Character(DIFFERENT_CHARACTERS[4]),new Character(DIFFERENT_CHARACTERS[4]),new Character(DIFFERENT_CHARACTERS[4]),
            new Item(DIFFERENT_ITEMS[4]),new Item(DIFFERENT_ITEMS[4]),new Item(DIFFERENT_ITEMS[4]),
            new Item(DIFFERENT_ITEMS[5]),new Item(DIFFERENT_ITEMS[5]),new Item(DIFFERENT_ITEMS[5]),
            new Item(DIFFERENT_ITEMS[6]),new Item(DIFFERENT_ITEMS[6]),new Item(DIFFERENT_ITEMS[6]),

    });

    public static final Character[] AvailablePartyMembers = new Character[]{
            new Character("Mir", "Cards/mir.png", true, new CharacterVoid[]{(user, team, enemies) -> {
                for (Character character : team) {
                    character.changeHp(-30);
                    GameBoard.sPrintln(character.getName() + " healed 30 damage");
                }
            }, (user, team, enemies) -> {
                GameBoard.setChoices(new int[]{5, 6, 7});
                GameBoard.setCardsInDisplay(2);
                Character character = (Character) GameBoard.choice("Which Teammate would you like to heal", team.toArray());
                character.changeHp(-70);
                GameBoard.sPrintln(character.getName() + " healed 70 Damage");

            },}, 0.9, 125, 0),
            new Character("Leo", "Cards/Leo.png", new CharacterVoid[]{(user, team, enemies) -> {
                for (Character enemy : enemies) {
                    enemy.addStatChange(new StatChange(new int[]{0, 0, -100}, 3));
                }
                GameBoard.sPrintln("Enemies no longer can evade");
            }, (user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                if (target.getAgility() > 0) {
                    GameBoard.sPrintln("Missed");
                } else {
                    target.changeHp((int) (70 * user.getStrength()));
                    GameBoard.sPrintln("dealt " + (int) (70 * user.getStrength()) + " damage");
                }

            },}, 1, 50, 35),
            new Character("Arrokoth", "Cards/Arrokoth.png", new CharacterVoid[]{(user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{-50, 20, 15}, 3));
                GameBoard.sPrintln("Stats changed to 50,80,45");

            }, (user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{-5, 60, -30}, 3));
                GameBoard.sPrintln("Stats changed to 95,120,0");


            },}, 1, 60, 30), new Character("Gliese", "Cards/Gliese.png", new CharacterVoid[]{(user, team, enemies) -> {
        GameBoard.setChoicesToEnemies();
        Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
        target.changeHp(30);
        GameBoard.sPrintln("damage dealt 30");

    }, (user, team, enemies) -> {
        for (Character enemy : enemies) {
            enemy.addStatChange(new StatChange(new int[]{-10, -10, -10}, 3));
        }
        GameBoard.sPrintln("Enemies stats lowered 10");
    },}, 1.05, 70, 20),
            new Character("Baidam", "Cards/baidam card.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                GameBoard.setChoices(new int[0]);
                int count = (int) GameBoard.choice("How Much damage would you like to take up to 9", new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
                int damage = (int) (count * 5 * user.getStrength());
                if (target.evadeCheck(user)) {
                    target.changeHp(damage);
                    GameBoard.sPrintln(damage + " damage dealt");
                    user.changeHp(count);
                    GameBoard.sPrintln("you took " + count + " damage");
                } else {
                    GameBoard.sPrintln("attack missed");
                }
            }, (user, team, enemies) -> {
                for (Character character : team) {
                    character.addStatChange(new StatChange(new int[]{user.getDamage() / 2, 0, 0}, 3));
                }
                GameBoard.sPrintln("Teams strength raised by " + user.getDamage());
            },}, 1.55, 60, 8),
            new Character("Orion", "Cards/Orion.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setChoices(new int[]{5, 6, 7});
                GameBoard.setCardsInDisplay(2);
                Character character = (Character) GameBoard.choice("Who's ability would you like to use", team.toArray());
                Card[] currentDisplay = GameBoard.getCardsInDisplay();
                Card[] targetDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, character};
                GameBoard.setCardsInDisplay(targetDisplay);
                GameBoard.setChoices(new int[]{7, 8});
                CharacterVoid attack = (CharacterVoid) GameBoard.choice("Which ability 1 or 2 would you like to use", character.getAbilities());
                attack.run(user, team, enemies);
            }, (user, team, enemies) -> {
                for (int i = 0; i < team.size(); i++) {
                    ArrayList<StatChange> stats = team.get(i).getStatChanges();
                    for (int j = 0; j < stats.size(); j++) {
                        stats.get(i).setTime(stats.get(i).getTime() + 3);
                    }
                }
                GameBoard.sPrintln("Your Teams Stat Changes will last 3 turns longer");

            },}, 1.25, 65, 10),
            new Character("Velorum", "Cards/velorum.png", new CharacterVoid[]{(user, team, enemies) -> {
                for (Character enemy : enemies) {
                    if (enemy.evadeCheck(user)) {
                        GameBoard.sPrintln(enemy.getName() + " took 20 damage");
                        enemy.changeHp(20);
                    } else {
                        GameBoard.sPrintln("Missed");
                    }
                }
            }, (user, team, enemies) -> {
                for (Character enemy : enemies) {
                    if (enemy.evadeCheck(user)) {
                        GameBoard.sPrintln(enemy.getName() + " took " + enemy.getDamage() + " damage");
                        enemy.changeHp(enemy.getDamage());
                    } else {
                        GameBoard.sPrintln("Missed");
                    }
                }
            },}, 0.6, 55, 55),
            new Character("Vela", "Cards/Vela.png", new CharacterVoid[]{(user, team, enemies) -> {
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
                for (Card card : loot) {
                    Character character = (Character) GameBoard.choice("Which party member should get the " + card.getName(), team.toArray());
                    GameBoard.sPrintln(character.getName() + " got a " + card.getName());
                    character.addItem((Item) card);
                }
            }, (user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                int count = 0;
                for (Character character : team) {
                    count += character.getItems().size();
                }
                int damage = (int) (10 * user.getStrength() * count);
                if (target.evadeCheck(user)) {
                    GameBoard.sPrintln(target.getName() + " took " + damage + " damage");
                    target.changeHp(damage);
                } else {
                    GameBoard.sPrintln("Missed");
                }
            },}, 0.5, 100, 30),


    };
}
