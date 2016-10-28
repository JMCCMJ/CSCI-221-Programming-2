import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FilteredIteratorImpl extends FilteredIterator{
	String final_words = "";
	
	

	public FilteredIteratorImpl(Iterator baseIterator, List filterValue) {
		super(baseIterator, filterValue);
		ArrayList finals = null;
		
		
		
		
		while(this.hasNext())
		{
			String word = (String) baseIterator.next();
			String noPunct = word.replaceAll("[\\W]", "");
//			System.out.println(noPunct);
//			System.out.println(word);
			boolean noMatch = true;
			
			String temp = "";
			for( int j=0; j<filterValue.size(); j++ )
			{
				//String filter = (String) filterValue.get(j);
				if(noPunct.equalsIgnoreCase((String) filterValue.get(j))){
//					System.out.println("YES");
					//temp = word.replaceAll(filter, "");
					
					//temp = word.replace(filter, "");
					if(word.length()>noPunct.length()){
						String tempPlus = "" + word.charAt(word.length()-1);
//						System.out.println(tempPlus);
						final_words=final_words+tempPlus+" ";
						
						noMatch=false;
//						System.out.println("HAS PUNCT");
						//finals.add(tempPlus);
					}
					else{
//						System.out.println(temp);
						//final_words=final_words+" ";
						noMatch=false;
						
					}
					
//					System.out.println(temp);
				}
				
				
				//System.out.println(filter);
				//System.out.println(word);
			}
			if(noMatch){
				final_words=final_words+word+" ";
			}
//			System.out.println(word);
			
			
		}
		//System.out.println(final_words);
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	public String toString()
	{
		return final_words;
	}
	
	
	@Override
	public boolean hasNext() {
		if(baseIterator.hasNext()==true){
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		baseIterator.next();
		return null;
	}

	@Override
	public void remove() {
		//I did not need this method
		baseIterator.remove();
		// TODO Auto-generated method stub
		
	}

}
