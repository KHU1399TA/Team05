package main;

import utils.FileManager;

public class Example {
	private static final String DATA_FILE_PATH = "src/resources/data.txt";
	
	public static void main(String[] args) {
		FileManager fileManager = new FileManager(DATA_FILE_PATH);
		
		boolean wrote = fileManager.writeLine("Something ...", true);
		if (wrote)
			System.out.println("The text has been successfully written to the file!");
		
		printSeparator();
		
		String content = fileManager.readAll();
		if (content != null)
			System.out.println("Content: '" + content + "'");
		
		printSeparator();
		
		String firstLine = fileManager.readLine(1);
		if (firstLine != null)
			System.out.println("First line: '" + firstLine + "'");
	}
	
	private static void printSeparator() {
		System.out.println();
		System.out.println("-".repeat(30));
		System.out.println();
	}
}
