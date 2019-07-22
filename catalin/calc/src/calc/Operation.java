package calc;

public class Operation extends ButtonClass {
	
	 static int plus_minus = 0;
	 static double divide=0;
	 static String result;
	  
	 public static String plus()
	 {
		 if(operation.get(0) == "+")
			 plus_minus = operators.get(0) + operators.get(1);
		     result = Integer.toString(plus_minus);
		     
		     return result;
		     
	 }
	 
	 public static String minus()
	 {
		 if(operation.get(0) == "-")
			 plus_minus = operators.get(0) - operators.get(1);
		     result = Integer.toString(plus_minus);
		        
		     return result;
	 }
	 

}
