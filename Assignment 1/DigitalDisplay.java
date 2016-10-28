
public class DigitalDisplay {
	
	private int hour = 0;
	private int minute = 0;
	private boolean isDark = false;
	private AnalogClock2 analogClock = null;
	
	public DigitalDisplay(AnalogClock2 analogClock) {
		this.analogClock = analogClock;
		
		// compute hour and minute using accessing methods 
		// to get hour and minute in degrees from the analog clock
		hour = analogClock.getDegreeOfHourHand() / 30;
		minute = analogClock.getDegreeOfMinuteHand() / 6;
		isDark = true;
	}
	
	public boolean isDark() {
		return isDark;
	}

	public void setDark(boolean isDark) {
		this.isDark = isDark;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public String toString()
	{
		return hour + ":" + minute + (isDark() ? " PM" : " AM");
	}

	public static void main(String[] args) {
		
		AnalogClock2 analogClock = new AnalogClock2( 330, 90 );
		DigitalDisplay digitalDisplay =  new DigitalDisplay(analogClock);
		
		System.out.println(digitalDisplay);
		
		digitalDisplay.setDark(false);
		
		System.out.println(digitalDisplay);

	}

}
