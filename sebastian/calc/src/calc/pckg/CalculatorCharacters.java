package calc.pckg;

public enum CalculatorCharacters {

	ADDITION("+"),
	SUBSTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/");
	
	private final String calculatorCharactersName;
	
	CalculatorCharacters(final String name)
	{
		this.calculatorCharactersName = name;
	}
	public String getName() 
	{
		return calculatorCharactersName;
	}
}
