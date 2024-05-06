import java.util.ArrayList;

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
        GameBoard.sPrintln("This is a character. Characters can be both friend or foe");
        GameBoard.sPrintln("You can see the stats of the Character on the card Strength is the percent of damage they deal when attacking");
        GameBoard.sPrintln("They also have 2 abilities that they can use during their turn");
        GameBoard.sPrintln("Next up is items the text below explain how they work. Character They can hold up to 2 at a time. These can be used instead of abilities during a Character turn");
        GameBoard.sPrintln("Next we have bosses they are the same as characters however they are more powerful but with better stats and 3 abilities");
        GameBoard.sPrintln("When a boss is killed you will receive bonus rewards and they are discarded");
        GameBoard.sPrintln("Finally we have events cards. When these cards go into play they are triggered. They effect is on the text");
        //Game Setup
        GameBoard.sPrintln("Alright now that you understand the goal lets pick your team");
        GameBoard.setTeam();
        GameBoard.getDeck().shuffle();
        GameBoard.sPrintln("Good Luck I hope I see you later");
        ArrayList<Character> team = GameBoard.getTeam();
        GameBoard.gameLoop();





    }
    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }
}
