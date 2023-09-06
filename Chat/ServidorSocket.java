package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorSocket {
	static List<PrintStream> clientes = new ArrayList<>();
	public static void main(String [] args) throws IOException {
		 
		
		ServerSocket servidor = null;
		
		Socket conexao = null;
		
		 try {
	            servidor = new ServerSocket(7000);
	            while (true) {
	                conexao = servidor.accept();
	                System.out.println("cliente " + conexao.getInetAddress().getHostAddress()+ " entrou");

	                PrintStream saida = new PrintStream(conexao.getOutputStream());
	                clientes.add(saida);
	                
	                Thread clienteThread = new Thread(new Handler(conexao,saida));
	                clienteThread.start();
	            }

	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } finally {
	                servidor.close();
	        }
			
		}
		
}
