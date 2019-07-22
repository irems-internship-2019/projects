

import java.util.Stack;

class ToCalculate {

	/*public static void main(String[] args) {
		  //String[] strArr = {"3" ,"10", "5", "+" ,"*"};
       
        System.out.println(PostFixCalculator(strArr));
    }*/

	public static double PostFixCalculator(String[] strArr) 
	{
        Stack<Double> operands = new Stack<Double>();

        for(String str : strArr) 
        {
        	if(str!= null)
            switch (str) 
            {
                case "+":
                case "-":
                case "*":
                case "/":
                    double right = operands.pop();
                    double left = operands.pop();
                    double value = 0;
                    switch(str) {
                    
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            value = left / right;
                            break;
                        default:
                            break;
                    }
                    operands.push(value);
                    break;
                default:
                    operands.push(Double.parseDouble(str));
                    break;  
            }
        }
        return operands.pop();
    }
	
	
	public static boolean isNumeric(String strNum) {
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	 static int Priority(String ch) 
	    { 
	        switch (ch) 
	        { 
	        case "+": 
	        case "-": 
	            return 1; 
	       
	        case "*": 
	        case "/": 
	            return 2; 
	        } 
	        return -1; 
	    } 
	       
	 // The main method that converts given infix expression 
	    // to postfix expression.  
	    public static String[] InfixToPostfixConverter(String[] exp) 
	    { 
	    	int i = 0;
	        // initializing empty String for result 
	        //String[] result;
	         String[] result = new String[25];

	          //String[] result = null;

	          
	        // initializing empty stack 
	        Stack<String> stack = new Stack<>(); 
	          
	        for(String str : exp) 
	        {
	        	 // If the scanned string is an operand, add it to output. 
	            if (isNumeric(str)) { 
	                result[i] = str; 
	                i++;
	            }
	         // If the scanned string is an '(', push it to the stack. 
	            else if (str.equals("(")) {
	                stack.push(str); 
	            }
	            //  If the scanned String is an ')', pop and output from the stack  
	            // until an '(' is encountered. 
	            else if (str.equals(")")) 
	            { 
	                while (!stack.isEmpty() && !stack.peek().equals("(")) 
	                {
	                	result[i] = stack.pop(); 
		                i++;
	                }
	                	
	                if (!stack.isEmpty() && !stack.peek().equals("(")) 
	                    return null; // invalid expression                 
	                else
	                    stack.pop(); 	            
	            }
	            else // an operator is encountered 
	            { 
	                while (!stack.isEmpty() && Priority(str) <= Priority(stack.peek()))
	                { 
	                	
	                    if(stack.peek().equals("(")) 
	                    {
	                        return null; 
	                    }
	                    else 
	                    {
	                    	result[i] = stack.pop(); 
	                    	i++;
	                    }
	                } 
	                stack.push(str); 
	            } 
	            
	        }
	        
	       
	        // pop all the operators from the stack 
	        while (!stack.isEmpty()){ 
	        	
	            if(stack.peek().equals("("))
	            { 
	            	return null; 
	            }
	            else
	            {
	            	result[i] = stack.pop(); 
                	i++;
	            }
	         } 
	        return result; 
	    } 
	    
    
	
}


