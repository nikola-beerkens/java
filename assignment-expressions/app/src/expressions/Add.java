package expressions;

import java.util.Map;


public class Add extends TwoArgExpr {
    public Add (Expression x, Expression y) {
        super(x, y);
    }
    
    @Override
    public String toString() {
        return "(" + getX() + "+" + getY() + ")";
    }

    @Override
    public double eval (Map<String, Double> env) {
        return getX().eval(env) + getY().eval(env);
    }

    @Override
    public Expression partialEval() {
        getX().partialEval();
        getY().partialEval();

        Double x = getX().getConstantValue();
        Double y = getY().getConstantValue();

        // Expr + Const -> Expr + 0
        if(x == null && y != null && y == 0){
            return getX().partialEval();
        }
        // Const + Expr -> 0 + Expr
        if(x != null && y == null && x == 0){
            return getY().partialEval();
        }
        // Const + Const -> 0 + Const
        if(x !=null && y!=null && x == 0){
            return getY().partialEval();
        }
        // Const + Const -> Const + 0
        if(x != null && y != null && y == 0){
            return getX().partialEval();
        }
        if(x!= null && y!=null){
            return new Constant(x+y);
        }
        return new Add(getX().partialEval(), getY().partialEval());
    }
}
