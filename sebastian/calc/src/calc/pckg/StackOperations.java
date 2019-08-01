package calc.pckg;

import java.util.Stack;

class StackOperations {

	private static Stack<String> inputStack = new Stack<>();

	void  pushStack(String strNum)
	{
		inputStack.push(strNum);
	}

	void  addToStack(int i, String strNum)
	{
		inputStack.add(i, strNum);
	}

	String popStack()
	{
		return inputStack.pop();
	}

	void  clearStack()
	{
		inputStack.clear();
	}

	String getStack(int i) 
	{
		return inputStack.get(i);
	}

	int stackSize() 
	{
		return inputStack.size();
	}

	String stackPeek() 
	{
		return inputStack.peek();
	}

	boolean isEmpty() 
	{
		return inputStack.isEmpty();
	}

	boolean isEqual(int i, String strNum) 
	{
		return inputStack.get(i).equals(strNum);
	}

	void removeFromStack(int i) 
	{
		inputStack.remove(i);
	}

}
