package com.vsl.tutorial.UDP;
/**
 * 
 */


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @author Visual Studio Learn (http://visualstudiolearn.blogspot.ca/)
 *
 */
public class UDPServer {

	static int UDPPort;
	static String ServerIP;


	/***
	 * Return Main.
	 */

	public static void main(String[] args) {

		try {
			
			//Read Configuration 
			ServersList serverList = ConfigReader.readServerInfo(); 
			
			// Start Server in Thread, to receive client requests.
			ServerConfig config = serverList.serverConfigList.get(0);
			UDPPort =config.udpPort;
			ServerIP =config.serverIP;
			startUDPServer();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 *  Start UDP Server in Threads.
	 */
	public static void startUDPServer() {
		new Thread(new UDPResponder()).start();
	}


	/**
	 * Threaded class UDPResponder is basically an actual Server
	 * @author Visual Studio Learn (http://visualstudiolearn.blogspot.ca/)
	 *
	 */
	public static class UDPResponder implements Runnable {

		private DatagramSocket serverSocket;

		public void run() {
			try {
				serverSocket = new DatagramSocket(UDPPort);
				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];
				System.out.println(ServerIP + " UDP Server Is UP!");
				
				while (true) {
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					serverSocket.receive(receivePacket);
					String request = new String(receivePacket.getData());
					String[] requestArray = request.trim().split(":");
					String remoteClient = requestArray[0];
					String query = requestArray[1];
					System.out.println("SEVER: Request RECEIVED from "+remoteClient+", for Query " + query+ ".");
					
					InetAddress IPAddress = receivePacket.getAddress();
					int port = receivePacket.getPort();
					String capitalizedSentence = "This is an UDP Server example."; 
					sendData = capitalizedSentence.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	
}
