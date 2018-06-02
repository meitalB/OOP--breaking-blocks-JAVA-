import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class BaseExpression {
	private Map<String, Double> assignment;
	public static final double E = 2.71828;
	public static final double Pi = 3.14;

	public abstract List<String> getVariables();

	public abstract double evaluate(Map<String, Double> asignment)
			throws Exception;

	public double evaluate() throws Exception {
		return this.evaluate(this.assignment);
	}
    public void setMap(Map<String, Double> assign) {
        this.assignment = assign;
    }

	public abstract Expression assign(String var, Expression expression);

	public static void main(String[] args) {
		
		Expression e = new Mult(new Mult(new Var("x") , new Num(4)) , 
                new Mult(new Var("x") , new Num(20)));
        
        e = e.differentiate("x").simplify(); 
		System.out.println(e);


        // 2 * cos(x) * (-sin(x)) = -2 * sin(x) * cos(x) 


		// the result is: ((2*8)-6)^2 => 100
		// (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e,
		// (x + y)))))

	}

}
