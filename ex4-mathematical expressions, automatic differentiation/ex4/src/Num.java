import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Num implements Expression{
	private double num;

	public Num(double newNum) {
		this.num = newNum;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
	}

	public double evaluate() throws Exception {
		return this.num;
	}

	public List<String> getVariables() {
		return new ArrayList<String>();
	}

	public String toString() {
		String str = null;
		return str.valueOf(this.num);
	}

	public Expression assign(String var, Expression expression) {
	    return new Num(this.num);
	}
	public Expression differentiate(String var) {
	    return new Num(0);

	}
    public Expression simplify() {
        return new Num(this.num);
    }

}
