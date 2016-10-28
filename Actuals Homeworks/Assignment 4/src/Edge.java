
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;

public class Edge {
	// numeric constants
	private static final int MAX_HOUR_VALUE = 23;
	private static final int MAX_MINUTE_VALUE = 59;
	private static final int MIN_VALUE = 0;
	
	// string constants
	private static final String RANGE_MESSAGE = "Enter time as XXYY where XX is in the range of 00 to 23 and YY is in the range 00 to 59";
	private static final String NONNUMERIC_DATA_ENTERED = "Non numeric data was entered.";
	private static final String ENTER_DESTINATION_STATION = "Enter the destination to which you want to travel: ";
	private static final String ENTER_STATION_ARRIVAL = "What time will you be arriving at the station: ";
	private static final String UNRECOGNIZED_STATION_MESSAGE = " is not recognized as a station name.";
	private static final String VALID_STATIONS_ARE = "Valid stations are ";
	private static final String QUIT_OR_CONTINUE_MESSAGE = "Enter 'Q' to quit or a valid station name: ";
	private static final String CUSTOMER_SERVICE_MESSAGE = "Please ask a ticket agent for help with your booking.";
	private static final String SUPPLY_FILENAME__MESSAGE = "A filename must be supplied via command line parameters";
	private static final String BAD_FILENAME = "Trouble opening file with name: ";
	private static final String OUTPUT_FORMAT = "%02d:%02d.";
	private static final String DEFAULT_STATION = "Linz";
	private static final String THREE_TRIES_UP = "You will automatically exit the program after three failed attempts.";
	private static final String ENTER_TIME = "Please enter the time in the format HH:MM.";
	
	private static ArrayList<String> validStationsListForDisplay;
	private static ArrayList<String> validStationsListForComparison;
	
	static boolean threeTries = false;
	
	private static boolean isBadValue( String arrivalTime, int min, int max )
	{
		boolean rtnval = true;
		
		
		
		int timeINT = Integer.parseInt(arrivalTime);
		
		if( timeINT >= min && timeINT <= max )
			rtnval = false;
		
		return rtnval;
	}
	
	private static String readTimeFromKeyboard( Scanner keyboard )
	{
		String rtnval = null;
		
		try
		{
			//rtnval = keyboard.nextLine();
			rtnval = keyboard.nextLine();
		}
		catch(Exception e)
		{
			System.out.println( NONNUMERIC_DATA_ENTERED );
			keyboard.next(); // read the offending data
		}
		
		return rtnval;
	}
	
	private static void buildValidStationsLists( MasterStationSchedule schedule )
	{
		validStationsListForDisplay = new ArrayList<String>();
		validStationsListForComparison = new ArrayList<String>();
		
		ArrayList<TimeTable> timeTable = schedule.getTimeTable();
		
		for( int i=0; i<timeTable.size(); i++ )
		{
			validStationsListForDisplay.add( timeTable.get(i).getStationName());
			validStationsListForComparison.add( timeTable.get(i).getStationName().toUpperCase() );
		}
	}
	
	private static boolean isValidStation( String stationName )
	{		
		return validStationsListForComparison.contains( stationName.toUpperCase() );
	}
	
