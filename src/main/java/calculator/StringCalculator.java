package calculator;

class StringCalculator {
	static int count = 0;
	
    public int add(String numbers) {
    	synchronized(this)
		{
			count++;
		}
		
		if(numbers.isEmpty())
			return 0;
		return 0;
		

}}