import java.awt.Color;


public class PolylineImpl extends Polyline{

	@Override
	public void createPolylineFromCmds(String cmdSequence) {
		// TODO Auto-generated method stub
		
		String[] commands = cmdSequence.split("\\|");
	
		char firstChar;
		for( int j=0; j<commands.length; j++ )
		{
			firstChar = commands[j].charAt(0);
			String tempStr = commands[j].substring(1, commands[j].length());
			String[] commandsSplit = tempStr.split("\\,");

			switch(firstChar){
			case 'm':
				float moveX = Float.parseFloat(commandsSplit[0].trim());
				float moveY = Float.parseFloat(commandsSplit[1].trim());
				polyline.moveTo(moveX,moveY);
				break;
			case 'c':
				//set color somehow
				int c1 = Integer.parseInt(commandsSplit[0].trim());
				int c2 = Integer.parseInt(commandsSplit[1].trim());
				int c3 = Integer.parseInt(commandsSplit[2].trim());
				color = new Color(c1, c2, c3);
				break;
			case 'l':
				float lineX = Float.parseFloat(commandsSplit[0].trim());
				float lineY = Float.parseFloat(commandsSplit[1].trim());
				polyline.lineTo(lineX, lineY);
				break;
			//no case s, it is removed already
				
			}
		}
		
	}

}
