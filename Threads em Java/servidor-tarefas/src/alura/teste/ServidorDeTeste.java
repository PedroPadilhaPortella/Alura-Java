package alura.teste;

import alura.exception.TratadorDeExcecao;

public class ServidorDeTeste {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        Thread threadTeste = new Thread(new Runnable() {

            public void run() {
            	try {
            		
                System.out.println("Servidor começando, estaRodando = " + estaRodando );

                while(!estaRodando) {}
                
//                if(estaRodando) {
//                	throw new Exception("a thread que devia estar rodando deixou de responder");                	
//                }

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            	} catch(Exception e) {
            		throw new RuntimeException(e.getMessage());
            	}
            }
        });
        
        threadTeste.setUncaughtExceptionHandler(new TratadorDeExcecao());
        		
        threadTeste.start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;        
    }
}