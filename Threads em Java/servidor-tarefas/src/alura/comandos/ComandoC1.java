package alura.comandos;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC1(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		try {
			System.out.println("Executando Comando C1");
			Thread.sleep(20000);
			System.out.println("Comando C1 executado com sucesso");
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
