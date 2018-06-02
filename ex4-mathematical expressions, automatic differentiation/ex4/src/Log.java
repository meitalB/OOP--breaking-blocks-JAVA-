import java.util.List;
import java.util.Map;

/**
 * class Log - expression of Log with functions. inherited from BinaryExpression
 * and implements from Expression.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * constructor Log - construct Log with two expressions.
     * @param ex1 is the first expression value.
     * @param ex2 is the second expression value.
     */
	public Log(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}
    /**
     * constructor Log - construct Log with double and expression.
     * @param ex1 is the first double value.
     * @param ex2 is the second expression value.
     */
	public Log(double exp1, Expression exp2) {
		super(new Num(exp1), exp2);
	}
    /**
     * constructor Log - construct Log with double and expression.
     * @param ex1 is the first expression value.
     * @param ex2 is the second double value.
     */
	public Log(Expression exp1, double exp2) {
		super(exp1, new Num(exp2));
	}
    /**
     * constructor Log - construct Log with string and expression.
     * @param ex1 is the first string value.
     * @param ex2 is the second expression value.
     */
	public Log(String exp1, Expression exp2) {
		super(new Var(exp1), exp2);
	}
    /**
     * constructor Log - construct Log with string and expression.
     * @param ex1 is the first expression value.
     * @param ex2 is the second string value.
     */
	public Log(Expression exp1, String exp2) {
		super(exp1, new Var(exp2));
	}
    /**
     * constructor Log - construct Log with two strings.
     * @param ex1 is the first string value.
     * @param ex2 is the second string value.
     */
	public Log(String exp1, String exp2) {
		super(new Var(exp1), new Var(exp2));
	}
    /**
     * constructor Log - construct Log with two doubles.
     * @param ex1 is the first double value.
     * @param ex2 is the second double value.
     */
	public Log(double exp1, double exp2) {
		super(new Num(exp1), new Num(exp2));
	}
    /**
     * constructor Log - construct Log with string and double.
     * @param ex1 is the first string value.
     * @param ex2 is the second double value.
     */
	public Log(String exp1, double exp2) {
		super(new Var(exp1), new Num(exp2));
	}
    /**
     * constructor Log - construct Log with string and double.
     * @param ex1 is the first double value.
     * @param ex2 is the second string value.
     */
	public Log(double exp1, String exp2) {
		super(new Num(exp1), new Var(exp2));
	}
    /**
     * the function calculate the Log between to numbers.
     * @param number1 - the first calculate value.
     * @param number2 - the second calculate value.
     * @throws Exception if the variables illegal.
     * @return result of the Log.
     */
	public double calculate(double number1, double number2) throws Exception {
		return Math.log(number2) / Math.log(number1);
	}
	/**
	 * the function returns string of the expression.
	 * @return a nice string representation of the expression.
	 */
	public String toString() {
		if (this.getExp1().toString().equals("2.71828"))
			return "log(" + "e" + ", " + this.getExp2().toString() + ")";
		return "log(" + this.getExp1().toString() + ", "
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
		return new Log(newEx1, newEx2);
	}
	/**
	 * the function returns the expression tree resulting from differentiating
	 * the current expression relative to variable `var` (recursively).
	 * @param var is the string variable in the expression.
	 * @return the differentiate of Log.
	 */
	public Expression differentiate(String var) {
		double newVar = 0;
		Expression Exp;
		if (getExp1() instanceof Num) {
			try {
				newVar = getExp1().evaluate();
			} catch (Exception e) {

			}
			Exp = new Div(getExp2().differentiate(var), new Mult(getExp2(),
					(Math.log(newVar) / Math.log(E))));
		} else {
			Exp = new Div(new Log(E, getExp2()).differentiate(var), new Log(E, getExp1()).differentiate(var));
		}
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
			return new Num(1);
		}
		try {
			if (listExp1.isEmpty()) {
				if (exp1.evaluate() == exp2.evaluate()) {
					return new Num(1);
				}
			}
		} catch (Exception e2) {
			System.out.println("Error1");
		}
		if ((exp1.getVariables()).isEmpty() && (exp2.getVariables()).isEmpty()) {

			try {
				double num = this.calculate(exp1.evaluate(), exp2.evaluate());
				return new Num(num);
			} catch (Exception e1) {
				System.out.println("Error3");
			}
		}
		return new Log(exp1, exp2);
	}
}