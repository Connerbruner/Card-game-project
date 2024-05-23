import java.util.ArrayList;

public class Cards {

    public static final Character[] MADDOX_BOSSES = new Character[]{
            new Character("Maddox", "Cards/maddox.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        GameBoard.sPrintln("YOU ARE POINTLESS");
                        for (Character enemy : enemies) {
                            enemy.addStatChange(new StatChange(new int[]{-10, -10, -10}, 5));
                        }
                        GameBoard.sPrintln("Everyone's stats are lowered by 10 for 5 turns");
                        user.getAbilities()[user.getAbilities().length - 1].run(user, team, enemies);

                    },
                    (user, team, enemies) -> {
                        GameBoard.sPrintln("THIS IRON FIST SHOULD HURT");
                        Character.basicAttack(50, enemies.get(Main.random(enemies.toArray())), user);
                        user.getAbilities()[user.getAbilities().length - 1].run(user, team, enemies);
                    },
                    (user, team, enemies) -> {
                        GameBoard.sPrintln("MY POWER IS LIMITLESS");
                        user.addStatChange(new StatChange(new int[]{20, 20, 20}, 5));
                        GameBoard.sPrintln("Maddox's stats are increased by 20 for 5 turns");
                        user.getAbilities()[user.getAbilities().length - 1].run(user, team, enemies);

                    },
                    (user, team, enemies) -> {
                        GameBoard.sPrintln("WANT SOME MISSILES?");
                        for (int i = 0; i < Main.random(0, 10); i++) {
                            Character.basicAttack(5, enemies.get(Main.random(enemies.toArray())), user);

                        }
                    },
                    ((user, team, enemies) -> {
                        if (Main.random(0, 2) > 0) {
                            user.getAbilities()[Main.random(user.getAbilities())].run(user, team, enemies);
                        } else {
                            GameBoard.sPrintln("Maddox Stares at you");
                        }

                    })

            }, 1, 500, 10)
    };

    public static final Item[] DIFFERENT_ITEMS = new Item[]{

            new Item("Laser Sword", "Cards/laserSword.png", 20, 25, false),
            new Item("Laser Bow", "Cards/laserBow.png", 3, 10, 10, false),
            new Item("Laser Axe", "Cards/laser axe.png", 5, 40, false),
            new Item("Laser Fists", "Cards/Laser fists.png", 18, 28, false),
            new Item("Laser Pistol", "Cards/Laser pistol.png", 7, 4, 4, false),
            new Item("Laser Pistols", "Cards/Laser pistols.png", 14, 2, 2, false),
            new Item("Laser Spear", "Cards/laser spear.png", 23, 23, false),
            new Item("Laser Rifle", "Cards/laser rifle.png", 5, 6, 6, false),
            new Item("Laser Trident", "Cards/Laser trident.png", 0, 50, false),

            new Item("Plasma Trident", "Cards/plasma trident.png", 0, 70, false),
            new Item("Plasma Axe", "Cards/Plasma axe.png", 10, 50, false),
            new Item("Plasma Rifle", "Cards/plasma rifle.png", 6, 10, 10, false),
            new Item("Plasma Sword", "Cards/plasmaSword.png", 30, 35, false),

            new Item("Cursed Cube", "Cards/cursedCube.png", -100, 100, false),
            new Item("Potion", "Cards/potionB.png", -30, 30),
            new Item("Potion", "Cards/potionP.png", 0, 10),
            new Item("Potion", "Cards/potion.png", -20, 20),
            new Item("lunch", "Cards/lunch.png", 10, 10),
            new Item("candy", "Cards/candy.png", 5, 5),
            new Item("Shield", "Cards/sheild.png", (user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{0, 30, 0}, 3));
                GameBoard.sPrintln("Defense raised by 30 for 3 turns");
            }, true),
            new Item("Rally Flag", "Cards/rally flag.png", (user, team, enemies) -> {
                for (Character character : team) {
                    character.addStatChange(new StatChange(new int[]{10, 10, 10}, 3));
                }
                GameBoard.sPrintln("All stats raised by 10 for the whole team for 3 turns");
            }, false),
            new Item("First aid kit", "Cards/first aid kit.png", (user, team, enemies) -> {
                GameBoard.setChoicesToTeam();
                GameBoard.setCardsInDisplay(2);
                Character teamMate = (Character) GameBoard.choice("Who would you like to heal", team.toArray());
                teamMate.changeHp(-20);
                GameBoard.sPrintln(teamMate.getName() + " healed 20 damage");
            }, false),
            new Item("Insta Smoke", "Cards/instaSmoke.png", (user, team, enemies) -> {
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).addStatChange(new StatChange(new int[] {0,0,-50},3));
                }
                for (int i = 0; i < team.size(); i++) {
                    team.get(i).addStatChange(new StatChange(new int[] {0,0,-50},3));

                }
                GameBoard.sPrintln("Everyone loses 50% dodge chance");
            }, true),
            new Item("Lock on Glasses", "Cards/LockOnGlasses.png", (user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                GameBoard.setCardsInDisplay(2);
                Character target = (Character) GameBoard.choice("who would you like to lock on to", team.toArray());
                target.addStatChange(new StatChange(new int[] {0,0,-100},1));
            }, false),


    };


    public static final Character[] DIFFERENT_CHARACTERS = new Character[]{
            new Character("Prototype", "Cards/prototype.png", new CharacterVoid[]{(user, team, enemies) -> {
                user.addStatChange(new StatChange(new int[]{-10, 0, 5}, 3));
                GameBoard.sPrintln("Prototype Gained 5 Agility but lost 10 Strength");
            }, (user, team, enemies) -> Character.basicAttack(user.getAgility(), enemies.get(Main.random(enemies.toArray())), user)
            }, 0.6, 40, 60),
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
                    }}, 0.5, 150, 0),
            new Character("Copilot X", "Cards/copilotX.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (Character enemy : enemies) {
                            Character.basicAttack(30, enemy, user);
                        }
                        for (Character character : team) {
                            Character.basicAttack(30, character, user);
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
            new Character("KHEPRI", "Cards/khepri.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        if (Character.basicAttack(30, enemies.get(Main.random(enemies.toArray())), user)) {
                            user.addStatChange(new StatChange(new int[]{0, 0, 5}, 3));
                            GameBoard.sPrintln("KHEPRI gains 5 agility");
                        }

                    },
                    (user, team, enemies) -> {
                        if (Character.basicAttack(30, enemies.get(Main.random(enemies.toArray())), user)) {
                            user.addStatChange(new StatChange(new int[]{5, 0, 0}, 3));
                            GameBoard.sPrintln("KHEPRI gains 5 strength");
                        }

                    }
            }, 0.9, 70, 30),
            new Character("Lazer dogo", "Cards/lazer dogo.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        for (int i = 0; i < 2; i++) {
                            Character.basicAttack(30, enemies.get(Main.random(enemies.toArray())), user);
                        }


                    },
                    (user, team, enemies) -> Character.basicAttack(40, enemies.get(Main.random(enemies.toArray())), user)
            }, 1, 85, 15),
            new Character("Robot", "Cards/robot.png", new CharacterVoid[]{
                    (user, team, enemies) -> Character.basicAttack(50, enemies.get(Main.random(enemies.toArray())), user),
                    (user, team, enemies) -> GameBoard.sPrintln("the robot just looks you")
            }, 1, 125, 0),
            new Character("phone", "Cards/phone.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        Deck.BASE_DECK.remove(0);
                        GameBoard.sPrintln("Removed the top card of the deck");

                    },
                    (user, team, enemies) -> Character.basicAttack(team.size() * 10, enemies.get(Main.random(enemies.toArray())), user)
            }, 0.7, 75, 35),
            new Character("Flame dogo", "Cards/flame dogo.png", new CharacterVoid[]{
                    (user, team, enemies) -> {

                        for (int i = 0; i < 2; i++) {
                            for (Character enemy : enemies) {
                                Character.basicAttack(7, enemy, user);

                            }
                        }


                    },
                    (user, team, enemies) -> Character.basicAttack(50, enemies.get(Main.random(enemies.toArray())), user),

            }, 1.15, 85, 0),
            new Character("Drone", "Cards/drone.png", new CharacterVoid[]{
                    (user, team, enemies) -> {
                        if (!Character.basicAttack(30, enemies.get(Main.random(enemies.toArray())), user)) {
                            GameBoard.sPrintln(user.getName() + " took 5 damage");
                            user.changeHp(5);
                        }
                    },
                    (user, team, enemies) -> Character.basicAttack(user.getDamage(), enemies.get(Main.random(enemies.toArray())), user)
            }, 0.7, 10, 50)

    };


    public static final Character[] AVAILABLE_PARTY_MEMBERS = new Character[]{

            new Character("Mir", "Cards/mir.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setCardsInDisplay(2);
                GameBoard.setChoices(new ArrayList<>());
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

            },}, 0.9, 125, 0, new String[]{"Heal 30 damage from all Teammates ", "Heal 70 damage from 1 Teammate"}),

            new Character("Leo", "Cards/Leo.png", new CharacterVoid[]{(user, team, enemies) -> {
                for (Character enemy : enemies) {
                    enemy.addStatChange(new StatChange(new int[]{0, 0, -30}, 2));
                }
                GameBoard.sPrintln("Enemies lost 30 agility");
            }, (user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                if (target.getAgility() > 0) {
                    GameBoard.sPrintln("Missed");
                } else {
                    target.changeHp((int) (70 * user.getStrength()));
                    GameBoard.sPrintln("dealt " + (int) (70 * user.getStrength()) + " damage");
                }

            },}, 1, 50, 35, new String[]{"-30 Dodge Chance for enemies for 2 turns", "70 damage if Dodge Chance is more than 0 this fails"}),

            new Character("Arrokoth", "Cards/Arrokoth.png", new CharacterVoid[]{(user, team, enemies) -> {
                user.getStatChanges().clear();
                user.addStatChange(new StatChange(new int[]{-50, 20, 15}, 3));
                GameBoard.sPrintln("Stats changed to 50,80,45");

            }, (user, team, enemies) -> {
                user.getStatChanges().clear();
                user.addStatChange(new StatChange(new int[]{-5, 60, -30}, 3));
                GameBoard.sPrintln("Stats changed to 95,120,0");


            },}, 1, 60, 30, new String[]{"Change stats to 50,80,45 for 3 turns", "Change stats to 95,120,0 for 3 turns"}),

            new Character("Gliese", "Cards/Gliese.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                target.changeHp(30);
                GameBoard.sPrintln("damage dealt 30");

            }, (user, team, enemies) -> {
                for (Character enemy : enemies) {
                    enemy.addStatChange(new StatChange(new int[]{-10, -10, -10}, 3));
                }
                GameBoard.sPrintln("Enemies stats lowered 10");
            },}, 1.05, 70, 20, new String[]{"30 damage this is not affected by any effects on any cards", "reduce all enemies stats by 10 for 3 turns"}),

            new Character("Baidam", "Cards/baidam card.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setChoicesToEnemies();
                Character target = (Character) GameBoard.choice("Who would you like to attack? ", enemies.toArray());
                GameBoard.setChoices(new int[0]);
                int count = (int) GameBoard.choice("How Much damage would you like to take up to 9", new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, false, "choice x 5 = damage");
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
                GameBoard.sPrintln("Teams strength raised by " + user.getDamage() / 2);
            },}, 1.55, 60, 8, new String[]{"Deal 5 damage for each you take (up to 9)", "Raise your team damage% for each (damage counter)/2 on this for 3 turns"}),

            new Character("Orion", "Cards/Orion.png", new CharacterVoid[]{(user, team, enemies) -> {
                GameBoard.setChoices(new int[]{5, 6, 7});
                GameBoard.setCardsInDisplay(2);
                Character character = (Character) GameBoard.choice("Who's ability would you like to use", team.toArray());
                Card[] currentDisplay = GameBoard.getCardsInDisplay();
                Card[] targetDisplay = new Card[]{currentDisplay[0], currentDisplay[1], currentDisplay[2], currentDisplay[3], currentDisplay[4], GameBoard.BLANK_CARD, GameBoard.BLANK_CARD, character};
                GameBoard.setCardsInDisplay(targetDisplay);
                GameBoard.setChoices(new int[]{7, 8});
                String[] strings = character.getAbilityStrings();
                CharacterVoid attack = (CharacterVoid) GameBoard.choice("Which ability 1 or 2 would you like to use", character.getAbilities(), "1 = " + strings[0] + "    2 = " + strings[1]);
                attack.run(user, team, enemies);
            }, (user, team, enemies) -> {
                for (Character character : team) {
                    ArrayList<StatChange> stats = character.getStatChanges();
                    for (StatChange stat : stats) {
                        stat.setTime(stat.getTime() + 3);
                    }
                }
                GameBoard.sPrintln("Your Teams Stat Changes will last 3 turns longer");

            },}, 1.25, 65, 10, new String[]{"Use one of your teammates abilities", "Extend all of the teams buffs for 3 turns"}),

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
            },}, 0.6, 55, 55, new String[]{"20 damage to all enemies", "double all damage on enemies"}),

            new Character("Vela", "Cards/Vela.png", new CharacterVoid[]{(user, team, enemies) -> {
                ArrayList<Card> loot = Deck.BASE_DECK.search(7, 3);
                Deck.BASE_DECK.removeRange(0,7);
                while (loot.size()>5) {
                    loot.remove(0);
                }
                new Chest("Cards/chest.png", loot.toArray(Item[]::new)).trigger();


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
            },}, 0.5, 100, 30, new String[]{"Grab all items from the top 7 cards", "10 damage for each item on your team"}),


    };
    public static final Document[] DIFFERENT_DOCUMENTS = new Document[]{
            new Document("F35 cad", "Cards/F35 cad.png", 3000),
            new Document("Password sticker", "Cards/passwordSticker.png", 100),
            new Document("Woke agenda", "Cards/woke agenda.png", 1),
            new Document("Joe Biden's shopping list", "Cards/shopping list.png", 1000)
    };
    public static final Event[] DIFFERENT_EVENTS = new Event[]{
//            new Chest("Cards/chest.png", 0, DIFFERENT_ITEMS.length, 3),
//            new Chest("Cards/chest rare.png", 0, DIFFERENT_ITEMS.length - 4, 3),
//            new Chest("Cards/chest legendary.png", 8, DIFFERENT_ITEMS.length, 5),
//            new Chest("Cards/chest power.png", 0, DIFFERENT_ITEMS.length - 4, 4),
//            new Chest("Cards/power chest rare.png", 8, DIFFERENT_ITEMS.length - 4, 4),
//            new Chest("Cards/fridge.png",new Item[] {DIFFERENT_ITEMS[16],DIFFERENT_ITEMS[17]}),
//            new Chest("Cards/fridge chest.png",new Item[] {DIFFERENT_ITEMS[16],DIFFERENT_ITEMS[17],DIFFERENT_ITEMS[17],DIFFERENT_ITEMS[16]}),
            new Event("ALARM", "Cards/alarm.png", () -> {
                Deck.BASE_DECK.removeRange(0, 20, 2);
                GameBoard.sPrintln("all items and events removed from the top 20 cards of the deck");

            }),
            new Event("wrong way", "Cards/wrong way.png", () -> {
                GameBoard.sPrintln("You went the wrong way you have to go down a floor");
                if (GameBoard.getCurrentFloor() > 0) {
                    GameBoard.setFloor(GameBoard.getCurrentFloor() - 1);

                }
            }),
            new Event("JACKPOT", "Cards/JACKPOT.png", () -> {
                new Chest("Cards/chest.png", Deck.BASE_DECK.search(5, 3).toArray(Item[]::new)).trigger();
                Deck.BASE_DECK.removeRange(0,5);
            }),
            new Event("Rally", "Cards/rally.png", () -> {
                GameBoard.sPrintln("The enemies rally up");
                GameBoard.sPrintln("All stats of enemies by raised 10");
                GameBoard.addToPermanentStatChange(new int[]{10, 10, 10});
            }),

    };

}
