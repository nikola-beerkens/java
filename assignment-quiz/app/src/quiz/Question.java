package quiz;

public abstract class Question {
    
    public abstract String toString ();
    public abstract boolean isCorrect (String answer) ;
    public abstract String correctAnswer ();
    public abstract int getScore ();
    public abstract void setScore (int val);
}
