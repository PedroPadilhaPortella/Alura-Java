package alura.lista;

public class TarefaImprimir implements Runnable {
	
	private Lista lista;
	
	TarefaImprimir(Lista lista) {
		this.lista = lista;
	}

	@Override
	public void run() {		
		synchronized (this.lista) {
			try {
//				Thread.sleep(1000);
				if(!lista.isFull()) {
					System.out.println("Esperando notificacao da lista");
					lista.wait();					
				}
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(i + " -- " + lista.get(i));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