	private static String collectUserInput( Scanner keyboard )
	{
		String rtnval = null;
		String destinationName = null;
		String arrivalTime = null;
		boolean bUserHasQuit = false;
		
		
		System.out.println(THREE_TRIES_UP);
		System.out.print(ENTER_DESTINATION_STATION);
		
		destinationName = keyboard.nextLine();
		
		int break3 = 0;
		while( !isValidStation(destinationName) && !bUserHasQuit && !threeTries)
		{
			System.out.println( destinationName + UNRECOGNIZED_STATION_MESSAGE);
			System.out.println(VALID_STATIONS_ARE + validStationsListForDisplay );
			System.out.print(QUIT_OR_CONTINUE_MESSAGE);

			destinationName = keyboard.nextLine();
			
			if(destinationName.length() == 1 &&
					(destinationName.charAt(0) == 'Q' || destinationName.charAt(0) == 'q' ))
				bUserHasQuit = true;
			break3++;
			if(break3==2){
				threeTries=true;
			}
		}
		
		
		
		if( !bUserHasQuit &&threeTries==false)
		{		
			// prompt the user for their arrival time the station
			System.out.println(ENTER_TIME);
			System.out.print(ENTER_STATION_ARRIVAL);
		
			arrivalTime = readTimeFromKeyboard(keyboard);
			
			String[] timesArray = arrivalTime.split("[:]");
			ArrayList<String> times =new ArrayList<String>(Arrays.asList(timesArray));
			String timeString1 = times.get(0);
			String timeString2 = times.get(1);
			
			
			while( isBadValue( timeString1 , MIN_VALUE, MAX_HOUR_VALUE ) ||
					isBadValue( timeString2, MIN_VALUE, MAX_MINUTE_VALUE) )
			{
				System.out.println( RANGE_MESSAGE );
				System.out.println(ENTER_TIME);
				System.out.print(ENTER_STATION_ARRIVAL);
				
				arrivalTime = readTimeFromKeyboard(keyboard);
			}
		}
		
		rtnval = destinationName + "|" + arrivalTime;
		
		return rtnval;
	}
	public boolean getThreeTries(){
		return threeTries;
	}
	
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = null;
		String string = null;
		String stationName = null;
		int stationCount = 0;
		MasterStationSchedule schedule = null;
		//TimeTable[] timeTable = null;
		ArrayList<TimeTable> timeTable = null;
	
		if( args.length > 0 )
		{
			try
			{
				// obtain a scanner attached to the filename that was provided
				scanner = new Scanner( new FileInputStream( args[0]) );
			}
			catch( Exception e )
			{
				System.out.println( BAD_FILENAME + args[0] );
				System.exit(-1);
			}
		}
		else
		{
			System.out.println(SUPPLY_FILENAME__MESSAGE);
			System.exit(-1);
		}
		
		// run through the file counting up the number of destination stations
		while( scanner.hasNextLine() )
		{
			string = scanner.nextLine();
			if( !Character.isDigit(string.charAt(0)) && string.charAt(0) != ';')
				stationCount++;
		}
		
		// close and reopen the file to start reading from the top
		scanner.close();
		scanner = new Scanner( new FileInputStream( args[0]) );
		
		// now that we know how many stations there are create the MasterStationSchedule
		schedule = new MasterStationSchedule( DEFAULT_STATION, stationCount );
		timeTable = schedule.getTimeTable();
		// reuse the station count as an index into the time table array
		stationCount = 0;
		
		
		
		while( scanner.hasNextLine() )
		{
			string = scanner.nextLine();
			
			if( string.charAt(0) == ';')
				; // do nothing, strip comments from the data
			else if( !Character.isDigit(string.charAt(0)))
				// save the destination schedule name
				stationName = string;
			else
				// we have the schedule line for the last destination read from the file
				// I created a method inside of the MasterStationSchedule to manage the housekeeping
				// it would be called as follows: schedule.createTimeTableEntry( stationName, string );	
				
				// your code should have looked as follows...
				
				timeTable.add(stationCount++,new TimeTable( stationName, string ));
		}

		scanner.close();
	
		// the MasterStationSchedule information has been loaded from disk to memory
		// let's dialog with the user...
		
		Scanner keyboard = new Scanner( System.in );
		
		buildValidStationsLists( schedule );
			
		String[] userInputArray = collectUserInput( keyboard ).split("[|]");
		ArrayList<String> userInput =new ArrayList<String>(Arrays.asList(userInputArray));
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		
		if( !userInput.get(0).equalsIgnoreCase("Q")&&threeTries==false )
		{
			
			
//			TimeDetail timeDetail = schedule.getNextDeparture( userInput[0], Integer.valueOf(userInput[1]) );
			TimeDetail timeDetail = schedule.getNextDeparture( userInput.get(0), LocalTime.parse(userInput.get(1), formatter) );
			System.out.println("Next Departure to " + userInput.get(0) + " is at: " +
					timeDetail.getDepartureTime());
			System.out.printf( "You will arrive at: " +
					timeDetail.getArrivalTime());

		}
		
		else
			System.out.println(CUSTOMER_SERVICE_MESSAGE);
		
		keyboard.close();
	}
}
