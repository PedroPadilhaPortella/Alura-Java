package alura.threads;

public class Main {

	public static void main(String[] args) {

		String nome = "Jef";
		
		Thread threadSignatures1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome));
		Thread threadSignatures2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome));
		Thread threadAuthors = new Thread(new TarefaBuscaTextual("autores.txt", nome));
		
		
		threadSignatures1.start();
		threadSignatures2.start();
		threadAuthors.start();
	}

}
