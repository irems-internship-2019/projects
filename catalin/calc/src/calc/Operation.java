package calc;

public class Operation extends ButtonClass {
	
	 static int plus_minus = 0;
	 static double divide=0;
	 static String result;
	  
	 public static String plus()
	 {
			 plus_minus = operators.get(0) + operators.get(1);
		     result = Integer.toString(plus_minus);
		     
		     return result;
		     
	 }
	 
	 public static String minus()
	 {
			 plus_minus = operators.get(0) - operators.get(1);
		     result = Integer.toString(plus_minus);
		        
		     return result;
	 }
	 
	 public static String multiple()
	 {
			 plus_minus = operators.get(0) * operators.get(1);
		     result = Integer.toString(plus_minus);
		        
		     return result;
	 }
	 
	 public static String divide()
	 {
			 divide = operators.get(0) / operators.get(1);
		     result = Double.toString(divide);
		        
		     return result;
	 }

}
