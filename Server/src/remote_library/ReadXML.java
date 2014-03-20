package remote_library;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import Server.SongFile;

public class ReadXML {

	private static Writer out;
	private static SAXParser parser;
	private static SongFile sf;
	protected static boolean bTrackID = false;
	protected static boolean bInKey = false;

	protected static boolean bFileFolderCount;
	protected static boolean bLocation;
	protected static boolean bPublisher;
	protected static boolean bComposer;
	protected static boolean bPlayCount;
	protected static boolean bBitRate;
	protected static boolean bPlayDate;
	protected static boolean bDateAdded;
	protected static boolean bDateModified;
	protected static boolean bYear;
	protected static boolean bTrackNumber;
	protected static boolean bTotalTime;
	protected static boolean bSize;
	protected static boolean bKind;
	protected static boolean bComments;
	protected static boolean bGenre;
	protected static boolean bAlbum;
	protected static boolean bAlbumArtist;
	protected static boolean bArtist;
	protected static boolean bName;
	protected static boolean bLibraryFolderCount;
	protected static String pathOutput = "C:\\Users\\Matt Gall\\git\\Java\\Java Projects\\Stand Alone Server\\assets\\processed library output.txt";
	protected static String pathInput = "C:\\Users\\Matt Gall\\git\\Java\\Java Projects\\Stand Alone Server\\assets\\raw library from winamp.xml";

	private static StringBuffer sb = new StringBuffer();


	public static void parseXML(){

		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {

			out = new FileWriter(pathOutput);
			out.write("");
			out = new FileWriter(pathOutput, true);
			out.write("{\n");
			out.write("\"Songs\": [\n");
			parser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler(){


				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes){
					if(qName.equalsIgnoreCase("key")){
						bInKey = true;
					}else{
						bInKey = false;
					}

				}

				public void endElement(String uri, String localName, String qName){
					if(!bInKey){
						if(bTrackID){
							
							//Create new song file since we are at the top of the attribute list
							sf = new SongFile();
							sf.setTrackID(sb.toString().trim());
							sb = new StringBuffer();
							bTrackID = false;
							
						}else if(bName){
							
							sf.setName(sb.toString().trim());
							sb = new StringBuffer();
							bName = false;
							
						}else if(bArtist){
							
							sf.setArtist(sb.toString().trim());
							sb = new StringBuffer();
							bArtist = false;
							
						}else if(bAlbumArtist){
							
							sf.setAlbumArtist(sb.toString().trim());
							sb = new StringBuffer();
							bAlbumArtist = false;
							
						}else if(bAlbum){
							
							sf.setAlbum(sb.toString().trim());
							sb = new StringBuffer();
							bAlbum = false;
							
						}else if(bGenre){
							
							sf.setGenre(sb.toString().trim());
							sb = new StringBuffer();
							bGenre = false;
							
						}else if(bComments){
							
							sf.setComments(sb.toString().trim());
							sb = new StringBuffer();
							bComments = false;
							
						}else if(bKind){
							
							sf.setKind(sb.toString().trim());
							sb = new StringBuffer();
							bKind = false;
							
						}else if(bSize){
							
							sf.setSize(sb.toString().trim());
							sb = new StringBuffer();
							bSize = false;
							
						}else if(bTotalTime){
							
							sf.setTotalTime(sb.toString().trim());
							sb = new StringBuffer();
							bTotalTime = false;
							
						}else if(bTrackNumber){
							
							sf.setTrackNumber(sb.toString().trim());
							sb = new StringBuffer();
							bTrackNumber = false;
							
						}else if(bYear){
							
							sf.setYear(sb.toString().trim());
							sb = new StringBuffer();
							bYear = false;
							
						}else if(bDateModified){
							
							sf.setDateModified(sb.toString().trim());
							sb = new StringBuffer();
							bDateModified = false;
							
						}else if(bDateAdded){
							
							sf.setDateAdded(sb.toString().trim());
							sb = new StringBuffer();
							bDateAdded = false;
							
						}else if(bPlayDate){
							
							sf.setPlayDate(sb.toString().trim());
							sb = new StringBuffer();
							bPlayDate = false;
							
						}else if(bBitRate){
							
							sf.setBitRate(sb.toString().trim());
							sb = new StringBuffer();
							bBitRate = false;
							
						}else if(bPlayCount){
							
							sf.setPlayCount(sb.toString().trim());
							sb = new StringBuffer();
							bPlayCount = false;
							
						}else if(bComposer){
							
							sf.setComposer(sb.toString().trim());
							sb = new StringBuffer();
							bComposer = false;
							
						}else if(bPublisher){
							
							sf.setPublisher(sb.toString().trim());
							sb = new StringBuffer();
							bPublisher = false;
							
						}else if(bLocation){
							
							sf.setLocation(sb.toString().trim());
							sb = new StringBuffer();
							bLocation = false;
							
						}else if(bFileFolderCount){
							
							sf.setFileFolderCount(sb.toString().trim());
							sb = new StringBuffer();
							bFileFolderCount = false;
							
						}else if(bLibraryFolderCount){
							
							sf.setLibraryFolderCount(sb.toString().trim());
							sb = new StringBuffer();
							bLibraryFolderCount = false;
							try {
								sf.writeJSON();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}

				}

				public void characters(char[] buffer, int start, int length){

					String temp = new String(buffer, start, length);
					temp = temp.trim();

					if(bInKey){

						if(temp.equalsIgnoreCase("Track ID")){
							bTrackID  = true;
						}else if(temp.equalsIgnoreCase("Name")){
							bName = true;
						}else if(temp.equalsIgnoreCase("Artist")){
							bArtist = true;
						}else if(temp.equalsIgnoreCase("Album Artist")){
							bAlbumArtist = true;
						}else if(temp.equalsIgnoreCase("Album")){
							bAlbum = true;
						}else if(temp.equalsIgnoreCase("Genre")){
							bGenre = true;
						}else if(temp.equalsIgnoreCase("Comments")){
							bComments = true;
						}else if(temp.equalsIgnoreCase("Kind")){
							bKind = true;
						}else if(temp.equalsIgnoreCase("Size")){
							bSize = true;
						}else if(temp.equalsIgnoreCase("Total Time")){
							bTotalTime = true;
						}else if(temp.equalsIgnoreCase("Track Number")){
							bTrackNumber = true;
						}else if(temp.equalsIgnoreCase("Year")){
							bYear = true;
						}else if(temp.equalsIgnoreCase("Date Modified")){
							bDateModified = true;
						}else if(temp.equalsIgnoreCase("Date Added")){
							bDateAdded = true;
						}else if(temp.equalsIgnoreCase("Play Date UTC")){
							bPlayDate = true;
						}else if(temp.equalsIgnoreCase("Bit Rate")){
							bBitRate = true;
						}else if(temp.equalsIgnoreCase("Play Count")){
							bPlayCount = true;
						}else if(temp.equalsIgnoreCase("Composer")){
							bComposer = true;
						}else if(temp.equalsIgnoreCase("Publisher")){
							bPublisher = true;
						}else if(temp.equalsIgnoreCase("Location")){
							bLocation = true;
						}else if(temp.equalsIgnoreCase("File Folder Count")){
							bFileFolderCount = true;
						}else if(temp.equalsIgnoreCase("Library Folder Count")){
							bLibraryFolderCount = true;
						}
					}else if(!bInKey){

						sb.append(temp);

					}

				}

			};

			parser.parse(pathInput, handler);
			out.append("{}]\n}");
			out.flush();
			out.close();


		} catch(Exception e) {

			e.printStackTrace();
		}

	}

}
