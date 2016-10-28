import java.util.*;
public class Stack 
{
	private ArrayList<Object> stack = null;
	
	public Stack()
	{
		stack = new ArrayList<Object>();	
	}
	
	
	
	public boolean push( Object object )
	{
		return stack.add(object);
	}
	
	public Object popEnd()
	{
		Object rtnval = null;
		int index = 0;
		
		if( index >= 0 )
			rtnval = stack.remove(index);
		
		return rtnval;
	}
	
	public Object pop()
	{
		Object rtnval = null;
		int index = stack.size()-1;
		
		if( index >= 0 )
			rtnval = stack.remove(index);
		
		return rtnval;
	}
	
	public int size()
	{
		return stack.size();
	}
	
	public String toString()
	{
		String rtnval;
		StringBuffer strbuf = new StringBuffer();
		
		for( int i = stack.size()-1; i>=0; i-- )
		{
			strbuf.append(stack.get(i) + "\n");
		}
		
		rtnval = strbuf.toString();
		
		return rtnval;
	}
	
	public String toStringFix2()
	{
		String rtnval;
		StringBuffer strbuf = new StringBuffer();
		
		for( int i = stack.size()-1; i>=0; i-- )
		{
			strbuf.insert(0, stack.get(i) + "\n");
		}
		
		rtnval = strbuf.toString();
		
		return rtnval;
	}
	
	public String toStringFix()
	{
		String rtnval;
		StringBuffer strbuf = new StringBuffer();
		System.out.println(stack.size());
		for( int i = stack.size()-1; i>=0; i--)
		{
			//strbuf.append(stack.get(i) + "\n");
			strbuf.insert(0, stack.get(i) + "\n");
		}
		
		rtnval = strbuf.toString();
		
		return rtnval;
	}
	
	
	public static void main(String[] args) 
	{
		Stack myStack = new Stack();

		System.out.println( "calling pop which returns: " + myStack.pop());

		myStack.push("first push");
		myStack.push("second push");
		myStack.push("third push");
		
		while( myStack.size() > 0 )
			System.out.println( "calling pop which returns: " + myStack.pop());

	}

}
