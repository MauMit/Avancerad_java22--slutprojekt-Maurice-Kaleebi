package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import controller.Controller;

public class SaveToTextFile {
	
	private Controller controller = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void readData(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				System.out.println(line);
				sb.append(line);
				line = br.readLine();

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public void writeData(String data, String path) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

			bw.write(data);
			bw.newLine();
			bw.flush();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void save(String log) {

		
		String path = "src/Files/saveLog.txt";
		SaveToTextFile svt = new SaveToTextFile();
		svt.writeData(log, path);
		svt.readData(path);
	}
}
