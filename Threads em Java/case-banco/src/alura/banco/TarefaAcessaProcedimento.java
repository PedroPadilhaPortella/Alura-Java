package alura.banco;

public class TarefaAcessaProcedimento implements Runnable {
	
    private PoolDeConexao pool;
    private GerenciadorDeTransacao tx;

    public TarefaAcessaProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

	@Override
	public void run() {
		synchronized (pool) {
    		System.out.println("Abrindo uma conex�o");
    		pool.getConnection();
    	    
        	synchronized (tx) { 
        	    System.out.println("Executando a transa��o");
        	    tx.begin();
			}
    	}
	}
}