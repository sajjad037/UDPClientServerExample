package com.vsl.tutorial.UDP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

/**
 * This class is for saving a data
 * 
 * @author Visual Studio Learn (http://visualstudiolearn.blogspot.ca/)
 *
 */
public class ConfigReader {
	private static ServersList serversConfigurations;

	/**
	 * Get Server Configuration list.
	 * 
	 * @return
	 */
	public static ServersList readServerInfo() {
		if (serversConfigurations == null) {
			serversConfigurations = (new ConfigReader()).LoadServerConfigurations();
		}
		return serversConfigurations;
	}

	/**
	 * this method opens up an Object from file
	 * 
	 * @param new_file
	 * @return
	 */
	public ServersList LoadServerConfigurations() {

		String fileContent = "";
		try {
			fileContent = new String(Files.readAllBytes(Paths.get((new File("App.Config")).getPath())));
			return (ServersList) getObjectFromJson(fileContent, ServersList.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * this method gets json from object
	 * 
	 * @param new_object
	 *            new object
	 * @return json converts gson to json and returns it
	 */
	public String getJsonFromObject(Object new_object) {
		Gson gson = new Gson();
		return gson.toJson(new_object);
	}

	/**
	 * this methods gets object from a json
	 * 
	 * @param new_jsonString
	 *            json string object
	 * @param new_class
	 *            new clas
	 * @return object object from json
	 */
	public Object getObjectFromJson(String new_jsonString, Class<?> new_class) {
		Gson gson = new Gson();
		return gson.fromJson(new_jsonString, new_class);
	}

}
