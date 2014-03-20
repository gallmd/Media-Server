package network;

import java.util.concurrent.BlockingQueue;

public class NetworkOperationsDTO {
	
	private BlockingQueue<String> queue;
	private int portNumber;
	
	
	//getters
	public BlockingQueue<String> getQueue() {
		return queue;
	}
	
	public int getPortNumber() {
		return portNumber;
	}
	
	//setters
	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	

}
