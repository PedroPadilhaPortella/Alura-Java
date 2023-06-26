package alura.lista;


public class TarefaAdicionarElemento implements Runnable {
	
	private Lista lista;
	//private List<String> lista;
	private int threadNumber;

    public TarefaAdicionarElemento(Lista lista, int threadNumber) {
	    this.lista = lista;
	   	this.threadNumber = threadNumber;    	
    }

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lista.add("Thread" + this.threadNumber + " --- " + i);
		}
	}

}
