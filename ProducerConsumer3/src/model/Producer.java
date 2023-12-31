package model;

import controller.Controller;
import itemsAndBuffer.Buffer;
import itemsAndBuffer.Item;
import view.GUI;

public class Producer implements Runnable {

	Buffer buffer = null;
	boolean isRunning = true;
	private int timeToProduce = 0;
	private Controller controller = null;
	private int index = 0;

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	
	public void stop() {
		isRunning = false;
	}
	

	// Gives index designation to producer to know which producer it is, for example producer 12.
	// Producer will get a random interval time to produce a product, time is between 1 and 10 seconds.
	// Product is added to buffer list 
	@Override
	public void run() {
		timeToProduce = (1 + (int) (Math.random() * 9)) * 1000;
		index = controller.getProducerNumber();
		++index;
		controller.setProducerNumber(index);

		while (isRunning) {
			try {

				Thread.sleep(timeToProduce);
				setTimeToProduce(timeToProduce);
				String product = ("" + (char) ((int) (Math.random() * 100)));
				buffer.add(new Item(product));
				try {
					controller.setProducer(this);
					controller.getProductionInfo();
					controller.totalProducers();
					controller.getTotalProducts();
				} catch (Exception e) {
					System.out.println("No controller instance");
				}

			} catch (InterruptedException e) {
				break;
			}

		}

	}

	
	// Creates new producer and thread and returns thread to Controller to be used elsewhere
	public Thread CreateAndGetThread() {
		Producer producer = new Producer(buffer);
		producer.setController(controller);
		Thread producerThread = new Thread(producer);
		return producerThread;
	}

	public int getTimeToProduce() {
		return timeToProduce / 1000;
	}

	public void setTimeToProduce(int timeToProduce) {
		this.timeToProduce = timeToProduce;
	}

	public int getIndex() {
		return index;
	}
}
