package calc.pckg;

import java.util.Stack;

class StackOperations {

	private static Stack<String> inputStack = new Stack<>();

	static void  pushStack(String strNum)
	{
		inputStack.push(strNum);
	}

	static void  addToStack(int i, String strNum)
	{
		inputStack.add(i, strNum);
	}

	static String popStack()
	{
		return inputStack.pop();
	}

	static void  clearStack()
	{
		inputStack.clear();
	}

	static String getStack(int i) 
	{
		return inputStack.get(i);
	}

	static int stackSize() 
	{
		return inputStack.size();
	}

	static String stackPeek() 
	{
		return inputStack.peek();
	}

	static boolean isEmpty() 
	{
		return inputStack.isEmpty();
	}

	static boolean isEqual(int i, String strNum) 
	{
		return inputStack.get(i).equals(strNum);
	}

	static void removeFromStack(int i) 
	{
		inputStack.remove(i);
	}

}
