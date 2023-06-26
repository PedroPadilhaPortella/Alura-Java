package alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
	
		System.out.println("Client is starting...");

		Socket socket = new Socket("localhost", 9000);
		
		
		Thread threadSendCommand = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Na espera para envio de comandos:");
					PrintStream saida = new PrintStream(socket.getOutputStream());
					Scanner teclado = new Scanner(System.in);
					
					while(teclado.hasNextLine()) {
						String linha = teclado.nextLine();	
						if(linha.trim().equals(""))  break;
						saida.println(linha);
					}
					
					teclado.close();
					saida.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} 
			}
		});
		
		Thread threadReceiveCommand = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Recebendo dados do servidos:");
					Scanner serverResponse = new Scanner(socket.getInputStream());
					
					while(serverResponse.hasNextLine()) {
						String linha = serverResponse.nextLine();	
						if(linha.trim().equals(""))  break;
						System.out.println(linha);
					}
					
					serverResponse.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		threadSendCommand.start();
		threadReceiveCommand.start();
		
		threadSendCommand.join();
		
		System.out.println("Finalizando conexão...");
		
		socket.close();
	}
}