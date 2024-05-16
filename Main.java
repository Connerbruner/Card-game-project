public class Main {


    public static void main(String[] args) {

        new GameBoard();
        //explain the idea
        GameBoard.sPrintln("Welcome to Maddox Merc HQ");
        GameBoard.sPrintln("I contracted you to steal some important documents for us and take out some higher-ups");
        GameBoard.sPrintln("Dont worry about the money I will pay you handsomely");
        GameBoard.sPrintln("The Goal is to get me all the goods you can get without dying");
        GameBoard.addScore(0);

        //Explaining the game
        GameBoard.sPrintln("Before we Get started Let me explain the how we plan to handle this");
        GameBoard.setBackground("ui_images/character.png");
        GameBoard.sPrintln("This is a character. Characters can be both friend or foe");
        GameBoard.setBackground("ui_images/Cards Explained0000.png");
        GameBoard.sPrintln("You can see the stats of the Character on the card ");
        GameBoard.sPrintln("Strength: is the percent of damage they deal when attacking");
        GameBoard.sPrintln("Defense: is the amount of damage that you can take ");
        GameBoard.sPrintln("Agility: is the percent chance to dodge");
        GameBoard.setBackground("ui_images/Cards Explained0001.png");
        GameBoard.sPrintln("They also have 2 abilities that they can use during their turn");
        GameBoard.setBackground("ui_images/Cards Explained0002.png");
        GameBoard.sPrintln("If any temporary stat changes are made they show up hear the format is: (Strength),(Defense),(Agility) for (Average Time Left)");
        GameBoard.setBackground("ui_images/Cards Explained0003.png");
        GameBoard.sPrintln("This is how much damage you have taken");
        GameBoard.setBackground("ui_images/items.png");
        GameBoard.sPrintln("Next up is items the text below explain how they work.");
        GameBoard.sPrintln("Character They can hold up to 2 at a time. These can be used instead of abilities during a Character turn");
        GameBoard.sPrintln("The effect of a item is listed below");
        GameBoard.setBackground("ui_images/events.png");
        GameBoard.sPrintln("Events trigger when entering play the effect is listed on the text");
        GameBoard.setBackground("ui_images/base.png");
        GameBoard.sPrintln("The round starts by drawing 5 cards");
        GameBoard.setBackground("ui_images/turnflowEvents.png");
        GameBoard.sPrintln("First events are triggers");
        GameBoard.setBackground("ui_images/turnflowCharacthers.png");
        GameBoard.sPrintln("Then you fight any characters/enemies.");
        GameBoard.sPrintln("Your party attacks first from left to right. Then the characters/enemies go left to right");
        GameBoard.setBackground("ui_images/turnflowItems.png");
        GameBoard.sPrintln("Then you add any items that remain");
        GameBoard.setBackground("ui_images/chosing explained.png");
        GameBoard.sPrintln("If you want to choose anything you press that number on the keyboard 2 times in a row");





        //Game Setup
        GameBoard.setupDisplay();
        GameBoard.sPrintln("Alright now that you understand the goal lets pick your team");
        GameBoard.setTeam();
        GameBoard.getDeck().shuffle();
        GameBoard.sPrintln("Good Luck I hope I see you later");

        GameBoard.gameLoop();
        GameBoard.sPrintln("GAME OVER");
        GameBoard.sPrintln("Thank you for Playing");


    }

    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }

    public static int randomExponential(int low, int high, double odds) {
        while (random(0, 100) > odds && low < high) {
            low++;
        }
        System.out.println(low);
        return low;
    }
}
