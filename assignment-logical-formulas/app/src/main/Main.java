package main;
/*
Nikola Beerkens /s1041042
Öykü Dila Akansu /s1041225
*/
    import java.util.HashMap;
    import java.util.Map;
    import ast.Formula;
    import static ast.FormulaFactory.*;

public class Main {

	private static Map<String, Boolean> env;

	
	public static void setUp() {
		env = new HashMap<>();
		env.put("P", true);
		env.put("Q", false);
		env.put("R", true);
		env.put("S", false);
	}

	
	public static void testEvalTrue() {
		Formula f = TRUE;
		evaluate(f, env);
	}

	
	public static void testEvalFalse() {
		Formula f = FALSE;
		evaluate(f, env);
	}

	
	public static void testEvalAtomP() {
		Formula f = atom("P");
		evaluate(f, env);
	}

	
	public static void testEvalAtomQ() {
		Formula f = atom("Q");
		System.out.println(evaluate(f, env));
	}


	public static void testEvalNot() {
		Formula nf = not(FALSE);
		Formula nt = not(TRUE);
		evaluate(nf, env);
		evaluate(nt, env);
	}

	
	public void testEvalAnd() {
		Formula ff = and(FALSE, FALSE);
		Formula tf = and(TRUE, FALSE);
		Formula ft = and(FALSE, TRUE);
		Formula tt = and(TRUE, TRUE);
		evaluate(ff, env);
		evaluate(tf, env);
		evaluate(ft, env);
		evaluate(tt, env);
	}

	public void testEvalOr() {
		Formula ff = or(FALSE, FALSE);
		Formula tf = or(TRUE, FALSE);
		Formula ft = or(FALSE, TRUE);
		Formula tt = or(TRUE, TRUE);
		evaluate(ff, env);
		evaluate(tf, env);
		evaluate(ft, env);
		evaluate(tt, env);
	}

	
	public static void testEvalImplies() {
		Formula ff = implies(FALSE, FALSE);
		Formula tf = implies(TRUE, FALSE);
		Formula ft = implies(FALSE, TRUE);
		Formula tt = implies(TRUE, TRUE);
		evaluate(ff, env);
		evaluate(tf, env);
		evaluate(ft, env);
		evaluate(tt, env);
	}

	public static void testEvalParens() {
		// (Q => R) /\ S
		Formula f1 = and(implies(atom("Q"), atom("R")), atom("S"));
		// Q => (R /\ S)
		Formula f2 = implies(atom("Q"), and(atom("R"), atom("S")));
		System.out.println(evaluate(f1, env));
		System.out.println(evaluate(f2, env));
	}

	public static void main(String[] args) {
		setUp();
                testEvalParens();
                testEvalAtomQ();
	}
}
