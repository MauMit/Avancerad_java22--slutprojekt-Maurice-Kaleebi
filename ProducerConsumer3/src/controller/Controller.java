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

	public Controller(Producer producer, Consumer consumer, GUI gui, Buffer buffer, ProducerList producerList, SaveToTextFile saveToTextFile) {

		this.producer = producer;
		this.consumer = consumer;
		this.gui = gui;
		this.buffer = buffer;
		this.producerList = producerList;
		this.saveToTextFile = saveToTextFile;
		
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

	public Thread getThread() {
		return producer.CreateAndGetThread();
	}
	
	public void startThread() {
		producerList.startThread();
	}
	
	public void removeThread() {
		producerList.removeThread();
	}
	
	
	public void stopAll() {
		producer.stop();
	}

	
	public void getProductionInfo() {
		gui.printProductionInfo(producer.getIndex(), producer.getTimeToProduce());
	}
	
	public void totalProducers() {
		int totalProducers = producerList.getProducerThreadsList().size();
		gui.printTotalProducers(totalProducers);
		
	}

	public void getTotalProducts() {
		gui.printProgressBar(buffer.getProductAmount());
	}
	
	public void consumers() {
		
		consumer.createConsumer();
	}
	
	public void saveLog(String log) {
		
		saveToTextFile.save(log);
	}
	
	public void sendAverage(double average) {
		 gui.printAverage(average);
		
	}

}