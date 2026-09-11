package hangman;

import java.util.ArrayList;
import java.util.List;


public class Gallows {
    private String word;
    private int noOfTries = 10;
    private List<Character> guessedLetters;
    private WordReader wordReader = new WordReader("words.txt");
    private String covered;
    private StringBuilder something;

    public Boolean win(){
        String our = something.toString();
        if(!this.word.equals(our) && noOfTries != 0){
            return true;
        }
        return false;
    }

    public Gallows(String word){
        this.word=word;
        this.covered = dots();
        this.something= new StringBuilder(covered);
        this.guessedLetters =new ArrayList<Character>();
    }

    public Gallows(){
        this.word = wordReader.getWord();
        this.covered = dots();
        this.something= new StringBuilder(covered);
        this.guessedLetters =new ArrayList<Character>();
    }

    public String getWord(){
        return this.word;
    }

    private String dots(){
        String dot = "";
        for(int i=0;i<this.word.length();i++){
            dot += ".";
        }
        return dot;
    }

    public int getLives()
    {
        return this.noOfTries;
    }

    public boolean guessLetter(char letter){
        boolean isInWord = word.indexOf(letter) != -1;
        for(int i=0; i<word.length(); i++){
            if(letter == word.charAt(i)){
                something.setCharAt(i, letter);
            }
        }
        if(!isInWord){
            noOfTries--;
        }
        guessedLetters.add(letter);
        return isInWord;
    }

    public String toString(){
        String string = "[ ";
        for(int i=0; i<guessedLetters.size(); i++){
            string += guessedLetters.get(i);
        }
        string += " ]";
        return string;
    }

    public StringBuilder print(){
        return this.something;
    }
}
