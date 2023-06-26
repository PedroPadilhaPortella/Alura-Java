package alura.banheiro;

public class Banheiro {
	
	private Boolean isDirty = true;
	
	public void mijar() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + ": Batendo na porta");
		
		synchronized(this) {
			try {
				System.out.println(nome + ": Entrando no banheiro");
				
				while(this.isDirty) {
					esperarLimparBanheiro(nome);					
				}
				
				System.out.println(nome + ": Mijando");
				Thread.sleep(5000);
				this.isDirty = true;
				System.out.println(nome + ": Dando descarga");
				System.out.println(nome + ": Lavando a mão");
				Thread.sleep(1000);
				System.out.println(nome + ": Saindo do banheiro");
				Thread.sleep(1000);
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cagar() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + ": Batendo na porta");
		
		synchronized(this) {
			try {
				System.out.println(nome + ": Entrando no banheiro");
				
				while(this.isDirty) {
					esperarLimparBanheiro(nome);					
				}
				
				System.out.println(nome + ": Cagando");
				Thread.sleep(10000);
				this.isDirty = true;
				System.out.println(nome + ": Dando descarga");
				Thread.sleep(2000);
				System.out.println(nome + ": Puts Entopiu kkkkk");
				Thread.sleep(2000);
				System.out.println(nome + ": Agora foi");
				Thread.sleep(1000);
				System.out.println(nome + ": Lavando a mão");
				Thread.sleep(1000);
				System.out.println(nome + ": Saindo do banheiro");	
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void limparBanheiro() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + ": Batendo na porta");
		
		synchronized(this) {
			try {
				if(this.isDirty) {
					System.out.println(nome + ": Limpando o banheiro");
					this.isDirty = false;
				} else {
					System.out.println(nome + ": O banheiro nao esta sujo");
				}
				
				Thread.sleep(5000);
				this.notifyAll();
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void esperarLimparBanheiro(String nome) throws InterruptedException {
		if(this.isDirty) {
			System.out.println(nome + ": Eca, o banheiro tá sujo");
			this.wait();
		}
	}
}
