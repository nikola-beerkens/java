package expressions;

import java.util.Map;

public class Constant extends NoArgExpr {
    private double value;

    public Constant(double value){
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> env) {
        return this.value;
    }

    @Override
    public String toString(){
        return this.value + "";
    }

    @Override
    public Double getConstantValue(){
        return this.value;
    }

    @Override
    public Expression partialEval() {
        return new Constant(value);
    }
    
}
