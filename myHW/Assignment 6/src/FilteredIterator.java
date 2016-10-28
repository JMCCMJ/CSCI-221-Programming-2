import java.util.*;

public abstract class FilteredIterator<E1, E2> implements Iterator<E1>
{
	protected Iterator<E1> baseIterator;
	protected List<E2> filterValue;
	
	public FilteredIterator( Iterator<E1> baseIterator, List<E2> filterValue )
	{
		this.baseIterator = baseIterator;
		this.filterValue = filterValue;
	}
	
	public abstract boolean hasNext();

	public abstract E1 next();
	
	public abstract void remove();
}

