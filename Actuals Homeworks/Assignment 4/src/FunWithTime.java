
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class FunWithTime {

	public static void main( String [] args )
	{	
		String timeFromFile = "22:30";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		LocalTime time = LocalTime.parse(timeFromFile, formatter);
		System.out.println( "Time: " + time );

		System.out.println( time.compareTo( LocalTime.parse("22:40", formatter)));
		
		System.out.println( time.plusHours(2));
	}
	
}
