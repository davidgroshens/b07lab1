import java.lang.Math;
import java.util.Arrays;

public class Polynomial {
	
	double[] coef;
	
	public Polynomial() {
		coef = new double[]{0}; // set array to 0
	}
	
	public Polynomial(double[] arr) {
		coef = arr;
	}
	
	public Polynomial add(Polynomial a) {
		
		Polynomial arr = new Polynomial();
		
		if (a.coef.length < coef.length) {
			arr.coef = new double[coef.length];
			double[] new_a = Arrays.copyOf(a.coef, coef.length);
			Arrays.setAll(arr.coef, i -> new_a[i] + coef[i]);
			
		}
			
		if (a.coef.length > coef.length) {
			arr.coef = new double[a.coef.length];
			double[] new_coef = Arrays.copyOf(coef, a.coef.length);
			Arrays.setAll(arr.coef, i -> new_coef[i] + a.coef[i]);	
				
		}
		
		else {
			arr.coef = new double[a.coef.length];
			Arrays.setAll(arr.coef, i -> coef[i] + a.coef[i]);
			
		}
		 
		return arr;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coef.length; i++) {
			result = result + coef[i] * Math.pow(x, i);
		}
		
		return result;	
	}
	
	public boolean hasRoot(double x) {
		if (evaluate(x) == 0) {
			return true;
			
		}
		
		else {
			return false;
			
		}
		
	}

}

	
		