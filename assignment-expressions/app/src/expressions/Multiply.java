package expressions;

import java.util.Map;

public class Multiply extends TwoArgExpr{
    public Multiply (Expression x, Expression y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "(" + getX() + "*" + getY() + ")";
    }
    
    @Override
    public double eval(Map<String, Double> env) {
        return getX().eval(env) * getY().eval(env);
    }

	@Override
	public Expression partialEval() {
		getX().partialEval();
        getY().partialEval();

        Double x = getX().getConstantValue();
        Double y = getY().getConstantValue();
        
        // Expr * Const -> Expr * 0 
        if(x == null && y != null && y == 0){
            return new Constant(y).partialEval();
        }
        // Const + Expr -> 0 * Expr
        if(x != null && y == null && x == 0){
            return new Constant(x).partialEval();
        }
        if(x != null && y == null && x == 1){
            return getY().partialEval();
        }
        if(x == null && y != null && y == 1){
            return getX().partialEval();
        }
        if(x != null && y !=null){
            return new Constant(x*y);
        }
        return new Multiply(getX().partialEval(), getY().partialEval());
	}
}
