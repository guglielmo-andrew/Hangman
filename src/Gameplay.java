import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class Gameplay {
    //Fields
    private String word;
    private char [] maskedWord;
    public int wrongGuesses;

    //Constructor
    Gameplay(String word) {
        this.word = word;
        this.maskedWord = maskWord(this.word);
    }
    //Methods
    char [] maskWord(String w){
        char [] maskedWordArray = w.toCharArray();
        for (int i=0; i<w.length(); i++) {
            if (Character.isLetter(maskedWordArray[i])) {
                maskedWordArray[i] = '_';
            }
        }
        return maskedWordArray;
    }

    public void guessLetter() {
        // Prompt user for input
        Scanner guesser = new Scanner(System.in);
        System.out.println("Guess a letter: ");
        char c = (char) guesser.next().charAt(0);
        int maxIndex = word.length();
        if (this.word.indexOf(c) == -1) {
            this.wrongGuesses++;
        } else {
            // Iterate over the word starting at the found index or zero finding all the letters
            int index = 0;
            while (index <= maxIndex) {
                index = this.word.indexOf(c, index); // If not found in word, will return -1
                if (index == -1){
                    break; // End the loop if all letters are found.
                }
                this.maskedWord[index] = c;
                index++;
            }


        }
    }

    public void displayWord() {
        for (int i = 0; i < 50; ++i) { // Simulate clearing the console out.
            System.out.println();
        }
        this.gallows();
        for (int i=0; i<this.maskedWord.length; i++){
            System.out.print(this.maskedWord[i]);
        }
        System.out.println();
        System.out.println("Guesses: " + this.wrongGuesses);
    }
    
    public String getMaskedWord() {
        //return Arrays.stream(this.maskedWord).collect(Collectors.joining(""));
        return String.valueOf(this.maskedWord);
    }

    public void gallows() {
        String [] drawings = {"   ------"
                             ,"   |     |"
                             ,"   O     |"
                             ,"  -|-    |"
                             ,"   ^     |"
                             ,"   ______|_____"};
        for (int i = 0; i < this.wrongGuesses; i++) {
            System.out.println(drawings[i]);
        }
        for (int j = 8 - this.wrongGuesses; j > 0; j--) {
            System.out.println();
        }
    }
}
