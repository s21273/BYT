package b_Money;

import java.text.NumberFormat;

public class Main {
	public static void main(String[] args ) {
		Integer amount = 40000;
		Double rate =1.5;
		Integer aaa = 23790;
		Double ccc = 1.566;
		NumberFormat nf =  NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
			Double a = (double) aaa/100;
			Double d = a*ccc;
			String s = nf.format(d).replace(",", "");
			System.out.print(s);
			//Integer i = Integer.parseInt(s);
		
		
		
		
		
	}	
}
