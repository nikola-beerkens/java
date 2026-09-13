package expressions;

public abstract class TwoArgExpr implements Expression{
    private final Expression x, y;

    public TwoArgExpr(Expression x, Expression y) {
        this.x = x;
        this.y = y;
    }

    public Expression getX() {
        return x;
    }

    public Expression getY() {
        return y;
    }
}
