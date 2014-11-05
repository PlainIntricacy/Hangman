package game.hangman;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

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
        BufferedReader dictreader = new BufferedReader(new FileReader(dictionary));
        ArrayList<String> WordList = new ArrayList<String>();
        String wordreader = dictreader.readLine();
        while(wordreader!=null){
            if(wordreader.length()==diff){
                WordList.add(wordreader);
            }
            wordreader=dictreader.readLine();
        }
        Random rnd = new Random();
        String answer = WordList.get(rnd.nextInt(WordList.size())).toLowerCase();
        //StringBuilder pass = new StringBuilder(answer.replaceAll(".","*"));
        String pass = new String();
        for(int i=0; i<answer.length(); i++){
            pass+="*";
        }
        int currG = 0;
        String listG = "";
        //char input;
        do{
            System.out.println();
            System.out.println("Actual word: " + answer);
            System.out.println("Word: " + pass);
            System.out.println("Guesses (" + currG + "/" + diff*2 + "): " + listG);
            System.out.println("HINT: The word contains " + getHint(answer) + " vowels!");
            System.out.println();
            System.out.println("Please enter your guess (one letter):");
            char input = in.next().charAt(0);
            boolean found = false;
            for(int i=0; i<answer.length(); i++){
                if(answer.charAt(i)==input){
                    pass.replace(pass.charAt(i), input);
                    found = true;
                }
            }
            listG += input + " ";
            if(found){
                System.out.println();
                System.out.println("Your guess, " + input + ", was found!");
            }else{
                System.out.println();
                System.out.println("Your guess, " + input + ", was not found.");
            }
            currG++;
        }while((currG<diff*2) || !pass.equals(answer));
        if(pass.equals(answer)){
            System.out.println("Congratulations! You found the word.");
        }else{
            System.out.println("Sorry, you did not find the word.");
            System.out.println("The correct answer was: " + answer);
        }
        dictreader.close();
    }

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

    /*public static String buildpass(String x){
        String result = "";
        for(int i=0; i<x.length(); i++){
            result+="*";
        }
        return result;
    }*/
}
