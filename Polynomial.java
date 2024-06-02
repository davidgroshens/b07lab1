import java.lang.Math;
import java.io.*;
import java.nio.charset.*;

public class Polynomial {
	//fields
	double[] nzcoef;
	int[] exp;
	
	//constructor methods
	public Polynomial() {
		nzcoef = new double[]{0}; //set array to 0
		exp = new int[]{0}; //set array to 0
	}
	public Polynomial(double[] nz, int[] e) {
		int size = e.length;
		nzcoef = new double[size];
		exp = new int[size];
		for (int i = 0; i < size; i++) {
			nzcoef[i] = nz[i];
			exp[i] = e[i]; 
		}
		
	}
	public Polynomial(File file){
		
		try {
			BufferedReader content = new BufferedReader(new FileReader(file));
			String s_content = content.readLine();
			content.close();
			
			double[] r_nzcoef = new double[s_content.length()];
			int[] r_exp = new int[s_content.length()];
			for (int i = 0; i<r_exp.length; i++){
				r_exp[i] = -999;
			}
			for (int i = 0; i<r_nzcoef.length; i++){
				r_nzcoef[i] = -999;
			}
			int counter = 0;
			boolean isx = false;
			char[] op = new char[s_content.length()];
			int opCounter = 0;
			
			for (int i = 0; i<s_content.length(); i++){
				if (s_content.charAt(i) == '+' || s_content.charAt(i) == '-'){
					op[opCounter] = s_content.charAt(i);
					opCounter++;
				}
			}
			
			String numcoef = "";
			String numexp = "";
			
			int fi = 0;
			boolean isf = false;
			
			
			String[] arr = s_content.split("[+-]", s_content.length());
			
			
			for (int i = 0; i<arr.length; i++){
				if (arr[i] == ""){
					isf = true;
					continue;
					
					
				}
				for (int j = 0; j<arr[i].length(); j++){
					if (isf){
						fi--;
						isf = false;
					}
					if (isx){
						numexp += arr[i].charAt(j);
					}
					else if ( arr[i].charAt(j) != 'x'){
						numcoef += arr[i].charAt(j);
					}
					else if (arr[i].charAt(j) == 'x'){
						isx = true;
					}
					
				}
				fi++;
				
				if (numcoef != ""){
					r_nzcoef[fi] = Double.parseDouble(numcoef);
					if(i>0 && op[i-1] == '-'){
						r_nzcoef[fi] = r_nzcoef[fi]*(-1);
					}
				}
				else{
					r_nzcoef[fi] = 1;
				}
				if (numexp != ""){
					r_exp[fi] = Integer.parseInt(numexp);
				}
				else if(isx) {
					r_exp[fi] = 1;
				}
				else {
					r_exp[fi] = 0;
				}
				numcoef = "";
				numexp = "";
				isx = false;
				
			}
			int c = 0;
			for (int i = 0; i<r_exp.length; i++){
				if (r_exp[i] != -999){
					c++;
				}
			}
			
			exp = new int[c];
			nzcoef = new double[c];
			
			for (int i = 0; i<c; i++){
				exp[i] = r_exp[i];
				nzcoef[i] = r_nzcoef[i];
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	//methods
	
	public void saveToFile(String str){
		String result = "";
		for (int i = 0; i < exp.length; i++){
			if (i == 0){
				result+=String.valueOf(nzcoef[i]);
			}
			else{
				if (Double.toString(nzcoef[i]).charAt(0) != '-'){
					result+='+';
					result+=String.valueOf(nzcoef[i]);
				}
				else{
					result+=String.valueOf(nzcoef[i]);
				}
			}
			if (exp[i]!=0){
				result+="x";
				if (exp[i]!=1){
					result+=String.valueOf(exp[i]);
				}
			}
		}
		
		try{
			PrintWriter out = new PrintWriter(str);
			out.println(result);
			out.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	public Polynomial add(Polynomial a) {
		Polynomial result = new Polynomial();
		int size_a = a.exp.length;
		int size_call = exp.length;
		int[] r_exp = new int[size_a + size_call];
		double[] r_nzcoef = new double[size_a + size_call];
		
		for (int i = 0; i < r_exp.length; i++) 
        {
            r_exp[i] = -1;
        }
		int counter = 0;
		boolean inArr = false;
		
		
		//adding both arrays with no duplicates
		for (int i = 0; i<size_a; i++){
			r_exp[i] = a.exp[i];
			r_nzcoef[i] = a.nzcoef[i];
			counter++;
		}
		for (int i = counter; i < r_exp.length; i++){
			for (int j = 0; j < r_exp.length; j++){
				if (exp[i-size_a] == r_exp[j]){
					inArr = true;
					r_nzcoef[j] += nzcoef[i-size_a];
				}
			}
			if (!inArr){
				r_exp[counter] = exp[i-size_a];
				r_nzcoef[counter] = nzcoef[i-size_a];
				counter++;
			}
			inArr = false;
			
		}
		// putting in array of correct length
		result.exp = new int[counter];
		result.nzcoef = new double[counter];
		for (int i = 0; i < counter; i++){
			result.exp[i] = r_exp[i];
			result.nzcoef[i] = r_nzcoef[i];
		}
		//bubble sort to sort exp and copy for nzcoef
		
		int temp;
		double temp2;
        boolean swapped;
        for (int i = 0; i < counter - 1; i++) {
            swapped = false;
            for (int j = 0; j < counter - i - 1; j++) {
                if (result.exp[j] > result.exp[j + 1]) {
                    
                    // Swap arr[j] and arr[j+1]
                    temp = result.exp[j];
                    result.exp[j] = result.exp[j + 1];
                    result.exp[j + 1] = temp;
					
					temp2 = result.nzcoef[j];
					result.nzcoef[j] = result.nzcoef[j+1];
					result.nzcoef[j+1] = temp2;
					
                    swapped = true;
                }
            }
            if (swapped == false){
                break;
			}
			
		}
		/*
		for (int i = 0; i<result.exp.length; i++){
			System.out.println(result.exp[i]);
		}
		for (int i = 0; i<result.nzcoef.length; i++){
			System.out.println(result.nzcoef[i]);
		}
		*/
		
		return result;	
	}
	
	public Polynomial multiply(Polynomial a) {
		double[] c1 = new double[1];
		int[] e1 = new int[1];
		
		Polynomial result = new Polynomial();
		Polynomial p1 = new Polynomial(c1, e1);
		
		
		for (int i = 0; i < a.exp.length; i++){
			for (int j = 0; j < exp.length; j++){
				p1.exp[0] = exp[i] + a.exp[j];
				p1.nzcoef[0] = nzcoef[i] * a.nzcoef[j];
				result = result.add(p1);
				
			}
			
		}
		
		//remove 0 from arrays
		int counter = result.exp.length;
		
		for (int i = 0; i<result.exp.length; i++){
			if (result.nzcoef[i] == 0){
				counter--;
			}
		}
		
		int[] r_exp = new int[counter];
		double[] r_nzcoef = new double[counter];
		int i1 = 0;
		int j1 = 0;
		
		while (j1 < result.exp.length){
			if (result.nzcoef[j1] != 0){
				r_exp[i1] = result.exp[j1];
				r_nzcoef[i1] = result.nzcoef[j1];
				i1++;
			}
			j1++;
		}
		result.exp = new int[counter];
		result.nzcoef = new double[counter];
		
		for (int i = 0; i < counter; i++){
			result.exp[i] = r_exp[i];
			result.nzcoef[i] = r_nzcoef[i];
		}
		
		/*
		for (int i = 0; i<result.exp.length; i++){
			System.out.println(result.exp[i]);
		}
		for (int i = 0; i<result.nzcoef.length; i++){
			System.out.println(result.nzcoef[i]);
		}
		*/
		
		return result;
		
		
		
	}
	
	
	public double evaluate(double x) {
		double result = 0;
		int size = nzcoef.length;
		for (int i = 0; i < size; i++){
			result += nzcoef[i] * Math.pow(x, exp[i]);   
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