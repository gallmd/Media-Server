package remote_library;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import network.NetworkOperations;

import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.List;


public class UpdateLibrary {

	private List list;
	private NetworkOperations network;
	private String[] lines;
	private StringBuffer sb;
	private int lastGood;
	private int start;

	public UpdateLibrary(NetworkOperations nw, List list, int start){
		this.network = nw;
		this.list = list;
		this.start = start;
	}

	public void sendUpdates() throws FileNotFoundException{
		
//		JSONParser parser = new JSONParser();
		//Set up scanner to read from library JSON file
//		Path path = Path.get("C:\\Users\\Matt Gall\\git\\Java\\Java Projects\\Server\\library.JSON");
		Scanner scanner;
		
		try {
			scanner = new Scanner("test");
			//Set delimiter so scanner reads one line at time
			scanner.useDelimiter("\n");

			int i = 0;
			while(scanner.hasNext()){

				sb = new StringBuffer();
				String line = scanner.next();
				sb.append(line);
				sb.append("}");
				if(i>=start){
//					network.sendMessage(sb.toString());
					list.add(sb.toString());
					Thread.sleep(100);
				}
				lastGood = i;
				i++;
			}

		} catch (Exception e) {
			
			list.add("failed, io");
			list.add((lastGood + " "));
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

}
