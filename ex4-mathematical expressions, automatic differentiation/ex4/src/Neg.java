
public class Neg extends UnaryExpression implements Expression {
	public Neg(Expression exp) {
		super(exp);
	}
	public Neg(double exp) {
		super(new Num(exp));
	}
	public Neg(String exp) {
		super(new Var(exp));
	}

	public double calculate(double number) throws Exception {
		return (number * - 1);

	}
	public String toString() {
		return "(-" + this.getExp().toString() + ")"; 
	}
	public Expression assign(String var, Expression expression) {
		Expression newExp = this.getExp().assign(var, expression);
		if (var.equals(newExp)) {
			newExp = expression;
		}
		return new Neg(newExp);
	}
	public Expression differentiate(String var) {
		Expression Exp = new Neg(getExp().differentiate(var));
		return Exp;
	}
    /**
     * the function makes new expression of Neg.
     * @param newExp is operator.
     * @return new expression of Neg.
     */
    public Expression makeExp(Expression newExp) {
        return new Neg(newExp);
    }
}