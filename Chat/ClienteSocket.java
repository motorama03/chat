package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket {

	public static void main(String[] args)throws IOException {
		
		//mais de 1 cliente usando
		//chat usando singleton
		//
		
		Scanner entrada = new Scanner(System.in);
		
		String texto = "";
		
		Socket cliente = null;
		
		PrintStream saida = null;
		
		try {
			cliente = new Socket("26.105.204.186",7000);
			
			saida = new PrintStream(cliente.getOutputStream());
			
			do {
				texto = entrada.nextLine();
				
				saida.println(texto);
			}while(!"sair".equals(texto));
		
		}catch(IOException e) {
			System.out.println("algo errado aconteceu.");
		}finally {
			cliente.close();
		}
		
		entrada.close();
	}
	
}
