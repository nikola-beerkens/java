package hangman;

import java.util.Scanner;

public class GallowsUI {

    private Scanner scanner;
    private String readString;
    private Gallows game;

    public GallowsUI(){
        this.scanner = new Scanner(System.in);
    }

    public void startGame(){
		System.out.println("Welcome to Hangman!");
		System.out.println("Please enter a word or press Enter to randomly pick one");
		readString = scanner.nextLine();
		if(readString.isEmpty()){
			 game = new Gallows();
             System.out.println("Picking random word");
		}
		else{
			 game = new Gallows(readString);
		}
		System.out.println("Remaining mistakes: " + game.getLives());
		System.out.println("Guessed letters: " + game.toString());
        System.out.println("Word: " + game.print().toString());
        while(game.win()){
            char c = scanner.next().charAt(0);
		    game.guessLetter(c);
            if (game.getWord().indexOf(c) != -1){
                System.out.println(c + " is in the word!");
            }
            else{
                System.out.println(c + " is not in the word");
            }
            System.out.println("Remaining mistakes: " + game.getLives());
		    System.out.println("Guessed letters: " + game.toString());
            System.out.println("Word: " + game.print().toString());
        }
    }
}