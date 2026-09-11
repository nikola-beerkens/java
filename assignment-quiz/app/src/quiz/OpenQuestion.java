package quiz;

public class OpenQuestion extends Question {

    private String question;
    private String answer;
    private int score;

    public OpenQuestion(String question , String answer , int score){
        this.question = question;
        this.answer=answer;
        setScore(score);
    }

    public OpenQuestion ( String question , String answer ){
        this.answer=answer;
        this.question=question;
        setScore(3);
    }

    @Override
    public String toString() {
         return this.question;
    }

    @Override
    public boolean isCorrect(String answer) {
        return answer.toLowerCase().equals(this.answer.toLowerCase());
    }

    @Override
    public String correctAnswer() {
        return this.answer;
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
