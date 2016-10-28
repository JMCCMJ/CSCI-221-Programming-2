import java.util.ArrayList;
import java.util.Arrays;


public class TimeTable {

	private String stationName;
	//private TimeDetail[] timeDetail;
	private ArrayList<TimeDetail> timeDetailAL;


	public TimeTable( String stationName, int tableEntries )
	{
		this.stationName = stationName;
		//timeDetail = new TimeDetail[tableEntries];
		timeDetailAL = new ArrayList<TimeDetail>(tableEntries);
	}
	
	public TimeTable( String stationName, String timeTableInformation )
	{
		// in the file each record consists of two lines. The first line is the station name
		// the second line is the time table information		
		String[] scheduleEntriesArray;
		//ArrayList<String> scheduleEntries;
		
		
		scheduleEntriesArray = timeTableInformation.split("[|]");
		ArrayList<String> scheduleEntries = new ArrayList<String>(Arrays.asList(scheduleEntriesArray));
		//System.out.println(scheduleEntries);
		
		// now that we have the destination schedule name and the number of 
		// departures, we can create the TimeTable
		this.stationName = stationName;
		
		//timeDetail = new TimeDetail[scheduleEntries.length];
		timeDetailAL = new ArrayList<TimeDetail>(scheduleEntries.size());
		//timeDetailAL.ensureCapacity(scheduleEntries.size());
				
		for( int i = 0; i < scheduleEntries.size(); i++ )
		{
			//timeDetail[i] = new TimeDetail( scheduleEntries[i] );
			timeDetailAL.add(i,new TimeDetail( scheduleEntries.get(i)));
			//timeTable.add(1,new TimeTable( "Munich", 1 ));
		}
		
		
		//This is the code I tried to use to sort the lists. Didn't get it to work.
//		LocalTime smallString;
//		for(int a=0; a < timeDetailAL.size() - 1;a++)
//		{
//			smallString = a;
//
//			for (int i=a+1 ; i<timeDetailAL.size(); i++)
//			{
//				if(timeDetailAL.get(i).compareTo(timeDetailAL.get(smallString))<0)
//				{
//					smallString = i;  
//				}
//			}
//			if(smallString != a)
//			{
//				TimeDetail temp = timeDetailAL.get(a);
//				timeDetailAL.add(a,smallString); 
//				timeDetailAL[smallString] = temp;
//			}
//		}
		
	}
	
	public String getStationName() 
	{
		return stationName;
	}

	public ArrayList<TimeDetail> getTimeDetail() 
	{
		return timeDetailAL;
	}
	
	public String toString()
	{
		StringBuffer strbuf = new StringBuffer();
		
		strbuf.append( "destination: " + stationName + "\n" );
		
		for( int i=0; i<timeDetailAL.size(); i++ )
		{
			strbuf.append( " " + timeDetailAL.get(i) );
		}
		
		return strbuf.toString();
	}
		
	public static void main(String[] args) {
		

		
		//System.out.println( timeTable );
		
		
		
		//System.out.println( timeTable );
	}
}
