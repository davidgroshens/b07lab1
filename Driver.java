import java.io.*;

public class Driver {
	public static void main(String [] args) {
		
		
		double[] c1 = {1,2,3};
		double[] c2 = {-3,2,-1};
		int[] exp1 = {0,1,2};
		int[] exp2 = {1,2,0};
		File file = new File("README.md");
		Polynomial p1 = new Polynomial(c1, exp1);
		Polynomial p2 = new Polynomial(c2, exp2);
		Polynomial p3 = new Polynomial(file);
		
		//add test
		Polynomial s = p1.add(p2);
		
		//evaluat test
		Polynomial t = p1.multiply(p2);
		
		p2.saveToFile("t.txt");
		
		
		
		
		
		
		
		
		
		/*
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		*/
	}
}