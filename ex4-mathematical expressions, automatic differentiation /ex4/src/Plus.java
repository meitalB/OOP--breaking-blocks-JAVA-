import java.util.List;
import java.util.Map;

public class Plus extends BinaryExpression implements Expression {

	public Plus(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}

	public Plus(double exp1, Expression exp2) {
		super(new Num(exp1), exp2);
	}

	public Plus(Expression exp1, double exp2) {
		super(exp1, new Num(exp2));
	}

	public Plus(String exp1, Expression exp2) {
		super(new Var(exp1), exp2);
	}

	public Plus(Expression exp1, String exp2) {
		super(exp1, new Var(exp2));
	}

	public Plus(String exp1, String exp2) {
		super(new Var(exp1), new Var(exp2));
	}

	public Plus(double exp1, double exp2) {
		super(new Num(exp1), new Num(exp2));
	}

	public Plus(String exp1, double exp2) {
		super(new Var(exp1), new Num(exp2));
	}

	public Plus(double exp1, String exp2) {
		super(new Num(exp1), new Var(exp2));
	}

	public double calculate(double number1, double number2) throws Exception {
		return number1 + number2;

	}

	public String toString() {
		/*
		 * if (this.simplify() == this.getExp1()) { return "(" +
		 * this.getExp1().toString() + ")"; } if (this.simplify() ==
		 * this.getExp2()) { return "(" + this.getExp2().toString() + ")"; }
		 */

		return "(" + this.getExp1().toString() + " + "
				+ this.getExp2().toString() + ")";
	}

	public Expression assign(String var, Expression expression) {
		Expression newEx1 = this.getExp1().assign(var, expression);
		Expression newEx2 = this.getExp2().assign(var, expression);

		if (var.equals(newEx1)) {
			newEx1 = expression;
		}
		if (var.equals(newEx2)) {
			newEx2 = expression;
		}
		return new Plus(newEx1, newEx2);
	}

	/**
	 * the function returns the expression tree resulting from differentiating
	 * the current expression relative to variable `var` (recursively).
	 * 
	 * @param var
	 *            is the string variable in the expression.
	 * @return the differentiate of Plus.
	 */
	public Expression differentiate(String var) {
		Expression Exp = new Plus(getExp1().differentiate(var), getExp2()
				.differentiate(var));
		return Exp;
	}

	/*
	public Expression simplify() {
		Expression exp1 = this.getExp1().simplify();
		Expression exp2 = this.getExp2().simplify();
		List<String> listExp1 = exp1.getVariables();
		List<String> listExp2 = exp2.getVariables();

		try {
			if (listExp1.isEmpty()) {
				if (exp1.evaluate() == 0) {
					return exp2;
				}
			}
		} catch (Exception e2) {
			System.out.println("Error1");
		}
		try {
			if (listExp2.isEmpty()) {
				if (exp2.evaluate() == 0) {
					return exp1;
				}
			}
		} catch (Exception e2) {
			System.out.println("Error2");
		}
		if ((exp1.getVariables()).isEmpty() && (exp2.getVariables()).isEmpty()) {

			try {
				double num = exp1.evaluate() + exp2.evaluate();
				return new Num(num);

			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}
		if (exp1 instanceof Var && exp2 instanceof Var) {
			if (exp1.toString().equals(exp2.toString())) {
				return new Mult(2, exp1).simplify();
			}
		}
		if (exp1 instanceof Var && exp2 instanceof Mult) {
			exp1 = new Mult(1, exp1);
		}
		if (exp2 instanceof Var && exp1 instanceof Mult) {
			exp2 = new Mult(1, exp2);
		}
		if (exp1 instanceof Mult && exp2 instanceof Mult) {
			Expression exp1a = orderInMultNum(exp1);
			Expression exp1b = orderInMultVar(exp1);
			Expression exp2a = orderInMultNum(exp2);
			Expression exp2b = orderInMultVar(exp2);
			if (exp1b.toString().equals(exp2b.toString())) {
				try {
					return new Mult(exp1a.evaluate() + exp2a.evaluate(), exp1b)
							.simplify();
				} catch (Exception e) {
				}
			}
			if (exp1 instanceof Mult) {
				exp1a = orderInMultVar(exp1);
				exp2a = exp2.simplify();

				try {
					return new Plus(exp1a.evaluate(), exp2a.simplify())
							.simplify();
				} catch (Exception e) {
				}
			}
		}

		return new Plus(exp1, exp2);
	}

	public Expression orderInMultNum(Expression exp) {
		Expression exp1a = ((Mult) exp).getExp1();
		Expression exp1b = ((Mult) exp).getExp2();
		if (exp1a instanceof Num) {
			return exp1a;
		}
		if (exp1b instanceof Num) {
			return exp1b;
		}
		return null;
	}

	public Expression orderInMultVar(Expression exp) {
		Expression exp1a = ((Mult) exp).getExp1();
		Expression exp1b = ((Mult) exp).getExp2();
		if (exp1a instanceof Var) {
			return exp1a;
		}
		if (exp1b instanceof Var) {
			return exp1b;
		} if (exp1a instanceof Mult) {
			 orderInMultVar(((Mult) exp1a).getExp1());
			 orderInMultVar(((Mult) exp1a).getExp2());
		} if (exp1b instanceof Mult) {
			 orderInMultVar(((Mult) exp1b).getExp1());
			 orderInMultVar(((Mult) exp1b).getExp2());
		}
		return null;
	}
*/
	
	
	
	public Expression simplify() {
		Expression exp1 = this.getExp1().simplify();
		Expression exp2 = this.getExp2().simplify();
		List<String> listExp1 = exp1.getVariables();
		List<String> listExp2 = exp2.getVariables();
		if (exp1.toString().equals(exp2.toString())) {
			return new Num(0);
		}
		try {
			if (listExp1.isEmpty()) {
				if (exp1.evaluate() == 0) {
					return new Neg(exp2);
				}
			}
		} catch (Exception e2) {
			System.out.println("Error1");
		}
		try {
			if (listExp2.isEmpty()) {
				if (exp2.evaluate() == 0) {
					return exp1;
				}
			}
		} catch (Exception e2) {
			System.out.println("Error2");
		}
		if ((exp1.getVariables()).isEmpty() && (exp2.getVariables()).isEmpty()) {

			try {
				double num = exp1.evaluate() + exp2.evaluate();
				return new Num(num);

			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}

		return new Plus(exp1, exp2);
	}
	
}
