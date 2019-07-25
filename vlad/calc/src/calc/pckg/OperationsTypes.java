package calc.pckg;

public enum OperationsTypes 
{
	ADDITION("addition"),
	SUBSTRACTION("substraction"), 
	MULTIPLICATION("multiplication"),
	DIVISION("division");
	
	private final String operationTypeName;
	
	OperationsTypes(final String name)
	{
		this.operationTypeName = name;
	}
	
	public String getName()
	{
		return operationTypeName;
	}
}
