
public class Main {
    static Deck deck = new Deck(
            new Card[] {new Card("",false)}
    );

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(deck);
        //explain the idea
        gameBoard.sPrintln("Welcome to the Dark Forest");
        gameBoard.sPrintln("Your goal is to rid this forest of dangerous monsters with your group of 3");
        gameBoard.sPrintln("");
        gameBoard.addScore(0);

        //Explaining the game
        gameBoard.sPrintln("Before we Get started Let me explain the basics");
        gameBoard.sPrintln("This is a character. Characters can be both friend or foe");
        gameBoard.sPrintln("You can see the stats of the Character on the card Strength is the percent of damage they deal when attacking");
        gameBoard.sPrintln("They also have 2 abilities that they can use during their turn");
        gameBoard.sPrintln("Next up is items the text below explain how they work. Character They can hold up to 2 at a time. These can be used instead of abilities during a Character turn");
        gameBoard.sPrintln("Next we have bosses they are the same as characters however they are more powerful but with better stats and 3 abilities");
        gameBoard.sPrintln("When a boss is killed you will receive bonus rewards and they are discarded");
        gameBoard.sPrintln("Finally we have events cards. When these cards go into play they are triggered. They effect is on the text");
        //Game Setup
        gameBoard.sPrintln("Lets Pick Your Team Now");
        gameBoard.setTeam();
        gameBoard.sPrintln("The Party Members you did not chose you will have to face");
        gameBoard.addRemainingPartyMembers();
        gameBoard.getDeck().shuffle();



    }
}
