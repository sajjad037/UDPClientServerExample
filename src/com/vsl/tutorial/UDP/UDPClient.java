package com.vsl.tutorial.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @author Visual Studio Learn (http://visualstudiolearn.blogspot.ca/)
 *
 */

public class UDPClient {
	/**
	 * Return Main.
	 */

	public static void main(String[] args) {

		try {
			new ConfigReader();
			// Read Configuration
			ServersList serverList = ConfigReader.readServerInfo(); 
						
			// Call Server
			ServerConfig config = serverList.serverConfigList.get(0);
			String reply = UPDRequester("Client", config.serverIP, config.udpPort, "Who are You ?");
			System.out.println("Client: Reply from Server ("+ config.serverIP+") : "+reply);
			

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Request a UDP Server
	 * 
	 * @param requester
	 * @param ip
	 * @param port
	 * @param query
	 * @return
	 */
	public static String UPDRequester(String requester, String ip, int port, String query) {
		String reply = "";
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(ip);
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			String request = requester + ":" + query;
			sendData = request.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			clientSocket.close();
			reply = modifiedSentence.trim();
		} catch (Exception ex) {
			reply = "Error: encouter, Message: " + ex.getMessage();
			ex.printStackTrace();
		}

		return reply;
	}

}
