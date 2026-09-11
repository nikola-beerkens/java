package quiz;

public class ThisThatQuestion extends MultipleChoiceQuestion {

    public ThisThatQuestion( String question , String answer1 , String answer2 , int correctAnswer , int score){
        super(question, new String[] {answer1, answer2}, correctAnswer, score);

    }
    public ThisThatQuestion( String question , String answer1 , String answer2 , int correctAnswer){
        super(question, new String[] {answer1, answer2}, correctAnswer);

    }

    @Override
    public String toString() {
        
    return this.answers[0] + " or " + this.answers[1] + ": " + this.question;
    }

    @Override
    public boolean isCorrect(String answer) {
        return this.answers[this.correctAnswer].toLowerCase().equals(answer.toLowerCase());
    }

    @Override
    public String correctAnswer() {
        return this.answers[this.correctAnswer];
    }
    
}
