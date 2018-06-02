import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression{

	private Expression exp;

	public UnaryExpression(Expression exp) {
		this.setExp(exp);
	}

	public UnaryExpression(double exp) {
		this.setExp(new Num(exp));
	}
	public UnaryExpression(String exp) {
		this.setExp(new Var(exp));
	}

	public abstract double calculate(double number)
			throws Exception;

	public List<String> getVariables() {
		List<String> listVariabels = new LinkedList<String>();
		listVariabels.addAll(this.exp.getVariables());
		return listVariabels;
	}

	public double evaluate(Map<String, Double> asignment) throws Exception {
		double calcAsignment = calculate(this.exp.evaluate(asignment));
		return calcAsignment;
	}

	public abstract Expression assign(String var, Expression expression);

	protected void setExp(Expression newExp) {
		this.exp = newExp;
	}

	protected Expression getExp() {
		return this.exp;
	}
    public abstract Expression makeExp(Expression newExp);
    
	public Expression simplify() {
		Expression exp = this.getExp().simplify();
			if (exp.getVariables().isEmpty()) {
				try {
					double num = this.calculate(exp.evaluate());
					return new Num(num);
				} catch (Exception e1) {
					System.out.println("Error");
				}	
			}
			return makeExp(exp);
	}

}
