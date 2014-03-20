package Server;

import java.io.IOException;

public class SongFile {

	private String trackID;
	private String name;
	private String artist;
	private String albumArtist;
	private String album;
	private String genre;
	private String comments;
	private String kind;
	private String size;
	private String totalTime;
	private String trackNumber;
	private String year;
	private String dateModified;
	private String dateAdded;
	private String playDate;
	private String bitRate;
	private String playCount;
	private String composer;
	private String publisher;
	private String location;
	private String fileFolderCount;
	private String libraryFolderCount;

	//getters
	public String getTrackID() {
		return trackID;
	}
	public String getName() {
		return name;
	}
	public String getArtist() {
		return artist;
	}
	public String getAlbumArtist() {
		return albumArtist;
	}
	public String getAlbum() {
		return album;
	}
	public String getGenre() {
		return genre;
	}
	public String getComments() {
		return comments;
	}
	public String getKind() {
		return kind;
	}
	public String getSize() {
		return size;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public String getYear() {
		return year;
	}
	public String getDateModified() {
		return dateModified;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public String getPlayDate() {
		return playDate;
	}
	public String getBitRate() {
		return bitRate;
	}
	public String getPlayCount() {
		return playCount;
	}
	public String getComposer() {
		return composer;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getLocation() {
		return location;
	}
	public String getFileFolderCount() {
		return fileFolderCount;
	}
	public String getLibraryFolderCount() {
		return libraryFolderCount;
	}

	//setters
	public void setTrackID(String temp) {
		this.trackID = temp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}
	public void setBitRate(String bitRate) {
		this.bitRate = bitRate;
	}
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setFileFolderCount(String fileFolderCount) {
		this.fileFolderCount = fileFolderCount;
	}
	public void setLibraryFolderCount(String libraryFolderCount) {
		this.libraryFolderCount = libraryFolderCount;
	}

	public String writeJSON() throws IOException{
		StringBuffer sb = new StringBuffer();
		//		sb.append("{\"Track ID\":\"");
		//		sb.append(trackID);
		//		sb.append("\", \"Name\":\"");
		//		sb.append(name);
		//		sb.append("\", \"Artist\":\"");
		//		sb.append(artist);
		//		sb.append("\", \"Album Artist\":\"");
		//		sb.append(albumArtist);
		//		sb.append("\", \"Album\":\"");
		//		sb.append(album);
		//		sb.append("\", \"Genre\":\"");
		//		sb.append(genre);
		//		sb.append("\", \"Comments\":\"");
		//		sb.append(comments);
		//		sb.append("\", \"Kind\":\"");
		//		sb.append(kind);
		//		sb.append("\", \"Size\":\"");
		//		sb.append(size);
		//		sb.append("\", \"Total Time\":\"");
		//		sb.append(totalTime);
		//		sb.append("\", \"Track Number\":\"");
		//		sb.append(trackNumber);
		//		sb.append("\", \"Year\":\"");
		//		sb.append(year);
		//		sb.append("\", \"Date Modified\":\"");
		//		sb.append(dateModified);
		//		sb.append("\", \"Date Added\":\"");
		//		sb.append(dateAdded);
		//		sb.append("\", \"Play Date\":\"");
		//		sb.append(playDate);
		//		sb.append("\", \"Bit Rate\":\"");
		//		sb.append(bitRate);
		//		sb.append("\", \"Play Count\":\"");
		//		sb.append(playCount);
		//		sb.append("\", \"Composer\":\"");
		//		sb.append(composer);
		//		sb.append("\", \"Publisher\":\"");
		//		sb.append(publisher);
		//		sb.append("\", \"Location\":\"");
		//		sb.append(location);
		//		sb.append("\", \"File Folder Count\":\"");
		//		sb.append(fileFolderCount);
		//		sb.append("\", \"Library Folder Count\":\"");
		//		sb.append(libraryFolderCount);
		//	
		//		sb.append("\"},\n");

		sb.append("{\"Name\":\"");
		sb.append(name);
		sb.append("\", \"Artist\":\"");
		sb.append(artist);
		sb.append("\", \"Album\":\"");
		sb.append(album);
		sb.append("\", \"Total Time\":\"");
		if(totalTime != null){
			sb.append(totalTime);
		}else{
			sb.append("0");
		}
		sb.append("\", \"Track Number\":\"");
		if(trackNumber != null){
			sb.append(trackNumber);
		}else{
			sb.append("0");
		}

		sb.append("\"},\n");

		return sb.toString();

	}


}
