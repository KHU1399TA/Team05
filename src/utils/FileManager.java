package utils;

import java.io.*;
import java.util.stream.Collectors;

public class FileManager {
	public String path;
	
	public FileManager(String path) {
		this.path = path;
	}
	
	public boolean write(String content, boolean append) {
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(path, append));
			writer.write(content);
			writer.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean writeLine(String content, boolean append) {
		return this.write(content + "\n", append);
	}
	
	public String readAll() {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String content = reader.lines().collect(Collectors.joining("\n"));
			reader.close();
			
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String readLine(int lineNumber) {
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(path));
			
			String content = null;
			for (int i = 0; i < lineNumber; i++)
				content = reader.readLine();
			
			reader.close();
			
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
