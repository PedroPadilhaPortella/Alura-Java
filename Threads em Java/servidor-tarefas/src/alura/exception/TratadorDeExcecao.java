package alura.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Houve uma exce��o ==> " + e.getMessage() + ", [Salvando erro no Dynatrace]");
		
	}

}
