
public class MasterStationSchedule {

	private String stationName;
	private TimeTable[] timeTable;
	private int nextTableIndex = 0;
	
	
	public MasterStationSchedule( String stationName, int timeTableEntries )
	{
		this.stationName = stationName;
		timeTable = new TimeTable[timeTableEntries];
	}
	
	// new helper method to clean up the API for MasterStationSchedule
	public void createTimeTableEntry( String stationName, String scheduleEntries )
	{ 
		timeTable[nextTableIndex++] = new TimeTable( stationName, scheduleEntries );
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
		TimeDetail[] timeDetail = null;
		int i;
		
		for( i=0; i<timeTable.length && timeDetail == null; i++ )
		{
			if( timeTable[i].getStationName().equalsIgnoreCase(destination) )
				timeDetail = timeTable[i].getTimeDetail();
		}
		
		// if we found the station
		if( timeDetail != null )
		{			
			for( i=0; i<timeDetail.length; i++)
			{
				if( timeDetail[i].getDepartureTime() >= stationArrivalTime )
				{
					rtnval = timeDetail[i];
					break;
				}				
			}
			
			// did we reach the end of the array without finding an entry?
			// if so, the next time is the first time tomorrow
			if( i == timeDetail.length )
				rtnval = timeDetail[0];
		}
		
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
		
		//station not found
		System.out.println( schedule.getNextDeparture("Zurick", 1556));
		
		// departure time greater than last departure time for the day
		System.out.println( schedule.getNextDeparture("Zurich", 2400));		
		
		// departure time greater than last departure time for the day
		System.out.println( schedule.getNextDeparture("Zurich", 0000));
	}

}
