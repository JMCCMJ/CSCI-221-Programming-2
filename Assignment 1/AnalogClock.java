
public class AnalogClock {

	private int degreeOfHourHand = 0;
	private int degreeOfMinuteHand = 0;

	public AnalogClock( int degreeOfHourHand, int degreeOfMinuteHand )
	{
		this.degreeOfHourHand = degreeOfHourHand;
		this.degreeOfMinuteHand = degreeOfMinuteHand;
	}
	
	public int getDegreeOfHourHand() {
		return degreeOfHourHand;
	}

	public void setDegreeOfHourHand(int degreeOfHourHand) {
		this.degreeOfHourHand = degreeOfHourHand;
	}

	public int getDegreeOfMinuteHand() {
		return degreeOfMinuteHand;
	}

	public void setDegreeOfMinuteHand(int degreeOfMinuteHand) {
		this.degreeOfMinuteHand = degreeOfMinuteHand;
	}

	public String toString()
	{
		return "hours: " + degreeOfHourHand + " minutes: " + degreeOfMinuteHand;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnalogClock clock = new AnalogClock(357, 330 );
		System.out.println( clock );
	}

}
