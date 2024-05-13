import java.lang.Math;

public class Polynomial {
	//fields
	double[] coef;
	
	//constructor methods
	public Polynomial() {
		coef = new double[]{0}; //set array to 0;
		
	}
	public Polynomial(double[] a) {
		int size = a.length;
		coef = new double[size];
		for (int i = 0; i < size; i++) {
			coef[i] = a[i];
		}
		
	}
	
	//methods
	public Polynomial add(Polynomial a) {
		Polynomial result = new Polynomial();
		int size_a = a.coef.length;
		int size_call = coef.length;
		int counter = 0;
		if (size_a > size_call){
			result.coef = new double[size_a];
			while (counter < size_call){
				result.coef[counter] = coef[counter] + a.coef[counter];
				counter++;
			}
			for (int i = counter; i < size_a; i++){
				result.coef[i] = a.coef[i]; 
			}				
		}
		else {
			result.coef = new double[size_call];
			while (counter < size_a){
				result.coef[counter] = coef[counter] + a.coef[counter];
				counter++;
			}
			for (int i = counter; i < size_call; i++){
				result.coef[i] = coef[i]; 
			}
		}
		return result;	
	}
	
	public double evaluate(double x) {
		double result = 0;
		int size = coef.length;
		for (int i = 0; i < size; i++){
			result += coef[i] * Math.pow(x, i);   
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