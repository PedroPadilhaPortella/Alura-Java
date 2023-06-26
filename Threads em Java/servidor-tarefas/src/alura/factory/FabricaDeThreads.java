package alura.factory;

import java.util.concurrent.ThreadFactory;

import alura.exception.TratadorDeExcecao;

public class FabricaDeThreads implements ThreadFactory {
	
	private static int numero = 1;

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("Fabricando nova thread...");
		Thread thread = new Thread(r, "Thread Servidor de Tarefas" + numero);
		numero++;
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		return thread;
	}
}