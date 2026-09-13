package expressions;

public class ExpressionFactory {

	public static Expression var(String x) {
		return new Variable(x);
	}

	public static Expression con(Double x) {
		return new Constant(x);
	}

	public static Expression add(Expression x, Expression y) {
		return new Add(x, y);
	}

	public static Expression mul(Expression x, Expression y) {
		return new Multiply(x, y);
	}

	public static Expression neg(Expression x) {
		return new Negate(x);
	}
}
