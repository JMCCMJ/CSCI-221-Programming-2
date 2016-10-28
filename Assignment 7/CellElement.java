
public class CellElement 
{
	private int value;
	private boolean modifiable;
	private boolean selected;
	
	public CellElement( int value, boolean modifiable )
	{
		this.value = value;
		this.modifiable = modifiable;
		this.selected = false;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue( int value )
	{
		this.value = value;
	}
	
	public boolean isModifiable()
	{
		return modifiable;
	}
	
	public void setModifiable( boolean modifiable )
	{
		this.modifiable = modifiable;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}
}
