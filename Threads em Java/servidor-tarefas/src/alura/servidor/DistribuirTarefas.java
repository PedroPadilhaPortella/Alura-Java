package alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import alura.comandos.ComandoC1;
import alura.comandos.ComandoC2AcessaDatabase;
import alura.comandos.ComandoC2ChamaWS;
import alura.comandos.JoinWebserviceAndDatabaseResults;
import alura.comandos.TarefaConsumir;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaDeComandos;

	public DistribuirTarefas(ExecutorService threadPool, BlockingQueue<String> filaDeComandos, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
		this.filaDeComandos = filaDeComandos;
		this.socket = socket;
		this.servidor = servidor;
		this.inicializarConsumidores(2);
	}
	
	private void inicializarConsumidores(int quantidadeConsumidores) {
		for(int i = 0; i <= quantidadeConsumidores; i++) {
			TarefaConsumir tarefa = new TarefaConsumir(this.filaDeComandos);
			this.threadPool.execute(tarefa);
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("Distribuindo tarefas para o cliente " + this.socket);
			
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
			
			while(entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				
				switch (comando) {
				    case "c1": {
				        saidaCliente.println("Confirmação do comando c1");
				        ComandoC1 c1 = new ComandoC1(saidaCliente);
				        this.threadPool.execute(c1);
				        break;
				    }
				    case "c2": {
				        saidaCliente.println("Confirmação do comando c2");
				        ComandoC2ChamaWS c2Ws = new ComandoC2ChamaWS(saidaCliente);
				        ComandoC2AcessaDatabase c2Db = new ComandoC2AcessaDatabase(saidaCliente);
	
				        Future<String> futureWs = this.threadPool.submit(c2Ws);
				        Future<String> futureDb = this.threadPool.submit(c2Db);
				        
				        this.threadPool.submit(new JoinWebserviceAndDatabaseResults(futureWs, futureDb, saidaCliente));
	
				        break;
				    }
				    case "c3": {
				    	this.filaDeComandos.put(comando);
				        saidaCliente.println("Comando c3 adicionado na Fila");
				        break;
				    }
				    case "end": {
				        saidaCliente.println("Desligando servidor cliente...");
				        servidor.parar();
				        break;
				    }
				    default: {
				        saidaCliente.println("Comando não encontrado");
				    }
				}
				
				System.out.println(comando);
			}

			entradaCliente.close();
			saidaCliente.close();
		} catch(Exception e ) {
			throw new RuntimeException(e);
		}	
	}
}