package alura.threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TarefaBuscaTextual implements Runnable {

	private String fileName;
	private String nome;

	public TarefaBuscaTextual(String fileName, String nome) {
		this.fileName = fileName;
		this.nome = nome;
		
	}

	@Override
	public void run() {
		try {
		Scanner scanner = new Scanner(new File(fileName));
		int lineNumber = 1;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.toLowerCase().contains(nome.toLowerCase())) {
				System.out.println(fileName + " - " + lineNumber + " - " + line);
			}
			
			lineNumber++;
		}
		
		scanner.close();
		
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
