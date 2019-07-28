package calc.pckg;
//STILL NEED TO BE DONE ########################################################################//after a calculation is done, reset Display IF, a "number" or "(" is pressed
//calculation is done IF you press "=" program dies - FIXED return ERROR...still need proper Verification
//if cases where 1.1. is inputed, display ERROR message - FIXED...still need proper Verification
//##############################################################################################


class Calculator 
{

	public static void main(String[] args) 
	{
		CreateCalculatorUi newWindow = new CreateCalculatorUi();
		newWindow.createNewWindow();
	}
}
