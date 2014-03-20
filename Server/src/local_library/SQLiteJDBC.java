package local_library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import network.NetworkOperations;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Server.SongFile;

public class SQLiteJDBC{

	Table displayTable;
	NetworkOperations netop;

	public SQLiteJDBC(Table table, NetworkOperations no){
		this.displayTable = table;
		this.netop = no;
	}

	public void viewDatabase(){
		Connection c = null;
		Statement stmnt = null;
		//Loop through results
		try {
			//Setup Table
			displayTable.clearAll();
			displayTable.removeAll();
			String[] columns = {"Title", "Artist", "Album", "Track", "Total Time"};
			for(int i = 0; i < columns.length; i++){
				TableColumn titleColumn = new TableColumn(displayTable, SWT.NONE);
				titleColumn.setText(columns[i]);
			}

			//Get SQLite connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/home/matt/.config/Clementine/clementine.db");
			c.setAutoCommit(false);

			//Create Statement
			stmnt = c.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM songs");



			while(rs.next()){
				//Get Title
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String album = rs.getString("album");
				int track = rs.getInt("track");
				int time = rs.getInt("length");

				TableItem item = new TableItem(displayTable, SWT.NONE);
				item.setText(0, title);
				item.setText(1, artist);
				item.setText(2, album);
				item.setText(3, (track + ""));
				item.setText(4, (time + ""));
				
			}

			for(int i = 0; i < columns.length; i++)
				displayTable.getColumn(i).pack();

			rs.close();
			stmnt.close();
			c.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateRemoteLibrary(){
		Connection c = null;
		Statement stmnt = null;
		
		//Loop through results
		try {
			
			netop.sendMessage("Update Library");

			//Setup Table
			displayTable.clearAll();
			displayTable.removeAll();
			String[] columns = {"Success", "Title", "Artist", "Album", "Track", "Total Time"};
			for(int i = 0; i < columns.length; i++){
				TableColumn titleColumn = new TableColumn(displayTable, SWT.NONE);
				titleColumn.setText(columns[i]);
			}

			//Get SQLite connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/home/matt/.config/Clementine/clementine.db");
			c.setAutoCommit(false);

			//Create Statement
			stmnt = c.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM songs");



			while(rs.next()){
				SongFile sf = new SongFile();
				//Get Title
				sf.setName(rs.getString("title"));  
				sf.setArtist(rs.getString("artist"));
				sf.setAlbum(rs.getString("album"));
				sf.setTrackNumber((rs.getInt("track")+""));
				sf.setTotalTime((rs.getInt("length")+""));
				
				netop.sendMessage(sf.writeJSON());

				TableItem item = new TableItem(displayTable, SWT.NONE);
				item.setText(1, sf.getName());
				item.setText(2, sf.getArtist());
				item.setText(3, sf.getAlbum());
				item.setText(4, sf.getTrackNumber());
				item.setText(5, sf.getTotalTime());
				
			}

			for(int i = 0; i < columns.length; i++)
				displayTable.getColumn(i).pack();

			rs.close();
			stmnt.close();
			c.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

