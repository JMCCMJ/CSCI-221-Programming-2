import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class LineBasedGraphicImpl extends LineBasedGraphic{
	String[] commands;
	String[] commandsCorrect;
	static int numPolylines;
	
	static String DRAWING_MESSAGE = "Drawing: ";
	
	//private ArrayList<Polyline> polylinesAL;

	@Override
	public void getPolylinesFromFile(String filename)
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Scanner scanner = null;
		scanner = new Scanner( new File( filename ));
		//System.out.println(scanner.nextLine());
		String scannerLine;
		String polyLines = "";
		while(scanner.hasNextLine()){
			scannerLine=scanner.nextLine();
			scannerLine.replaceAll("\\s+","");
			char firstChar;
			firstChar = scannerLine.charAt(0);
			switch(firstChar){
			case ';':
				
				break;
			case 'm':
				//System.out.println(scannerLine);
				polyLines = polyLines + scannerLine+'#';
				boolean breaker = true;
				while(breaker){
					scannerLine = scanner.nextLine();
					scannerLine.replaceAll("\\s+","");
					firstChar = scannerLine.charAt(0);
					switch(firstChar){
					case 'l':
						polyLines=polyLines+scannerLine+"/";
						break;
					case 'c':
						polyLines=polyLines+scannerLine+"<";
						break;
					case 's':
						polyLines=polyLines+scannerLine;
						breaker=false;
						break;
					}
				}
				break;
			}
		}
		scanner.close();
		System.out.println(DRAWING_MESSAGE + filename);
		StringBuffer polyLinesStrBuff = new StringBuffer(polyLines);
		String newPolyLines = new String(polyLinesStrBuff.toString());
		String newStr1 = newPolyLines.replaceAll("#", "|");
		String newStr2 = newStr1.replaceAll("<","|");
		String newStr3 = newStr2.replaceAll("/", "|");
		commands = newStr3.split("s");
		commandsCorrect = new String[commands.length];
		for( int j=0; j<commands.length; j++ )
		{
			commandsCorrect[j]=commands[j].substring(0, commands[j].length()-1);
		}
		numPolylines = commands.length;
		for( int p=0; p<commandsCorrect.length; p++ )
		{
			graphic.add(new PolylineImpl());
			for( int b=0; b<commandsCorrect[p].length()+1; b++ ){
				graphic.get(p).createPolylineFromCmds(commandsCorrect[p]);
			}
		}
	}


	@Override
	public int getNumPolylines() {
		// TODO Auto-generated method stub
		
		//return numPolylines;
		return commandsCorrect.length;
	}

	@Override
	public Polyline getPolylineByIndex(int i) {
		// TODO Auto-generated method stub
		return graphic.get(i);
	}

}
