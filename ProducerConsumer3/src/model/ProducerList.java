package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class ProducerList {

	private List<Thread> producerThreadsList = new ArrayList<>();

	private Controller controller = null;

	public void setController(Controller controller) {
		this.controller = controller;
		controller.consumers();
	}

	
	// Method to start producer threads
	public void startThread() {
		Thread producerThread = controller.getThread();
		producerThreadsList.add(producerThread);
		producerThread.start();
	}

	// Method to start remove threads

	public void removeThread() {
		if (!producerThreadsList.isEmpty()) {
			Thread removedThread = producerThreadsList.remove(producerThreadsList.size() - 1);
			removedThread.interrupt();
			controller.stopAll();
		}

	}

	public List<Thread> getProducerThreadsList() {
		return producerThreadsList;
	}

	public void setProducerThreadsList(List<Thread> producerThreadsList) {
		this.producerThreadsList = producerThreadsList;
	}

}
