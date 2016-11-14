package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient {
	private static DatagramSocket clientSocket;

	public static void main(String args[]) throws Exception {
		while (true) {
			// lê o buffer do teclado
			BufferedReader infoUser = new BufferedReader(new InputStreamReader(System.in));
            // cria um socket para o cliente
			clientSocket = new DatagramSocket();
			// pega o inderenço ip do cliente
			InetAddress enderecoIP = InetAddress.getByName("localhost");
            // array que vai guarda os bytes para envia ao servidor
			byte[] sendData = new byte[1024];
			// array que vai guarda os bytes que chegarem do servidor
			byte[] receiveData = new byte[1024];

			// pega o buffer lido do teclado e tranforma em uma String
			String dados = infoUser.readLine();
			// pega os bytes da String dados que contém os bytes lidos do teclado
			sendData = dados.getBytes();
			/* cria um pacote para envia ao servidor onde passamos o texto, o ip do cliente
			   e a porta que o servidor esta escutando  */
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, enderecoIP, 3000);
			// envia o pacote
			clientSocket.send(sendPacket);

			// aqui o cliente fica esperando um pacote
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			// aqui recebemnos um pacote que no caso sera mandado pelo servidor
			clientSocket.receive(receivePacket);
			//aqui pega a mensagem que o servidor mandou pelo pacote
			String dadosRecebidos = new String(receivePacket.getData());
			// mostra a mensagem 
			System.out.println("Do servidor: " + dadosRecebidos);
		}
	}
}