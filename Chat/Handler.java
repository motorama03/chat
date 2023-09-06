package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Handler implements Runnable {
    private Socket cliente;
	private PrintStream clienteSaida;

    public Handler(Socket cliente, PrintStream clienteSaida) {
        this.cliente = cliente;
        this.clienteSaida = clienteSaida;
    }

    public void run() {
        try {
            Scanner entrada = new Scanner(cliente.getInputStream());

            String texto = "";
            do {
                texto = entrada.nextLine();
                System.out.println(texto);
                enviarParaTodos(texto);
              
            } while (!"sair".equals(texto));
            ServidorSocket.clientes.remove(clienteSaida);

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void enviarParaTodos(String mensagem) {
        synchronized (ServidorSocket.clientes) {
            for (PrintStream cliente : ServidorSocket.clientes) {
                cliente.println(mensagem);
            }
        }
    }
}
