package game.hangman;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.util.stream.StreamSupport;

public class Main {

    public static Scanner in = new Scanner(System.in);
    public static File dictionary = new File("C:/Users/Irukandji/code/Hangman/src/game/hangman/enable1.txt");

    public static void main(String[] args) throws IOException {

        int menu_input;

        System.out.println("Welcome to Hangman!");
        System.out.println();

        do {
            System.out.println("Please choose a difficulty level:");
            System.out.println("1 = Easy: 5 letter word, 10 chances.");
            System.out.println("2 = Medium: 7 letter word, 14 chances.");
            System.out.println("3 = Hard: 10 letter word, 20 chances.");
            System.out.println("0 = Exit Game");
            menu_input = in.nextInt();
        }while (menu_input<0||menu_input>3);

        switch (menu_input){
            case 0:
                System.out.println("Thank you for playing Hangman!");
                break;
            case 1:
                runGame(5);
                break;
            case 2:
                runGame(7);
                break;
            case 3:
                runGame(10);
                break;
            default:
                break;
        }

        in.close();
    }

    public static void runGame(int diff) throws IOException {
        //builds an arraylist of words based on the given difficulty (diff variable)
        BufferedReader dictreader = new BufferedReader(new FileReader(dictionary));
        ArrayList<String> WordList = new ArrayList<String>();
        String wordreader = dictreader.readLine();
        while(wordreader!=null){
            if(wordreader.length()==diff){
                WordList.add(wordreader);
            }
            wordreader=dictreader.readLine();
        }
        //extracts a random word as the answer from the previously formed arraylist of eligible words
        Random rnd = new Random();
        String answer = WordList.get(rnd.nextInt(WordList.size())).toLowerCase();
        //builds the word to be displayed and updated during the game
        char[] pass = new char[diff];
        for(int i=0; i<answer.length(); i++){
            pass[i]='*';
        }
        //variable currG will count the number of guesses the player has made
        int currG = 0;
        String listG = "";
        //do-while loop of the game information to be displayed to the player
        do{
            System.out.println();
            //System.out.println("Actual word: " + answer);     //For testing purposes
            System.out.println("Word: " + new String(pass));
            System.out.println("Guesses (" + currG + "/" + diff*2 + "): " + listG);
            System.out.println("HINT: The word contains " + getHint(answer) + " vowels!");
            System.out.println();
            System.out.println("Please enter your guess (one letter):");
            //searches for the player's guess in the answer String
            //if the guess is correct, the pass variable is updated accordingly
            char input = in.next().charAt(0);
            //found is used to verify if the player's guess was correct
            boolean found = false;
            for(int i=0; i<answer.length(); i++){
                if(answer.charAt(i)==input){
                    pass[i] = input;
                    found = true;
                }
            }
            listG += input + " ";
            //based on the value of found, the player is informed on whether their guess was correct
            if(found){
                System.out.println();
                System.out.println("Your guess, " + input + ", was found!");
            }else{
                System.out.println();
                System.out.println("Your guess, " + input + ", was not found.");
            }
            currG++;
        //loop the game until the player finds the answer or they run out of guesses
        }while(currG<diff*2 && !new String(pass).equals(answer));
        //appropriate messages to the player based on the result of the game:
        //either they found the answer or they ran out of guesses
        if(new String(pass).equals(answer)){
            System.out.println();
            System.out.println("Congratulations! You found the word - " + answer + " - in " + currG + " guesses.");
        }else{
            System.out.println();
            System.out.println("Sorry, you did not find the word.");
            System.out.println("The correct answer was: " + answer);
        }
        dictreader.close();
    }
    //calculates the number of vowels the answer contains
    //used as a hint for the player
    public static int getHint(String q){
        q=q.toLowerCase();
        int num = 0;
        for(int i=0; i<q.length(); i++){
            if(q.charAt(i)=='a'||q.charAt(i)=='e'||q.charAt(i)=='i'||q.charAt(i)=='o'||q.charAt(i)=='u'){
                num++;
            }
        }
        return num;
    }
}
