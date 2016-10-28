import java.util.ArrayList;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;


public class TimeDetail {
	
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	
	public TimeDetail( LocalTime departureTime, LocalTime arrivalTime )
	{
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	public TimeDetail( String timedetail )
	{
		// this constructor manages an instantiation when passed a comma delimited
		// value of the form "departureTime,arrivalTime"
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		
		
		String[] timesArray = timedetail.split("[,]");
		ArrayList<String> times =new ArrayList<String>(Arrays.asList(timesArray));
		
		LocalTime time1 = LocalTime.parse(times.get(0), formatter);
		LocalTime time2 = LocalTime.parse(times.get(1), formatter);
		
		// this code could be tightened, it assumes perfect data
		
		this.departureTime = time1;
		this.arrivalTime = time2;
		
		
		
		
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String toString()
	{
		return "departure at: " + departureTime + " arrival at: " + arrivalTime + "\n";		
	}
	
	public static void main(String[] args)
	{
		
		String timeFromFile = "13:50";
		String timeFromFile2 = "04:50";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		LocalTime time = LocalTime.parse(timeFromFile, formatter);
		LocalTime time2 = LocalTime.parse(timeFromFile2, formatter);
		
		TimeDetail timeDetail = new TimeDetail(  time, time2 );
		
		System.out.println( timeDetail );
		
		timeDetail = new TimeDetail("1350,0450");
		
		System.out.println( timeDetail );
	}

}
