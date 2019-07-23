

import java.util.Arrays;
import java.util.Stack;

class ToCalculate {


	public static double PostFixCalculator(String[] strArr) 
	{
        Stack<Double> operands = new Stack<Double>();

        for(String str : strArr) 
        {
        	//The loop will run untill it finds a null, because of a "(" or ")" character
        	if(str!= null)
            switch (str) 
            {
            //If there is one of these characters ID'd, the loop will pop last 2 operators
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
                    //after the case break the value of the new operand will be added to the LIFO stack
                    operands.push(value);
                    break;
                default:
                	//if no case is triggered than the value, of the operand will pe addes to the stack
                    operands.push(Double.parseDouble(str));
                    break;  
            }
        }
        //returns the result in a Double form
        return operands.pop();
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
	    public static String[] InfixToPostfixConverter(String[] exp, int count) 
	    { 
	    	int i = 0;
	        // initializing empty String for result 
	         String[] result = new String[count];

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
	        
	       
	        // pop all the operators from the stack aka the higher priority operands
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
	    
	    // if the String is "("
	    public static boolean isOpenB(String strNum) 
	    {
	    	if(strNum.equals("("))
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    
	  // if the String is ")"
	    public static boolean isCloseB(String strNum) 
	    {
	    	if(strNum.equals(")"))
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    
	    
	    public static boolean isNumeric(String strNum) {
		    try {
		        Double.parseDouble(strNum);
		    } catch (NumberFormatException | NullPointerException nfe) {
		        return false;
		    }
		    return true;
		}
	    
	    public static boolean Verify(String right, String left) {
	    
	    	//The order if these conditions is critical
	    	//the ones that are specific, have to be on top
	    	//the ones that are not specific, eg. !isNumeric MUST be LOWER 
	    	//Specifics
	    	if(left.equals("dorel")){return true;}                        //init stack
	    	else if(isNumeric(left) && isNumeric(right)) {return true;}   // 1  1
	    	else if(isNumeric(left) && isCloseB(right)){return true;}     // 1  )
	    	else if(isNumeric(left) && isOpenB(right)){return false;}     // 1  (
	    	else if(isOpenB(left) && isCloseB(right)){return false;}      // (  )
	    	else if(isCloseB(left) && isOpenB(right)){return false;}      // )  (
	    	else if(isCloseB(left) && isNumeric(right)){return false;}    // )  1
	    	else if(left.equals("/") && right.equals("0")){return false;} // /  0
	    	else if(isOpenB(left) && isNumeric(right)){return true;}      // (  1
	    	else if(isOpenB(left) && isOpenB(right)){return true;}        // (  (
	    	else if(isCloseB(left) && isCloseB(right)){return true;}      // )  )
	    	//add for .(   .+ 
	    	
	    	//non Specifics
	    	else if(isCloseB(left) && !isNumeric(right)){return true;}    // )  +
	    	else if(isNumeric(left) && !isNumeric(right)){return true;}   // 1  +
	    	else if(!isNumeric(left) && isOpenB(right)){return true;}     // +  (
	    	else if(!isNumeric(left) && isNumeric(right)){return true;}   // +  1
	    	else if(!isNumeric(left) && isCloseB(right)){return false;}   // +  )
	    	else if(isOpenB(left) && !isNumeric(right)){return false;}    // (  +
	    	else if(!isNumeric(left) && !isNumeric(right)){return false;} // +  +
		    return false;
		}
    
	    //should add more conditions
	    //with 3 parameters eg. 1.1. or
	
	    
	    public static boolean VerifyIfDoubleDigit(String right, String left) 
	    {
	    	if(left.equals("dorel")){return false;} 
	    	else if(isNumeric(left) && isNumeric(right)) {return true;}
	    	return false;
	    }
	    public static boolean SomethingElse(String right, String left) {
	    	if(isNumeric(left) && !isNumeric(right)){return true;}   // 1  +
	    	else return false;
	    }
	        
	    
}




