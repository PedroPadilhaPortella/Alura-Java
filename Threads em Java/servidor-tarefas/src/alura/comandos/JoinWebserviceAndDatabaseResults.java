package alura.comandos;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JoinWebserviceAndDatabaseResults implements Callable<Void> {

	private Future<String> futureWs;
	private Future<String> futureDb;
	private PrintStream saidaCliente;

	public JoinWebserviceAndDatabaseResults(Future<String> futureWs, Future<String> futureDb, PrintStream saidaCliente) {
		this.futureWs = futureWs;
		this.futureDb = futureDb;
		this.saidaCliente = saidaCliente;
	}

	@Override
	public Void call() {
		System.out.println("Aguardando resultados do future WS e DB");
		
		try {
			String numeroWs = this.futureWs.get(15, TimeUnit.SECONDS); 
			String numeroDb = this.futureDb.get(15, TimeUnit.SECONDS);
			this.saidaCliente.println("Webservice =>" + numeroWs + ", Database => " + numeroDb);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("Timeout: Execução do comando C2 - Cancelando execução do C2");
			this.futureWs.cancel(true);
			this.futureDb.cancel(true);
		}
		
		System.out.println("C2 Command execution finished!");
		return null;
	}

}
