import java.util.List;
import java.util.Map;


public class Mult extends BinaryExpression implements Expression {

	public Mult (Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}
	public Mult (double exp1, Expression exp2) {
		super(new Num(exp1), exp2);
	}
	public Mult (Expression exp1, double exp2) {
		super(exp1, new Num(exp2));
	}
	public Mult (String exp1, Expression exp2) {
		super(new Var(exp1), exp2);
	}
	public Mult (Expression exp1, String exp2) {
		super(exp1, new Var(exp2));
	}
	public Mult (String exp1, String exp2) {
		super(new Var(exp1), new Var(exp2));
	}
	public Mult (double exp1, double exp2) {
		super(new Num(exp1), new Num(exp2));
	}
	public Mult (String exp1, double exp2) {
		super(new Var(exp1), new Num(exp2));
	}
	
	public Mult (double exp1, String exp2) {
		super(new Num(exp1), new Var(exp2));
	}
	
	public double calculate(double number1, double number2) throws Exception {
		return number1 * number2;

	}
	public String toString() {
		return "(" + this.getExp1().toString() + " * " + this.getExp2().toString() + ")"; 
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
		
		return new Mult(newEx1, newEx2);
	}
	public Expression differentiate(String var) {
		Expression a = getExp1().differentiate(var);
		Expression b = getExp2().differentiate(var);

		Expression Exp = new Plus(new Mult(a, getExp2()), new Mult(getExp1(), b));
		System.out.println(getExp1());
		System.out.println(getExp2());

		return Exp;
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
					return exp2;
				} 
			}
		} catch (Exception e2) {
			System.out.println("Error1");
		}
		try {
			if (listExp2.isEmpty()) {
				if (exp2.evaluate() == 0) {
					return new Num(0);
				} else if (exp2.evaluate() == 1) {
					return exp1;
				} 
			}
		} catch (Exception e2) {
			System.out.println("Error2");
		}
		if ((exp1.getVariables()).isEmpty() && (exp2.getVariables()).isEmpty()) {

			try {
				double num = exp1.evaluate() * exp2.evaluate();
				return new Num(num);

			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}

		return new Mult(exp1, exp2);
	}

}