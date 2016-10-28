import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FilteredIteratorDriver {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		List<String> filteredValues = new ArrayList<String>();
		
		strings.add("now");
		strings.add("is");
		strings.add("the");
		strings.add("time!");
		strings.add("is");
		
		filteredValues.add("is");
		filteredValues.add("the");
		
		Iterator<String> itr = strings.iterator();
		
		FilteredIterator<String, String> filteredIterator = new FilteredIteratorImpl(itr, filteredValues);
		
		while(filteredIterator.hasNext()){
			System.out.println(filteredIterator.next());
			filteredIterator.remove();
		}
		System.out.println(strings);
		
	}

}
