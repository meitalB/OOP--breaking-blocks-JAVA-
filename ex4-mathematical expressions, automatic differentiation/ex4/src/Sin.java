/**
 * class Sin - expression of Sin with functions. inherited from UaryExpression
 * and implements from Expression.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
    * constructor - construct Sin from expression.
    * @param exp is the expression to set to the Sin.
    */
	public Sin(Expression exp) {
		super(exp);
	}
    /**
    * constructor - construct Sin from double.
    * @param exp is the expression to set to the Sin.
    */
	public Sin(double exp) {
		super(new Num(exp));
	}
    /**
    * constructor - construct Sin from string.
    * @param exp is the expression to set to the Sin.
    */
	public Sin(String exp) {
		super(new Var(exp));
	}
    /**
     * the function calculate a new variable according to Sin.
     * @param number - the calculate value.
     * @throws Exception if the variables illegal.
     * @return result of the Sin.
     */
	public double calculate(double number) throws Exception {
		return Math.sin(Math.toRadians(number));
	}
    /**
     * the function returns string of the expression.
     * @return a nice string representation of the expression.
     */
	public String toString() {
		return "(" + "sin" + "(" + this.getExp().toString() + ")" + ")"; 
	}
	public Expression assign(String var, Expression expression) {
		Expression newExp = this.getExp().assign(var, expression);
		if (var.equals(newExp)) {
			newExp = expression;
		}
		return new Sin(newExp);
	}
    /**
     * the function returns the expression tree resulting from differentiating
     * the current expression relative to variable `var` (recursively).
     * @param var is the string variable in the expression.
     * @return the differentiate of Sin.
     */
	public Expression differentiate(String var) {
		Expression Exp = new Mult(new Cos(getExp()), getExp().differentiate(var));
		return Exp;
	}
    /**
     * the function makes new expression of Sin.
     * @param newExp is operator.
     * @return new expression of Sin.
     */
    public Expression makeExp(Expression newExp) {
        return new Sin(newExp);
    }
}
