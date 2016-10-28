import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MasterStationSchedule {

	private String stationName;
	//private TimeTable[] timeTable;
	ArrayList<TimeTable> timeTableAL; //= new ArrayList<String>();
	public MasterStationSchedule( String stationName, int timeTableEntries )
	{
		this.stationName = stationName;
		
		timeTableAL = new ArrayList<TimeTable>(timeTableEntries);
		
		timeTableAL.ensureCapacity(timeTableEntries);

	}
	

	
	public String getStationName() {
		return stationName;
	}

	//public TimeTable[] getTimeTable() {
	public ArrayList<TimeTable> getTimeTable() {
		return timeTableAL;
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		
		strbuf.append( "departing station: " + stationName + "\n" );
		
		for( int i=0; i<timeTableAL.size(); i++ )
		{
			strbuf.append( " " + timeTableAL.get(i) );
		}
		
		return strbuf.toString();
	}
	
	// rather than providing abstract methods, I have just created a stub for you to complete.
	// I changed my mind because I wanted you to be able to run the main method below so you 
	// can see how the three classes hook together.
	public TimeDetail getNextDeparture( String destination, LocalTime stationArrivalTime )
	{
		TimeDetail rtnval= null;
		ArrayList<TimeDetail> timeDetail = null;
		int i;
		
		for( i=0; i<timeTableAL.size() && timeDetail == null; i++ )
		{
			if( timeTableAL.get(i).getStationName().equalsIgnoreCase(destination) )
				timeDetail = timeTableAL.get(i).getTimeDetail();
		}
		
		// if we found the station
		if( timeDetail != null )
		{			
			for( i=0; i<timeDetail.size(); i++)
			{
				if( timeDetail.get(i).getDepartureTime().compareTo(stationArrivalTime)==1||timeDetail.get(i).getDepartureTime().compareTo(stationArrivalTime)==0 )
				{
					rtnval = timeDetail.get(i);
					break;
				}				
			}
			
			// did we reach the end of the array without finding an entry?
			// if so, the next time is the first time tomorrow
			if( i == timeDetail.size() )
				rtnval = timeDetail.get(0);
		}
		
		return rtnval;
	}
	
	
	public static void main(String[] args) {
		MasterStationSchedule schedule;		
		ArrayList<TimeTable> timeTable = null;
		ArrayList<TimeDetail> timeDetail = null;
		
		schedule = new MasterStationSchedule( "Linz", 2 );
		
		// the constructor for MasterStationSchedule allocates the array of references
		// you still need to create each of the array entries
		timeTable = schedule.getTimeTable();
		//TimeTable timeTableAL0 = timeTable.add(0, timeTableAL0);
		timeTable.add(0,new TimeTable( "Zurich", 4 ));
		//System.out.println(timeTable.get(0));
		//timeTableAL0 = new TimeTable( "Zurich", 4 );
		
		// the constructor for TimeTable allocates the array of references to timeDetail objects
		// you still need to create each of array entries
		timeDetail = timeTable.get(0).getTimeDetail();		

		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		
		timeDetail.add(0,new TimeDetail( LocalTime.parse("13:50", formatter),LocalTime.parse("14:50", formatter)));
		timeDetail.add(1,new TimeDetail( LocalTime.parse("13:50", formatter),LocalTime.parse("15:50", formatter)));
		timeDetail.add(2,new TimeDetail( LocalTime.parse("15:55", formatter),LocalTime.parse("16:44", formatter)));
		timeDetail.add(3,new TimeDetail( LocalTime.parse("23:50", formatter),LocalTime.parse("13:50", formatter)));
		
		
		timeTable.add(1,new TimeTable( "Munich", 1 ));
		TimeTable timeTableAL1 = timeTable.get(1);
		//timeTableAL1 =new TimeTable( "Munich", 1 );
		
		
		timeDetail = timeTableAL1.getTimeDetail();
		//timeDetail[0] = new TimeDetail("2300,0230");
		timeDetail.add(0,new TimeDetail( "23:00,02:30"));
		
		//System.out.println( schedule );

		System.out.println( schedule.getNextDeparture("Zurich", LocalTime.parse("15:56", formatter)));
		
		//station not found
		System.out.println( schedule.getNextDeparture("Zurick", LocalTime.parse("15:56", formatter)));
		
		// departure time greater than last departure time for the day
		System.out.println( schedule.getNextDeparture("Zurich", LocalTime.parse("23:00", formatter)));		
		
		// departure time greater than last departure time for the day
		System.out.println( schedule.getNextDeparture("Zurich", LocalTime.parse("00:00", formatter)));
	}

}
