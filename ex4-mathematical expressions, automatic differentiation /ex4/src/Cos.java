/**
 * class Cos - expression of Cos with functions. inherited from UaryExpression
 * and implements from Expression.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
    * constructor - construct Cos from expression.
    * @param exp is the expression to set to the Cos.
    */
	public Cos(Expression exp) {
		super(exp);
	}
    /**
    * constructor - construct Cos from double.
    * @param exp is the expression to set to the Cos.
    */
	public Cos(double exp) {
		super(new Num(exp));
	}
    /**
    * constructor - construct Cos from string.
    * @param exp is the expression to set to the Cos.
    */
	public Cos(String exp) {
		super(new Var(exp));
	}
    /**
     * the function calculate a new variable according to Cos.
     * @param number - the calculate value.
     * @throws Exception if the variables illegal.
     * @return result of the Cos.
     */
	public double calculate(double number) throws Exception {
		return Math.cos(Math.toRadians(number));
	}
    /**
     * the function returns string of the expression.
     * @return a nice string representation of the expression.
     */
	public String toString() {
		return "(" + "cos" + "(" + this.getExp().toString() + ")" + ")";
	}

	public Expression assign(String var, Expression expression) {
		Expression newExp = this.getExp().assign(var, expression);
		if (var.equals(newExp)) {
			newExp = expression;
		}
		return new Cos(newExp);
	}
    /**
     * the function returns the expression tree resulting from differentiating
     * the current expression relative to variable `var` (recursively).
     * @param var is the string variable in the expression.
     * @return the differentiate of Cos.
     */
	public Expression differentiate(String var) {
		Expression Exp = new Neg(new Mult(new Sin(getExp()), getExp().differentiate(var)));
		return Exp;
	}
    /**
     * the function makes new expression of cos.
     * @param newExp is operator.
     * @return new expression of cos.
     */
	public Expression makeExp(Expression newExp) {
		return new Cos(newExp);
	}
}