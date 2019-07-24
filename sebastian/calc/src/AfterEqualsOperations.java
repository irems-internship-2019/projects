import java.util.Stack;

public class AfterEqualsOperations {
	static Stack<String> multipleDigitStack = new Stack<>();

	public static String[] ComplexNumbers(Stack<String> exp, int count) 
    {
		exp.remove(0);
     	exp.push(null);
		String[] infix = new String[count];
		int j = 0;
		for(int i = 0; i < count; i++) {
			if(ToCalculate.isNumeric(exp.get(i)))
			 {
					 multipleDigitStack.push(exp.get(i));
			 }else {
				 if(!multipleDigitStack.isEmpty()) {
		    	   infix[j] = multipleDigitStack.toString().replace(",", "")
		    			                                   .replace(" ","")
		    			                                   .replace("[","")
		    			                                   .replace("]","");
		    	   j++; i--;
		    	   multipleDigitStack.clear();
				 }
				 else 
				 {
					 infix[j] = exp.get(i); 
					 j++;
					 
				 }
				 
			 }
		}
		return infix;
    }
	
	
	public static String[] RemoveNull(String[] exp, int count) 
    {
		int numberOfcharacters = 0;
		 for(String str : exp) 
		 {
			if(str != null && !str.isEmpty()) {numberOfcharacters++;}
		 }
		 
		 //method to remove all null values
		 
		 String[] infixFinal = new String[numberOfcharacters];
		 int FinalCount = 0;
		 for(int i = 0; i < count; i++) 
		 {
			if(exp[i] != null && !exp[i].isEmpty()) 
			{
				infixFinal[FinalCount] = exp[i];
				FinalCount++;
			}
		 }
		return infixFinal;
    }

}
