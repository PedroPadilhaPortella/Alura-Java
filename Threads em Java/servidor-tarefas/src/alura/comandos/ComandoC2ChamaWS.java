package alura.comandos;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaWS implements Callable<String> {

	private PrintStream saidaCliente;

	public ComandoC2ChamaWS(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Servidor recebeu Comando C2");
		System.out.println("Processando Comando C2 e chamando Web Service");
		Thread.sleep(15000);
		int numero = new Random().nextInt(100) + 1;
		System.out.println("Comando C2 executado com sucesso");
		return Integer.toString(numero);
	}
}