import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * The class BinaryExpression - an abstract class with 2 members,
 * and inherited from the BaseExpression.
 */
public abstract class BinaryExpression extends BaseExpression {
	private Expression ex1;
	private Expression ex2;
	/**
     * constructor BinaryExpression - construct the members with two expressions.
     * @param ex1 is the first expression member.
     * @param ex2 is the second expression member.
     */
	public BinaryExpression(Expression exp1, Expression exp2) {
		this.setExp1(exp1);
		this.setExp2(exp2);
	}
	/**
     * constructor BinaryExpression - construct the members with double and expression.
     * @param ex1 is the first double member.
     * @param ex2 is the second expression member.
     */
	public BinaryExpression(double exp1, Expression exp2) {
		this.setExp1(new Num(exp1));
		this.setExp2(exp2);
	}
	/**
     * constructor BinaryExpression - construct the members with expression and double.
     * @param ex1 is the first expression member.
     * @param ex2 is the second double member.
     */
	public BinaryExpression(Expression exp1, double exp2) {
		this.setExp1(exp1);
		this.setExp2(new Num(exp2));
	}
	/**
     * constructor BinaryExpression - construct the members with string and expression.
     * @param ex1 is the first string member.
     * @param ex2 is the second expression member.
     */
	public BinaryExpression(String exp1, Expression exp2) {
		this.setExp1(new Var(exp1));
		this.setExp2(exp2);
	}
	/**
     * constructor BinaryExpression - construct the members with string and double.
     * @param ex1 is the first expression member.
     * @param ex2 is the second string member.
     */
	public BinaryExpression(Expression exp1, String exp2) {
		this.setExp1(exp1);
		this.setExp2(new Var(exp2));
	}
	/**
     * constructor BinaryExpression - construct the members two strings.
     * @param ex1 is the first string member.
     * @param ex2 is the second string member.
     */
	public BinaryExpression(String exp1, String exp2) {
		this.setExp1(new Var(exp1));
		this.setExp2(new Var(exp2));
	}
	/**
     * constructor BinaryExpression - construct the members two double.
     * @param ex1 is the first string member.
     * @param ex2 is the second string member.
     */
	public BinaryExpression(double exp1, double exp2) {
		this.setExp1(new Num(exp1));
		this.setExp2(new Num(exp2));
	}
	/**
     * constructor BinaryExpression - construct the members string and double.
     * @param ex1 is the first string member.
     * @param ex2 is the second double member.
     */
	public BinaryExpression(String exp1, double exp2) {
		this.setExp1(new Var(exp1));
		this.setExp2(new Num(exp2));
	}
	/**
     * constructor BinaryExpression - construct the members double and string.
     * @param ex1 is the first double member.
     * @param ex2 is the second string member.
     */
	public BinaryExpression(double exp1, String exp2) {
		this.setExp1(new Num(exp1));
		this.setExp2(new Var(exp2));
	}
	/**
     * the function calculate two numbers according to inherited function.
     * @param number1 - first value to calculate.
     * @param number2 - second value to calculate.
     * @throws Exception check the validity of the values.
     * @return calculate between the two numbers.
     */
	public abstract double calculate(double number1, double number2)
			throws Exception;
    /**
     * the function returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
	public List<String> getVariables() {
		List<String> listVariabels = new LinkedList<String>();
		listVariabels.addAll(this.ex1.getVariables());
		listVariabels.addAll(this.ex2.getVariables());
		return listVariabels;
	}
    /**
     * the function evaluates the expression by using the variable values
     * and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown. 
     * @param assignment - map with variables.
     * @throws Exception if the variable illegal.
     * @return the number value.
     */
	public double evaluate(Map<String, Double> asignment) throws Exception {
        setMap(asignment);
		double calcAsignment = calculate(this.getExp1().evaluate(asignment),
				this.getExp2().evaluate(asignment));
		return calcAsignment;
	}
    /**
     * the function returns a new expression in which all occurrences of the variables
     * are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var - variable in the expression.
     * @param expression - expression to assigning.
     * @return a new expression in which all occurrences of the variables
     * are replaced with the provided expression.
     */
	public abstract Expression assign(String var, Expression expression);
	/**
     * the function sets the number value.
     * @param exp1 is the value.
     */
	protected void setExp1(Expression exp1) {
		this.ex1 = exp1;
	}
	/**
     * the function sets the number value.
     * @param exp2 is the value.
     */
	protected void setExp2(Expression exp2) {
		this.ex2 = exp2;
	}
	/**
     * the function return the value of the number.
     * @return the value of the number.
     */
	protected Expression getExp1() {
		return this.ex1;
	}
	/**
     * the function return the value of the number.
     * @return the value of the number
     */
	protected Expression getExp2() {
		return this.ex2;
	}
}