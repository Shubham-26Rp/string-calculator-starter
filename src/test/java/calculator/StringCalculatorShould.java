package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorShould {


	StringCalculator stringcalculator = new StringCalculator();

	@Test
	public void returnZeroOnEmptyString()
	{
		assertEquals(0, stringcalculator.add(""));
	}
	
	@Test
	public void returnNumOnSingleArg()
	{
		assertEquals(1, stringcalculator.add("1"));
	}
	
	@Test
	public void returnSumOnTwoArgsDelimitedByComma()
	{
		assertEquals(3, stringcalculator.add("1,2"));
	}
	
	@Test
	public void returnSumOnMultipleArgsDelimitedByComma()
	{
		assertEquals(6, stringcalculator.add("1,2,3"));
	}

	@Test
	public void acceptNewlineAsValidDelimiter()
	{
		assertEquals(6, stringcalculator.add("1\n2,3"));
	}
	
	@Test
	public void customDelimeter()
	{
		assertEquals(3, stringcalculator.add("//;\n1;2"));
	}
	
	@Test
	public void raiseExceptionOnNegativeNumbers()
	{
		try {
			stringcalculator.add("-1,-2,3");
			fail("Exception expected.");
		}
		
		catch(RuntimeException e)
		{
			assertEquals("Negatives not allowed: [-1, -2]", e.getMessage());
		}
	}
	
	  @Test
		public void validateCallCount()
		{
			assertEquals(StringCalculator.getCallCount(), StringCalculator.count);
		}
	  
	    @Test
		public void ignoreNumsGrtThn1000()
		{
			assertEquals(2, stringcalculator.add("2,1001"));
		}
}
