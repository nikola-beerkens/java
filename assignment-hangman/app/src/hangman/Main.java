package hangman;

public class Main {

	public static void main(String[] args) {
		GallowsUI name = new GallowsUI();
		name.startGame();
		/*
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Hangman!");
		System.out.println("Please enter a word or press Enter to randomly pick one");
		String readString = scanner.nextLine();
		Gallows game = new Gallows("cock");
		if(readString.isEmpty()){
			 game = new Gallows();
		}
		else{
			 game = new Gallows(readString);
		}
		System.out.println("Remaining mistakes: " + game.getLives());
		System.out.println("Guessed letters: ");
		char c = scanner.next().charAt(0);
		game.guessLetter(c);
		System.out.println("Word: " + game.printt().toString());
		*/
	}
}