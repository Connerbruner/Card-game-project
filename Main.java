import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        new GameBoard();
        //explain the idea
        GameBoard.sPrintln("Welcome to Maddox Merc HQ");
        GameBoard.sPrintln("I contracted you to steal some important documents for us and take out some higher-ups");
        GameBoard.sPrintln("Dont worry about the money I will pay you handsomely");
        GameBoard.addScore(0);
        GameBoard.turnOnDisplay();

        //Explaining the game
        tutorial();

        //Game Setup
        GameBoard.sPrintln("Alright now that you understand the goal lets pick your team of 3");
        GameBoard.setTeam();
        GameBoard.sPrintln("Good Luck I hope I see you later");
        GameBoard.showHighScore();
        GameBoard.gameLoop();
        GameBoard.sPrintln("GAME OVER");
        GameBoard.sPrintln("Thank you for Playing");

        Main.main(null);
    }

    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }
    public static int random(Object[] arr) {
        return (int) (Math.random() * arr.length);
    }


    public static int randomExponential(int low, int high, double odds) {
        while (random(0, 100) > odds && low < high) {
            low++;
        }
        System.out.println(low);
        return low;
    }
    public static void tutorial() {
        GameBoard.setCardsInDisplay(new Card[] {
                Cards.DIFFERENT_CHARACTERS[0],
                GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,
                Cards.AVAILABLE_PARTY_MEMBERS[1],
                GameBoard.BLANK_CARD,GameBoard.BLANK_CARD
        });
        GameBoard.sPrintln("Lets Learn to play");
        GameBoard.sPrintln("First off lets learn to make a decisions");
        GameBoard.sPrintln("Press the 3 or 4 keys 2 times to select one of the abilities ");
        GameBoard.setCardsInDisplay(new Card[] {
                Cards.DIFFERENT_CHARACTERS[0],
                GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,
                GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,
                Cards.AVAILABLE_PARTY_MEMBERS[1]
        });
        GameBoard.setChoices(new int[] {5,6,7,8});
        while ((int)GameBoard.choice("What would you like to use? ", new Object[]{0,1,2,3},"3 = Ability 1    4 = Ability 2")<2);
        GameBoard.sPrintln("Now lets add some items and use some");
        GameBoard.setCardsInDisplay(new Card[] {
                Cards.DIFFERENT_CHARACTERS[0],
                GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,GameBoard.BLANK_CARD,
                Cards.DIFFERENT_ITEMS[0],GameBoard.BLANK_CARD,
                Cards.AVAILABLE_PARTY_MEMBERS[1]
        });
        while (!((int)GameBoard.choice("What would you like to use? ", new Object[]{0,1,2,3},"1 = Laser Sword   3 = Ability 1    4 = Ability 2") ==0));
        GameBoard.setChoices(new int[] {0});
        GameBoard.choice("Who would you like to attack",new Object[]{1},"1 = Prototype");




    }
}