
public class TimeTable {

	private String stationName;
	private TimeDetail[] timeDetail;


	public TimeTable( String stationName, int tableEntries )
	{
		this.stationName = stationName;
		timeDetail = new TimeDetail[tableEntries];
	}
	
	public String getStationName() 
	{
		return stationName;
	}

	public TimeDetail[] getTimeDetail() 
	{
		return timeDetail;
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		
		strbuf.append( "destination: " + stationName + "\n" );
		
		for( int i=0; i<timeDetail.length; i++ )
		{
			strbuf.append( " " + timeDetail[i] );
		}
		
		return strbuf.toString();
	}
		
	public static void main(String[] args) {
		TimeTable timeTable = null;
		
		timeTable = new TimeTable( "Zurich", 4 );
		
		timeTable.timeDetail[0] = new TimeDetail( 1350, 1450 );
		timeTable.timeDetail[1] = new TimeDetail( 1350, 1550 );
		timeTable.timeDetail[2] = new TimeDetail( 1555, 1644 );
		timeTable.timeDetail[3] = new TimeDetail( 2350, 1350 );
		
		System.out.println( timeTable );
	}
}
