package expressions;

import java.util.Map;

public class Negate extends OneArgExpr{

    public Negate (Expression x) {
        super(x);
    }
    
    @Override
    public String toString() {
        return "-" + getX();
    }

    @Override
    public double eval (Map<String, Double> env) {
        return getX().eval(env)*(-1);
    }

    @Override
    public Expression partialEval() {

        Double x = getX().getConstantValue();
        
        if(x != null){
            return new Negate(getX());
        }
        return new Negate(getX().partialEval());

    }
}
