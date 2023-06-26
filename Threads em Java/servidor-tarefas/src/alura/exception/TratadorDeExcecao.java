package alura.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Houve uma exceção ==> " + e.getMessage() + ", [Salvando erro no Dynatrace]");
		
	}

}
