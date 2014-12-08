package client.main;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import server.command.CatanCommand;
import server.command.SendChat;
import shared.serialization.parameters.SendChatParameters;

public class testMain {

	public static void main(String[] args) {
		
		
		//Serialize
		SendChat command = new SendChat(new SendChatParameters(0, "Hey"), 0);
		
		Gson gson = new Gson();
		String command_blob = gson.toJson(command);
		
		JsonArray final_array = new JsonArray();
		
		JsonElement type_element = gson.toJsonTree("server.command.SendChat");
		final_array.add(type_element);
		
		JsonElement blob_element = gson.toJsonTree(command_blob);
		final_array.add(blob_element);
		
		
		
		//Deserialize
		JsonParser parser = new JsonParser();
		
		JsonArray o = parser.parse(final_array.toString()).getAsJsonArray();
		
		String n_type = o.get(0).getAsString();
		String new_blob = o.get(1).getAsString();
		
		System.out.println("Read type: " + n_type);
		System.out.println("Read blob: " + new_blob);
		
		try {
			CatanCommand new_command = (CatanCommand) gson.fromJson(new_blob, Class.forName(n_type));
			new_command.execute();
			System.out.println(new_command.wasSuccessful());
		} 
		catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
