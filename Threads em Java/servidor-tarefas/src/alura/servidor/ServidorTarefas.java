package alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import alura.factory.FabricaDeThreads;

public class ServidorTarefas {
	
	private ServerSocket servidor;
	private ExecutorService threadPool;
	private AtomicBoolean isRunning;
	private BlockingQueue<String> filaDeComandos;

	public ServidorTarefas() throws IOException {
		System.out.println("Server is starting...");
		this.servidor = new ServerSocket(9000);
		this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreads()); //newFixedThreadPool(4);
		this.isRunning = new AtomicBoolean(true);
		this.filaDeComandos = new ArrayBlockingQueue<String>(2);
	}
	
	public void rodar() throws IOException {
		while(this.isRunning.get()) {
			try {
			Socket socket = servidor.accept();	
			System.out.println("Accepting new client na porta " + socket.getPort());
			DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, filaDeComandos, socket, this);
			threadPool.execute(distribuirTarefas);
			} catch (SocketException e) {
				System.out.println("SocketException, está rodando? " + this.isRunning);
			}
		}
	}

	public void parar() throws IOException {
		this.isRunning.set(false);;
		servidor.close();
		threadPool.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();
		servidor.parar();
	}
}