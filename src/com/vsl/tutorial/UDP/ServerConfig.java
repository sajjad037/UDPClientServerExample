package com.vsl.tutorial.UDP;


/**
 * Server Configuration Model
 * @author Visual Studio Learn (http://visualstudiolearn.blogspot.ca/)
 *
 */
public class ServerConfig {

	public String serverIP;
	public int udpPort;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerConfig [serverIP=" + serverIP + ", udpPort=" + udpPort + "]";
	}

}
