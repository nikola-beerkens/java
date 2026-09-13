package expressions;

import java.util.Map;

public abstract class NoArgExpr implements Expression {

    @Override
    public abstract double eval(Map<String, Double> env);

    @Override
    public abstract Expression partialEval();

    @Override
    public abstract String toString();
}
