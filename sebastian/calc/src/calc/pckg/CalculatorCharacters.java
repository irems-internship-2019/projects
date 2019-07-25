package calc.pckg;

public enum CalculatorCharacters {

	ADDITION("+"),
	SUBSTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/");
	
	private final String CalculatorCharactersName;
	
	CalculatorCharacters(final String name)
	{
		this.CalculatorCharactersName = name;
	}
	public String getName() 
	{
		return CalculatorCharactersName;
	}
}
