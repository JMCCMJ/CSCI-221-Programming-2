
public class MasterStationSchedule {

	private String stationName;
	private TimeTable[] timeTable;
	
	
	public MasterStationSchedule( String stationName, int timeTableEntries )
	{
		this.stationName = stationName;
		timeTable = new TimeTable[timeTableEntries];
	}
	
	public String getStationName() {
		return stationName;
	}

	public TimeTable[] getTimeTable() {
		return timeTable;
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		
		strbuf.append( "departing station: " + stationName + "\n" );
		
		for( int i=0; i<timeTable.length; i++ )
		{
			strbuf.append( " " + timeTable[i] );
		}
		
		return strbuf.toString();
	}
	
	// rather than providing abstract methods, I have just created a stub for you to complete.
	// I changed my mind because I wanted you to be able to run the main method below so you 
	// can see how the three classes hook together.
	public TimeDetail getNextDeparture( String destination, int stationArrivalTime )
	{
		TimeDetail rtnval= null;

		// your code goes here
		
		return rtnval;
	}
	
	
	public static void main(String[] args) {
		MasterStationSchedule schedule;		
		TimeTable[] timeTable = null;
		TimeDetail[] timeDetail = null;
		
		schedule = new MasterStationSchedule( "Linz", 2 );
		
		// the constructor for MasterStationSchedule allocates the array of references
		// you still need to create each of the array entries
		timeTable = schedule.getTimeTable();		
		timeTable[0] = new TimeTable( "Zurich", 4 );
		
		// the constructor for TimeTable allocates the array of references to timeDetail objects
		// you still need to create each of array entries
		timeDetail = timeTable[0].getTimeDetail();		
		timeDetail[0] = new TimeDetail( 1350, 1450 );
		timeDetail[1] = new TimeDetail( 1350, 1550 );
		timeDetail[2] = new TimeDetail( 1555, 1644 );
		timeDetail[3] = new TimeDetail( 2350, 1350 );
		
		timeTable[1] =new TimeTable( "Munich", 1 );
		timeDetail = timeTable[1].getTimeDetail();
		timeDetail[0] = new TimeDetail("2300,0230");
		
		System.out.println( schedule );
		
		System.out.println( schedule.getNextDeparture("Zurich", 1556));
	}

}
