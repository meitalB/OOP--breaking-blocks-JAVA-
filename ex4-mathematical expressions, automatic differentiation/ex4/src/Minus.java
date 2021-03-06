import java.util.List;
import java.util.Map;
/**
 * class Minus - expression of Minus with functions. inherited from BinaryExpression
 * and implements from Expression.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * constructor Minus - construct Minus with two expressions.
     * @param ex1 is the first expression value.
     * @param ex2 is the second expression value.
     */
	public Minus(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}
    /**
     * constructor Minus - construct Minus with double and expression.
     * @param ex1 is the first double value.
     * @param ex2 is the second expression value.
     */
	public Minus(double exp1, Expression exp2) {
		super(new Num(exp1), exp2);
	}
    /**
     * constructor Minus - construct Minus with double and expression.
     * @param ex1 is the first expression value.
     * @param ex2 is the second double value.
     */
	public Minus(Expression exp1, double exp2) {
		super(exp1, new Num(exp2));
	}
    /**
     * constructor Minus - construct Minus with string and expression.
     * @param ex1 is the first string value.
     * @param ex2 is the second expression value.
     */
	public Minus(String exp1, Expression exp2) {
		super(new Var(exp1), exp2);
	}
    /**
     * constructor Minus - construct Minus with string and expression.
     * @param ex1 is the first expression value.
     * @param ex2 is the second string value.
     */
	public Minus(Expression exp1, String exp2) {
		super(exp1, new Var(exp2));
	}
    /**
     * constructor Minus - construct Minus with two strings.
     * @param ex1 is the first string value.
     * @param ex2 is the second string value.
     */
	public Minus(String exp1, String exp2) {
		super(new Var(exp1), new Var(exp2));
	}
    /**
     * constructor Minus - construct Minus with two doubles.
     * @param ex1 is the first double value.
     * @param ex2 is the second double value.
     */
	public Minus(double exp1, double exp2) {
		super(new Num(exp1), new Num(exp2));
	}
    /**
     * constructor Minus - construct Minus with string and double.
     * @param ex1 is the first string value.
     * @param ex2 is the second double value.
     */
	public Minus(String exp1, double exp2) {
		super(new Var(exp1), new Num(exp2));
	}
    /**
     * constructor Minus - construct Minus with string and double.
     * @param ex1 is the first double value.
     * @param ex2 is the second string value.
     */
	public Minus(double exp1, String exp2) {
		super(new Num(exp1), new Var(exp2));
	}
    /**
     * the function calculate the Minus between to numbers.
     * @param number1 - the first calculate value.
     * @param number2 - the second calculate value.
     * @throws Exception if the variables illegal.
     * @return result of the Minus.
     */
	public double calculate(double number1, double number2) throws Exception {
		return number1 - number2;
	}
	/**
	 * the function returns string of the expression.
	 * @return a nice string representation of the expression.
	 */
	public String toString() {
		return "(" + this.getExp1().toString() + " - "
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
		return new Minus(newEx1, newEx2);
	}
	/**
	 * the function returns the expression tree resulting from differentiating
	 * the current expression relative to variable `var` (recursively).
	 * @param var is the string variable in the expression.
	 * @return the differentiate of Minus.
	 */
	public Expression differentiate(String var) {
		Expression Exp = new Minus(getExp1().differentiate(var), getExp2().differentiate(var));
		return Exp;
	}
	/**
	 * the function simplifies version the current expression.
	 * @return a simplified expression of the current expression.
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
				double num = exp1.evaluate() - exp2.evaluate();
				return new Num(num);

			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}

		return new Minus(exp1, exp2);
	}
}