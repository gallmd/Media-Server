package Server;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import local_library.SQLiteJDBC;
import network.NetworkOperations;
import network.NetworkOperationsDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import remote_library.ReadXML;


public class ServerMainWindow {

	private static NetworkOperations network;
	private static Display display;
	private static Shell shell;
	private static BlockingQueue<String> inputQueue;
	private static BlockingQueue<String> outputQueue;
	private static Table displayTable;

	
	public static void main(String[] args) {

		//GUI operations
		display = new Display();
		shell = new Shell(display);
		shell.setSize(682, 430);
		shell.setLayout(new GridLayout(2, false));
		//add widgets
		ServerMainWindow.setContents();
		
		shell.open();

		inputQueue = new ArrayBlockingQueue<String>(1024);
		NetworkOperationsDTO networkDTO = new NetworkOperationsDTO();
		networkDTO.setQueue(inputQueue);
		networkDTO.setPortNumber(3490);

		network = new NetworkOperations(networkDTO, shell);
		
		

		networkDTO = null;

		
		network.startServer();

		while (!shell.isDisposed()) {

			if(!inputQueue.isEmpty()){
				try {
					TableItem item = new TableItem(displayTable, SWT.NONE);
					item.setText(inputQueue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!display.readAndDispatch())
				display.sleep();
		}
		network.stopServer();
		network=null;
		inputQueue = null;
		display.dispose();

	}

	//UI Layouts
	public static void setContents(){
		//Table to display data
		displayTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		displayTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		displayTable.setHeaderVisible(true);
		displayTable.setLinesVisible(true);
		
		//Composite that holds function buttons
		Composite functionButtonsComposite = new Composite(shell, SWT.NONE);
		functionButtonsComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		functionButtonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
//		Button btnNewButton = new Button(functionControlComposite, SWT.NONE);
//		btnNewButton.setText("New Button");
		
		//Display Connections Function Button
		Button btnDisplayConnections = new Button(functionButtonsComposite, SWT.NONE);
		btnDisplayConnections.setText("Display Connections");
		
		Label label_1 = new Label(functionButtonsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblLocalLibrary = new Label(functionButtonsComposite, SWT.HORIZONTAL | SWT.CENTER);
		lblLocalLibrary.setText("Local Library");
		
		//Local Library Function Button
		Button btnLocalUpdate = new Button(functionButtonsComposite, SWT.NONE);
		btnLocalUpdate.setText("Update");
		
		//Selection adapter
		btnLocalUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ReadXML.parseXML();
			}
		});
		
		//Local Library View Button
		Button btnLocalView = new Button(functionButtonsComposite, SWT.CENTER);
		btnLocalView.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				SQLiteJDBC songsJDBC = new SQLiteJDBC(displayTable, network);
				songsJDBC.viewDatabase();

			}
		});
		btnLocalView.setText("View");
		
		Label label = new Label(functionButtonsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblRemoteLibrary = new Label(functionButtonsComposite, SWT.CENTER);
		lblRemoteLibrary.setText("Remote Library");
		
		Button btnUpdate = new Button(functionButtonsComposite, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SQLiteJDBC songsJDBC = new SQLiteJDBC(displayTable, network);
				songsJDBC.updateRemoteLibrary();
			}
		});
		btnUpdate.setText("Update");
		
		Button btnView = new Button(functionButtonsComposite, SWT.NONE);
		btnView.setText("View");
		
		Button btnDelete = new Button(functionButtonsComposite, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					network.sendMessage("delete library");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setText("Delete");
		
	}

}
