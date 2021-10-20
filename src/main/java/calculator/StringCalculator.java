package calculator;

import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator {
	static int count = 0;
	static int sum;
    public int add(String numbers) {
    	synchronized(this)
		{
			count++;
		}
		
		if(numbers.isEmpty())
			return 0;
		else
		{
			String[] nums = tokenize(numbers);
			int l = nums.length;
			Vector<Integer> negatives = new Vector<>();
			Vector<Integer> nos = new Vector<>();
			String q = "";
			int e =0;
			int sum = 0;
			
			for(int i =0; i<l; i++)
			{
			
					Matcher mx = Pattern.compile("^(\\+|-)?\\d+$").matcher(nums[i]);
					while(mx.find())
					{	
						q = mx.group();
						e = Integer.parseInt(q);
						
						if(e<0)
						{
							negatives.add(e);
						}
						if(e>1000)
							e=0;
							
						nos.add(e);
						sum +=e;
					 }
			}
			
			if(negatives.size() > 0)
				throw new RuntimeException("Negatives not allowed: " + negatives);
			
			return sum;
		}
	}
	
    private static String[] tokenize(String str)
   	{

   		if(str.startsWith("//"))
   		{
   			if(str.startsWith("//["))
   				return splitMulCustomDelWithAnyLength(str);
   			else
   				return splitCustomDelimeter(str);	
   		}
   		
   			else
   			{
   				return splitNewlineAndCommas(str);	
   			}
   	}
		
    private static String[] splitMulCustomDelWithAnyLength(String str)
   	{
   		Matcher m = Pattern.compile("//(\\[.+\\])+\n(.*)").matcher(str);
   		m.matches();
   		String del = m.group(1);
   		String delimeters = new String();
   		
   		int l = del.length(),last =0;
   		for(int i =0; i<l ; i++)
   		{
   			if(del.charAt(i) == ']' && i != l-1)
   			{	
   				delimeters += del.substring(last,i);
   				delimeters += "]|";
   				last = i+1;
   				
   			}
   			
   			else if(i == l-1)
   				delimeters += del.substring(last,i) + "]";
   		}
   		String nums = m.group(2);
   		
   		return nums.split(delimeters); 
   		
   		
   	}
       
    private static String[] splitCustomDelimeter(String str)
   	{
   		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(str);
   		m.matches();
   		String delimeter = m.group(1);
   		String nums = m.group(2);
   		return nums.split(delimeter);
   	}
    
    private static String[] splitNewlineAndCommas(String str)
   	{
   		String[] nums = str.split(",|\n");
   		return nums;
   	}
    public static int getCallCount()
	{
		return count;
	}
    
    public static int sum()
    {
    	String s= "+,1,2,3";
    	System.out.println(s);
    	
    	String[] strArray=s.split("");
    	System.out.println(Arrays.toString(strArray));
    	
    	if(strArray[0]=="+")
    	{
    		int sum=0;
    		for(int i=2;i<strArray.length;i=i+2)
    		{
    			sum=sum+Integer.parseInt((strArray[i])+1);
    		}
    	}   
		return sum;
    	
    	
    	
    }
}