package calc.pckg;

class CalculatorRestrictions extends Verify
{

	boolean inputRestrictions(String stackPeek, String beforePeek) 
	{
		// The order for these conditions, is critical
		// the ones that are specific, have to be on top
		// the ones that are not specific, eg. !isNumeric MUST be LOWER

		// Specifics
		if (isNull(beforePeek) && isNumeric(stackPeek))                // null 1
			return true;
		else if (isNull(beforePeek) && isOpenBracket(stackPeek))       // null (
			return true;
		else if (isNull(beforePeek) && isCloseBracket(stackPeek))      // null )
			return false;
		else if (isNull(beforePeek) && !isNumeric(stackPeek))          // null +
			return false;
		else if (isNumeric(beforePeek) && isNumeric(stackPeek))         // 1 1
			return true;
		else if (isNumeric(beforePeek) && isCloseBracket(stackPeek))    // 1 )
			return true;
		else if (isNumeric(beforePeek) && isOpenBracket(stackPeek))     // 1 (
			return false;
		else if (isOpenBracket(beforePeek) && isCloseBracket(stackPeek))// ( )
			return false;
		else if (isCloseBracket(beforePeek) && isOpenBracket(stackPeek))// ) (
			return false;
		else if (isCloseBracket(beforePeek) && isNumeric(stackPeek))    // ) 1
			return false;
		else if (beforePeek.equals("/") && stackPeek.equals("0"))                     // / 0
			return false;
		else if (isOpenBracket(beforePeek) && isNumeric(stackPeek))     // ( 1
			return true;
		else if (isOpenBracket(beforePeek) && isOpenBracket(stackPeek)) // ( (
			return true;
		else if (isCloseBracket(beforePeek) && isCloseBracket(stackPeek))// ) )
			return true;

		// non Specifics
		else if (isCloseBracket(beforePeek) && !isNumeric(stackPeek))    // ) +
			return true;
		else if (isNumeric(beforePeek) && !isNumeric(stackPeek))         // 1 +
			return true;
		else if (!isNumeric(beforePeek) && isOpenBracket(stackPeek))     // + (
			return true;
		else if (!isNumeric(beforePeek) && isNumeric(stackPeek))         // + 1
			return true;
		else if (!isNumeric(beforePeek) && isCloseBracket(stackPeek))    // + )
			return false;
		else if (isOpenBracket(beforePeek) && !isNumeric(stackPeek))     // ( +
			return false;
		else if (!isNumeric(beforePeek) && !isNumeric(stackPeek))        // + +
			return false;
		return false;

		// should add more conditions
		// with 3 parameters eg. 1.1. or
	}
}
