package calc.pckg;

class CalculatorRestrictions 
{

	static boolean inputRestrictions(String stackPeek, String beforePeek) 
	{
		// The order for these conditions, is critical
		// the ones that are specific, have to be on top
		// the ones that are not specific, eg. !isNumeric MUST be LOWER
		// Specifics

		if (Verify.isNull(beforePeek) && Verify.isNumeric(stackPeek))                // null 1
			return true;
		else if (Verify.isNull(beforePeek) && Verify.isOpenBracket(stackPeek))       // null (
			return true;
		else if (Verify.isNull(beforePeek) && Verify.isCloseBracket(stackPeek))      // null )
			return false;
		else if (Verify.isNull(beforePeek) && !Verify.isNumeric(stackPeek))          // null +
			return false;
		else if (Verify.isNumeric(beforePeek) && Verify.isNumeric(stackPeek))         // 1 1
			return true;
		else if (Verify.isNumeric(beforePeek) && Verify.isCloseBracket(stackPeek))    // 1 )
			return true;
		else if (Verify.isNumeric(beforePeek) && Verify.isOpenBracket(stackPeek))     // 1 (
			return false;
		else if (Verify.isOpenBracket(beforePeek) && Verify.isCloseBracket(stackPeek))// ( )
			return false;
		else if (Verify.isCloseBracket(beforePeek) && Verify.isOpenBracket(stackPeek))// ) (
			return false;
		else if (Verify.isCloseBracket(beforePeek) && Verify.isNumeric(stackPeek))    // ) 1
			return false;
		else if (beforePeek.equals("/") && stackPeek.equals("0"))                     // / 0
			return false;
		else if (Verify.isOpenBracket(beforePeek) && Verify.isNumeric(stackPeek))     // ( 1
			return true;
		else if (Verify.isOpenBracket(beforePeek) && Verify.isOpenBracket(stackPeek)) // ( (
			return true;
		else if (Verify.isCloseBracket(beforePeek) && Verify.isCloseBracket(stackPeek))// ) )
			return true;

		// non Specifics
		else if (Verify.isCloseBracket(beforePeek) && !Verify.isNumeric(stackPeek))    // ) +
			return true;
		else if (Verify.isNumeric(beforePeek) && !Verify.isNumeric(stackPeek))         // 1 +
			return true;
		else if (!Verify.isNumeric(beforePeek) && Verify.isOpenBracket(stackPeek))     // + (
			return true;
		else if (!Verify.isNumeric(beforePeek) && Verify.isNumeric(stackPeek))         // + 1
			return true;
		else if (!Verify.isNumeric(beforePeek) && Verify.isCloseBracket(stackPeek))    // + )
			return false;
		else if (Verify.isOpenBracket(beforePeek) && !Verify.isNumeric(stackPeek))     // ( +
			return false;
		else if (!Verify.isNumeric(beforePeek) && !Verify.isNumeric(stackPeek))        // + +
			return false;
		return false;
		// should add more conditions
		// with 3 parameters eg. 1.1. or
	}
}
