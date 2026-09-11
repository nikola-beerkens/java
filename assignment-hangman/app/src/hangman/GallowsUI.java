package hangman;

import java.util.Scanner;

public class GallowsUI {

    private static final Scanner scanner = new Scanner(System.in);
    private final Gallows game;

    public GallowsUI(){
        System.out.println("Welcome to Hangman!");
        System.out.println("Please enter a word or press Enter to randomly pick one");
        String readString = scanner.nextLine();
        if(readString.isEmpty()){
            game = new Gallows();
            System.out.println("Picking random word");
        }
        else{
            game = new Gallows(readString);
        }
    }

    public void startGame(){
		System.out.println("Remaining mistakes: " + game.getLives());
		System.out.println("Guessed letters: " + game.toString());
        System.out.println("Word: " + game.print().toString());
        do{
            char c = scanner.next().charAt(0);
            if (game.guessLetter(c)){
                System.out.println(c + " is in the word!");
            }
            else{
                System.out.println(c + " is not in the word");
            }
            System.out.println("Remaining mistakes: " + game.getLives());
		    System.out.println("Guessed letters: " + game.toString());
            System.out.println("Word: " + game.print().toString());
        }while(game.win());
    }
}
