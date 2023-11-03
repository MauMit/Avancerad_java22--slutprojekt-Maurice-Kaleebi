package controller;

import itemsAndBuffer.Buffer;
import model.Consumer;
import model.Producer;
import model.ProducerList;
import model.SaveToTextFile;
import view.GUI;

public class Controller {

	private Producer producer;
	private Consumer consumer;
	private GUI gui;
	private Buffer buffer;
	private ProducerList producerList;
	private SaveToTextFile saveToTextFile;

	public Controller(Producer producer, Consumer consumer, GUI gui, Buffer buffer, ProducerList producerList,
			SaveToTextFile saveToTextFile) {

		this.producer = producer;
		this.consumer = consumer;
		this.gui = gui;
		this.buffer = buffer;
		this.producerList = producerList;
		this.saveToTextFile = saveToTextFile;

		// Starts timer which calculates average production rate per 10 seconds
		buffer.startTimer();

		producer.setController(this);
		consumer.setController(this);
		producerList.setController(this);
		gui.setController(this);
		saveToTextFile.setController(this);
		buffer.setController(this);
	}

	public void setProducerNumber(int index) {
		buffer.setProducerNumber(index);
	}

	public int getProducerNumber() {
		return buffer.getProducerNumber();
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	// Creates consumers and threads for each consumer, called in ProducerList
	public void consumers() {

		consumer.createConsumer();
	}

	// Gets thread from each new producer instance from Producer class, called in producerList
	public Thread getThread() {
		return producer.CreateAndGetThread();
	}

	// Starts thread from producer instance, called in GUI
	public void startThread() {
		producerList.startThread();
	}

	// Interrupts and removes producer threads, called in GUI
	public void removeThread() {
		producerList.removeThread();
	}

	// Method to change isRunning to false, called in producerList
	public void stopAll() {
		producer.stop();
	}

	// Method to get the producernumber and the time it takes to produce
	public void getProductionInfo() {
		gui.printProductionInfo(producer.getIndex(), producer.getTimeToProduce());
	}

	// Method to display the total producers that are active when to app is running
	public void totalProducers() {
		int totalProducers = producerList.getProducerThreadsList().size();
		gui.printTotalProducers(totalProducers);

	}
	// Method for the total products that are being created, its called to the gui and used in the progressbar

	public void getTotalProducts() {
		gui.printProgressBar(buffer.getProductAmount());
	}

	//Method to save the log from GUI to a textfile
	public void saveLog(String log) {

		saveToTextFile.save(log);
	}

	
	 // Gets average production rate and prints them in GUI, called in Buffer.
	public void sendAverage(double average) {
		gui.printAverage(average);

	}

}