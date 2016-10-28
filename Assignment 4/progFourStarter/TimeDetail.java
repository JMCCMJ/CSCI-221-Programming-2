
public class TimeDetail {
	
	private int departureTime;
	private int arrivalTime;
	
	public TimeDetail( int departureTime, int arrivalTime )
	{
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	public TimeDetail( String timedetail )
	{
		// this constructor manages an instantiation when passed a comma delimited
		// value of the form "departureTime,arrivalTime"
		String[] times = timedetail.split("[,]");
		
		// this code could be tightened, it assumes perfect data
		this.departureTime = Integer.parseInt( times[0] );
		this.arrivalTime =Integer.parseInt(times[1]);
	}
	
	public int getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String toString()
	{
		return "departure at: " + departureTime + " arrival at: " + arrivalTime + "\n";		
	}
	
	public static void main(String[] args)
	{
		TimeDetail timeDetail = new TimeDetail( 1350, 0450 );
		
		System.out.println( timeDetail );
		
		timeDetail = new TimeDetail("1350,0450");
		
		System.out.println( timeDetail );
	}

}
