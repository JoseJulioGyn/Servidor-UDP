package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer {
	private static DatagramSocket serverSocket;

	public static void main(String args[]) throws Exception {
		// cria um socket para o servidor na porta 3000
		serverSocket = new DatagramSocket(3000);

		while (true) {
            // array que guardara os bytes do pacote recebido
			byte[] receiveData = new byte[1024];
		    // array que guardara os bytes do pacote enviado
			byte[] sendData = new byte[1024];

			// aqui o servidor fica esperando um pacote
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			// aqui recebemnos um pacote que no caso sera mandado pelo cliente
			serverSocket.receive(receivePacket);
			// pega o endereço ip do cliente
			InetAddress enderecoIp = receivePacket.getAddress();
			// pega a porta do cliente 
			int porta = receivePacket.getPort();
            // aqui pegamos a mensagem que o cliente envia no pacote
			String mensagemCliente = new String(receivePacket.getData());
			// mostra a mensagem
			System.out.println(enderecoIp + ": " + mensagemCliente);

			// aqui pegamos o texto digitado no lado do servidor
			String mensagemCapturada = new BufferedReader(new InputStreamReader(System.in)).readLine();
			// passamos os bytes do texto que a mensagemCapturada contém
			sendData = mensagemCapturada.getBytes();
			/* cria um pacote para envia ao cliente onde passamos o texto, o ip do servidor
			   e a porta que o cliente esta escutando  */
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, enderecoIp, porta);
			// envia o pacote
			serverSocket.send(sendPacket);
		}
	}
}