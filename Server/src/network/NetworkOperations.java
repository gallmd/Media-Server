package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.swt.widgets.Shell;

public class NetworkOperations {

	private DataInputStream input;
	private DataOutputStream output;
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private BlockingQueue<String> inputQueue;
	private int portNumber;
	private Thread serverThread;


	public NetworkOperations(NetworkOperationsDTO networkDTO, Shell shell){
		this.inputQueue = networkDTO.getQueue();
		this.portNumber = networkDTO.getPortNumber();	
	}



	public void startServer(){

		final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

		Runnable serverTask = new Runnable(){
			@Override
			public void run(){

				//create server socket
				try {

					serverSocket = new ServerSocket(portNumber);

					while(!serverThread.isInterrupted()){
						//if a client connects, give it its own thread
						clientSocket = serverSocket.accept();
						inputQueue.put("Client Connected");
						clientProcessingPool.submit(new ClientTask(clientSocket));
						if(serverThread.isInterrupted()){
							serverSocket.close();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				clientProcessingPool.shutdown();

			}

		};

		serverThread = new Thread(serverTask);
		serverThread.start();
	}
	
	public void stopServer(){
		serverThread.interrupt();
	}
	


	public String getConnectedClient(){
		InetAddress clientAddress = clientSocket.getInetAddress();

		String result = clientAddress.getCanonicalHostName();
		return result;
	}

	public String getServerAddress(){
		InetAddress serverAddress = serverSocket.getInetAddress();
		String result = serverAddress.toString();
		//serverAddress.getHostAddress();
		return result;

	}

	public void sendMessage(String message) throws IOException{
		message = (message + "\n");
		output.writeBytes(message);
	}

	public String recvMessage(){
		String message = "test";
		try {
			message = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	private class ClientTask implements Runnable{

		private final Socket clientSocket;

		//Constructor
		private  ClientTask(Socket clientSocket){
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			//create client socket, get input and output
			try {
				
				

				input = new DataInputStream(clientSocket.getInputStream());
				output = new DataOutputStream(clientSocket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(true){
				try {
					String message = input.readLine();
					inputQueue.put(message);
					if(Thread.interrupted()){
						clientSocket.close();
						input.close();
						output.close();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

}
