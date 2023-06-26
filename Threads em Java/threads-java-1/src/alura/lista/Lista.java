package alura.lista;

public class Lista {
	
    private String[] elementos = new String[100];
    private int indice = 0;

    public synchronized void add(String elemento) {
    	try {
	  		this.elementos[indice] = elemento;
	    	this.indice++;	
	    	Thread.sleep(10);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
    	
    	if(this.indice == this.size()) {
    		System.out.println("Lista esta cheia, notificando");
    		this.notify();    		
    	}
    } 

    public int size() {
        return this.elementos.length;
    }

    public String get(int posicao) {
        return this.elementos[posicao];
    }
    
    public boolean isFull() {
    	return this.indice == this.size();
    }
}