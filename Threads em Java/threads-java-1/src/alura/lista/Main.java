package alura.lista;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Lista lista = new Lista();
		//List<String> lista = Collections.synchronizedList(new ArrayList<String>());
		//List<String> lista = new Vector<String>();
		
        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionarElemento(lista, i)).start();;
        } 
        
        new Thread(new TarefaImprimir(lista)).start();
	}
}