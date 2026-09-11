package quiz;

import java.util.Arrays;
import java.util.LinkedList;

public class MultipleChoiceQuestion extends Question {

    protected String question;
    private int score;
    protected String[] answers;
    protected int correctAnswer;
    private LinkedList<String> ans = new LinkedList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k"));

    public MultipleChoiceQuestion(String question , String [] answers, int correctAnswer , int score){
        this.question = question;
        this.answers=answers;
        setScore(score);
        this.correctAnswer = correctAnswer;
    }

    public MultipleChoiceQuestion ( String question , String[] answers,int correctAnswer ){
        this.answers=answers;
        this.question=question;
        this.correctAnswer = correctAnswer;
        setScore(3);
    }

    @Override
    public String toString() {
        String result ="";
       for(int i=0;i<answers.length;i++){
        result += ans.get(i);
        result += ") ";
        result += answers[i];
        result +="\n";
       }
    return this.question + "\n" + result;
    }

    @Override
    public boolean isCorrect(String answer) {

        return correctAnswer == ans.indexOf(answer);
    }

    @Override
    public String correctAnswer() {
        return this.ans.get(correctAnswer);
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int val) {
        if(val<1 || val >5)
          this.score=3;
        else this.score = val;
    }
    
    
}
