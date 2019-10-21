/**
 * Establishes the word bank for the game by reading a file of words and returning a single word.
 * The Class establishes two different scanners. One is used to read the total number of lines of the file.
 * The second Scanner is used in cojunction with a random number, from 0 to the number of lines in the file, to obtain
 * a random word for use elsewhere in the game.
 * Randomized through use of a random number generator
 */
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class WordBank {
    // Fields
    private File file;
    private Scanner scanner;
    // Constructor
    WordBank(File file) throws Exception{
        try {
            this.file = file;
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(file.getName() + " not found. Please check the file you submitted or use a different file.");
            System.out.println("System message" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("You must provide a file name");
            System.out.println("System message" + e.getMessage());
        }
    }
    // Methods
    /**
     * Obtains a new word for use throughout the game. Uses inherited fields obtain the word.
     * @return The word used in the game.
     * @throws Exception
     */
    public String newWord() throws Exception {
        Random random = new Random();
        String word = null;
        int numberOfLines = numberOfLinesInFile(this.file);
        int linesToSkip = random.nextInt(numberOfLines);// *  - 1;
        try {
            for (int i=0; i<linesToSkip; i++) {
                word = this.scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Not reading a line.");
        }
        return word;
    }

    private int numberOfLinesInFile(File file) throws FileNotFoundException {
        int numberOfLines = 0;
        Scanner scanForLines = new Scanner(file);
        while (scanForLines.hasNextLine()) {
            scanForLines.nextLine();
            numberOfLines++;
        }
        scanForLines.close();
        return numberOfLines;
    }
}
