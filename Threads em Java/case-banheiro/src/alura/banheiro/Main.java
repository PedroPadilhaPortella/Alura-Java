package alura.banheiro;

public class Main {

	public static void main(String[] args) {
		
		Banheiro banheiro = new Banheiro();

		Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "Felipe"); 
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Rogerio"); 
//		Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Marcello"); 
//		Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Pedro"); 
		Thread zelador = new Thread(new TarefaLimpeza(banheiro), "Zelador"); 
		
		zelador.setDaemon(true);
		zelador.setPriority(Thread.MAX_PRIORITY);
		zelador.start();
		
		convidado1.start();
		convidado2.start();
//		convidado3.start();
//		convidado4.start();
	}
}