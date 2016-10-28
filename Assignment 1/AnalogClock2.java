
public class AnalogClock2 {

	private int degreeOfHourHand = 0;
	private int degreeOfMinuteHand = 0;

	public AnalogClock2( int degreeOfHourHand, int degreeOfMinuteHand )
	{
		this.degreeOfHourHand = rangeCheck( degreeOfHourHand, 0, 360 );
		this.degreeOfMinuteHand = rangeCheck( degreeOfMinuteHand, 0, 360 );
	}
	
	public int getDegreeOfHourHand() {
		return degreeOfHourHand;
	}

	public void setDegreeOfHourHand(int degreeOfHourHand) {
		this.degreeOfHourHand = rangeCheck(degreeOfHourHand, 0, 360);
	}

	public int getDegreeOfMinuteHand() {
		return degreeOfMinuteHand;
	}

	public void setDegreeOfMinuteHand(int degreeOfMinuteHand) {
		this.degreeOfMinuteHand = rangeCheck( degreeOfMinuteHand, 0, 360 );
	}

	public String toString()
	{
		return "hours: " + degreeOfHourHand + " minutes: " + degreeOfMinuteHand;
	}
	
	private int rangeCheck( int value, int min, int max )
	{
		int rtnval = 0;
		
		if( value >= min && value <= max )
			rtnval = value;
		
		return rtnval;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnalogClock2 clock = new AnalogClock2(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println( clock );
		
		// when testing be sure to hit the boundary conditions and a number in between
		int value;
		
		// should return 0
		value = clock.rangeCheck(-1, 0, 360 );
		System.out.println( "expect 0, value is: " + value );
		
		// should return 0
		value = clock.rangeCheck(0, 0, 360 );
		System.out.println( "expect 0, value is: " + value );
		
		// should return 150
		value = clock.rangeCheck(150, 0, 360);
		System.out.println( "expect 150, value is: " + value );
		
		//should return 360
		value = clock.rangeCheck(360, 0, 360 );
		System.out.println( "expect 360, value is: " + value );
		
		// should return 0
		value = clock.rangeCheck(361, 0, 360);
		System.out.println( "expect 0, value is: " + value );

	}

}
