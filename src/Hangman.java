

/**
 * A command line Hangman game
 */
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Hangman {
    public static void main (String [] args) throws Exception {
        // Get word from word bank using random number generator
        File file = new File("movies.txt");
        String word = new WordBank(file).newWord();
        if (word == null) {
            // If the Word Bank returns null as the word, try again.
            word = new WordBank(file).newWord();
        }

        Gameplay game = new Gameplay(word);

        System.out.println("db   d8b   db d88888b db       .o88b.  .d88b.  .88b  d88. d88888b      d888888b  .d88b.       db   db  .d8b.  d8b   db  d888b  .88b  d88.  .d8b.  d8b   db \n" +
                "88   I8I   88 88'     88      d8P  Y8 .8P  Y8. 88'YbdP`88 88'          `~~88~~' .8P  Y8.      88   88 d8' `8b 888o  88 88' Y8b 88'YbdP`88 d8' `8b 888o  88 \n" +
                "88   I8I   88 88ooooo 88      8P      88    88 88  88  88 88ooooo         88    88    88      88ooo88 88ooo88 88V8o 88 88      88  88  88 88ooo88 88V8o 88 \n" +
                "Y8   I8I   88 88~~~~~ 88      8b      88    88 88  88  88 88~~~~~         88    88    88      88~~~88 88~~~88 88 V8o88 88  ooo 88  88  88 88~~~88 88 V8o88 \n" +
                "`8b d8'8b d8' 88.     88booo. Y8b  d8 `8b  d8' 88  88  88 88.             88    `8b  d8'      88   88 88   88 88  V888 88. ~8~ 88  88  88 88   88 88  V888 \n" +
                " `8b8' `8d8'  Y88888P Y88888P  `Y88P'  `Y88P'  YP  YP  YP Y88888P         YP     `Y88P'       YP   YP YP   YP VP   V8P  Y888P  YP  YP  YP YP   YP VP   V8P");
        System.out.println("ASCII Art by http://patorjk.com");
        TimeUnit.SECONDS.sleep(3);
        while (!word.equals(game.getMaskedWord()) && game.wrongGuesses < 6) { // Max 6 guesses
            game.displayWord();
            game.guessLetter();
        }
        if (word.equals(game.getMaskedWord())) {
            System.out.printf("You won! You guessed '%s'. Great job!\n", word);
        } else if (game.wrongGuesses >= 6){
            game.displayWord();
            System.out.print("You guessed wrong too many times. You are hanged!\n");
            game.gallows();

            System.out.printf("The word was %s. Good luck next time.\n", word);
        }
    }
}
