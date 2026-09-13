package expressions;

public abstract class OneArgExpr implements Expression{
    private final Expression x;

    public OneArgExpr(Expression x) {
        this.x = x;
    }

    public Expression getX() {
        return x;
    }
}
