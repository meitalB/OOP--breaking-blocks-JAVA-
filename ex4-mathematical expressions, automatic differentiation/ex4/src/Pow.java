import java.util.List;
import java.util.Map;

public class Pow extends BinaryExpression implements Expression {

	public Pow(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}

	public Pow(double exp1, Expression exp2) {
		super(new Num(exp1), exp2);
	}

	public Pow(Expression exp1, double exp2) {
		super(exp1, new Num(exp2));
	}

	public Pow(String exp1, Expression exp2) {
		super(new Var(exp1), exp2);
	}

	public Pow(Expression exp1, String exp2) {
		super(exp1, new Var(exp2));
	}

	public Pow(String exp1, String exp2) {
		super(new Var(exp1), new Var(exp2));
	}

	public Pow(double exp1, double exp2) {
		super(new Num(exp1), new Num(exp2));
	}

	public Pow(String exp1, double exp2) {
		super(new Var(exp1), new Num(exp2));
	}

	public Pow(double exp1, String exp2) {
		super(new Num(exp1), new Var(exp2));
	}

	public double calculate(double number1, double number2) throws Exception {
		return Math.pow(number1, number2);

	}

	public String toString() {
		return "(" + this.getExp1().toString() + " ^ "
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
		return new Pow(newEx1, newEx2);
	}

	public Expression differentiate(String var) {
		double newVar = 0;
		Expression exp;
		double x;
		
		try {
			x = getExp1().differentiate(var).evaluate();
			if ( x == 0.0) {
				return new Num(0);
			}
		} catch (Exception e) {
			
		}
			
		if (getExp2().differentiate(var) == new Num(0)) {
			return new Num(1);
		}
		if (getExp2() instanceof Num) {
			try { 
				newVar = getExp2().evaluate() - 1;
			} catch (Exception e) {

			}
			exp = new Mult(new Mult(getExp2(), new Pow(getExp1(), newVar)), getExp1().differentiate(var));
		} else { 
			exp = new Mult(new Pow(getExp1(), getExp2()), new Plus(
					new Mult(getExp1().differentiate(var), new Div(getExp2(),
							getExp1())), new Mult(getExp2().differentiate(var),
							new Log(E, getExp1()))));
		}
		return exp;
	}

	public Expression simplify() {
		Expression exp1 = this.getExp1().simplify();
		Expression exp2 = this.getExp2().simplify();
		List<String> listExp1 = exp1.getVariables();
		List<String> listExp2 = exp2.getVariables();

		try {
			if (listExp1.isEmpty()) {
				if (exp1.evaluate() == 0) {
					return new Num(0);
				} else if (exp1.evaluate() == 1) {
					return new Num(1);
				}
			}
		} catch (Exception e2) {
			System.out.println("Error1");
		}
		try {
			if (listExp2.isEmpty()) {
				if (exp2.evaluate() == 0) {
					return new Num(1);
				} else if (exp2.evaluate() == 1) {
					return exp1;
				}
			}
		} catch (Exception e2) {
			System.out.println("Error2");
		}
		if ((exp1.getVariables()).isEmpty() && (exp2.getVariables()).isEmpty()) {

			try {
				double num = this.calculate(exp1.evaluate(), exp2.evaluate());
				return new Num(num);

			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}
		if (exp1 instanceof Pow) {
			Expression exp1a = ((Pow) exp1).getExp1();
			Expression exp1b = ((Pow) exp1).getExp2();

			return new Pow(exp1a, new Mult(exp1b, exp2)).simplify();
		}
		return new Pow(exp1, exp2);
	}
}