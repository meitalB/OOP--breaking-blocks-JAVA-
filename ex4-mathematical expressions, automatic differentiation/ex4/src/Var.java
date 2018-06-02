import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
	private String var;

	public Var(String newVar) {
		this.var = newVar;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		if (assignment.containsKey(this.var)) {
			return assignment.get(this.var);
		} else {
			throw new Exception("No value in the Map");
		}
	}

	public double evaluate() throws Exception {
		throw new Exception("No value in the Map");
	}

	public List<String> getVariables() {
		List<String> listVariabels = new LinkedList<String>();
		listVariabels.add(this.var);
		return listVariabels;
	}

	public String toString() {
		return this.var;
	}

	public Expression assign(String var, Expression expression) {
		if (this.var.equals(var)) {
			return expression;
		} else {
			return new Var(this.var);
		}
	}

	public Expression differentiate(String var) {
		if (this.var.equals(var)) {
			return new Num(1);
		}
		return new Num(0);
	}

	public Expression simplify() {
		return new Var(this.var);
	}

}
