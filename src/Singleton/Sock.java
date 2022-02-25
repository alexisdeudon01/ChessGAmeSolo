package Singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Game.Board.RequestChessBoardMessage;
import Game.Board.ViewChessBoard;
import Game.Board.Pieces.Piece;
import Game.Chat.ChatEntity;

import javax.xml.bind.DatatypeConverter;

public class Sock {
	// static variable single_instance of type Singleton
	private static Sock single_instance = null;
	// Http Client
	private DefaultHttpClient Client;
	// Based URL
	private String url;

	private String username;
	// private constructor restricted to this class itself
	private Sock() {
		// Create new client
		this.Client = getThreadSafeClient();
		// Save url of server
		this.url = "http://192.168.178.52:9000";
	}

	public String readMsg(HttpResponse response) {
		BufferedReader breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder responseString = new StringBuilder();
		String line = "";
		try {
			while ((line = breader.readLine()) != null) {
				responseString.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			breader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String repsonseStr = responseString.toString();
		return repsonseStr;
	}

	// static method to create instance of Singleton class
	public static Sock getInstance() {
		if (single_instance == null)
			single_instance = new Sock();

		return single_instance;
	}

	public boolean createUser(User user) throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/users/addUser");
		//activate json
		httpPost.setHeader("Content-type", "application/json");
		//Creating json
		StringEntity postingString = new StringEntity(gson.toJson(user));//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response=this.Client.execute(httpPost);
		//Read response
		String msg=readMsg(response);
		//Convert String to JSON
		Boolean status = Boolean.parseBoolean((String)new JSONObject(msg).get("success"));
		System.out.println("status is "+status);
		return status;

	}
	
	public String[][] getAvailableGameplay() throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		HttpGet httpGet = new HttpGet(url+"/pregameplay/getGameAvailable");
		HttpResponse response = Client.execute(httpGet);
		String[][] userArray = gson.fromJson(readMsg(response), String[][].class);  
		for(int i=0;i<userArray.length;i++) {
			System.out.println("Player available "+userArray[i]);
		}
		
		return userArray;
		
	}
	
	public int  createGame() {
		Gson gson = new Gson();
		HttpGet httpGet = new HttpGet(url+"/pregameplay/createGame");
		HttpResponse response = null;
		try {
			response = Client.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonParser parser = new JsonParser();
		JsonObject element = (JsonObject)parser.parse(readMsg(response));
		JsonElement responseWrapper = element.get("id");
		
		int id = gson.fromJson(responseWrapper, Integer.class);
		System.out.println(id);
		return id;
		
	}
	
	public String join(int gameId) throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/pregameplay/join");
		//Creating json
		StringEntity postingString = new StringEntity(Integer.toString(gameId));//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response=this.Client.execute(httpPost);
		//Read response
		String msg=readMsg(response);
		//Convert String to JSON
		String opponent = (String)new JSONObject(msg).get("found");
		return opponent;
	}
	
	public String checkIfOpponentFound(int gameId) {
		Gson gson=new Gson();
		HttpPost httpPost=new HttpPost(this.url+"/pregameplay/checkIfOpponentFound");
		//activate json
		//httpPost.setHeader("Content-type", "application/json");
		//Creating json
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(Long.toString(gameId));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response = null;
		String msg = null;
		try {
			response = this.Client.execute(httpPost);
			msg=readMsg(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonParser parser = new JsonParser();
		JsonObject element = (JsonObject)parser.parse(msg);
		JsonElement responseWrapper = element.get("found");
		
		Boolean found = gson.fromJson(responseWrapper, Boolean.class);
		if(found) {
			JsonParser parser2 = new JsonParser();
			JsonObject element2 = (JsonObject)parser2.parse(msg);
			JsonElement responseWrapper2 = element2.get("gamer");
			
			String gamer = gson.fromJson(responseWrapper2, String.class);
			return gamer;
		}
		return null;

	}
	public ChatEntity getLastMessage(int gameid) {
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/chat/getLastMessage");
		//Creating json
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(Integer.toString(gameid));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response = null;
		try {
			System.out.println("Sending request");
			response = this.Client.execute(httpPost);
			//Read response
			String msg1=readMsg(response);
			System.out.println("Answer is "+msg1);
			ChatEntity target2 = gson.fromJson(msg1, ChatEntity.class); 
			System.out.println("We just receive new message -> "+target2);
			return target2;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void sendMessageChat(ChatEntity msg) {
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/chat/sendMsg");
		//activate json
		httpPost.setHeader("Content-type", "application/json");
		//Creating json
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(gson.toJson(msg));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response = null;
		try {
			response = this.Client.execute(httpPost);
			//Read response
			String msg1=readMsg(response);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendUpdateGame(LinkedList<Piece> listPieces) {
		//Convert to view chess board
		ViewChessBoard viewChessBoard=new ViewChessBoard(Game.getInstance().isGameCreation(),Game.getInstance().getGameid(),listPieces);
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/game/sendView");
		//activate json
		httpPost.setHeader("Content-type", "application/json");
		//Creating json
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(gson.toJson(viewChessBoard).toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response = null;
		try {
			response = this.Client.execute(httpPost);
			//Read response
			String msg1=readMsg(response);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LinkedList<Piece> getNewViewChessBoard(int gameid, boolean white){
		RequestChessBoardMessage requestChessBoardMessage=new RequestChessBoardMessage(gameid, white);
		Gson gson = new Gson();
		//Creating post request
		HttpPost httpPost=new HttpPost(this.url+"/game/askingNextView");
		//activate json
		httpPost.setHeader("Content-type", "application/json");
		//Creating json
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(gson.toJson(requestChessBoardMessage));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//gson.tojson() converts your pojo to json
		//Binding json data to request
		httpPost.setEntity(postingString);
		//Send request
		HttpResponse response = null;
		try {
			System.out.println("Sending request for view");
			response = this.Client.execute(httpPost);
			//Read response
			String msg1=readMsg(response);
			System.out.println("Answer is for view "+msg1);
			ViewChessBoard target2 = gson.fromJson(msg1, ViewChessBoard.class); 
			System.out.println("We just receive new message for view-> "+target2);
			return target2.getChessBoard();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean login(String username, String password) throws Exception {

			// Creating request
			HttpGet httpGet = new HttpGet(url);
			String encoding = DatatypeConverter.printBase64Binary((username + ":" + password).getBytes("UTF-8"));
			httpGet.setHeader("Authorization", "Basic " + encoding);
			// Sending request
			HttpResponse response = Client.execute(httpGet);
			// Checking status code
			// If 401 ko ok otherwise
			readMsg(response);
			if (response.getStatusLine().toString().contains("401")) {
				return false;
			}
			this.username=username;
			return true;



	}
	public void exitGamePlay() {
		Gson gson = new Gson();
		HttpGet httpGet = new HttpGet(url+"/pregameplay/exitGamePlay");
		HttpResponse response = null;
		try {
			response = Client.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	public static DefaultHttpClient getThreadSafeClient() {

	    DefaultHttpClient client = new DefaultHttpClient();
	    ClientConnectionManager mgr = client.getConnectionManager();
	    HttpParams params = client.getParams();

	    client = new DefaultHttpClient(
	        new ThreadSafeClientConnManager(params,
	            mgr.getSchemeRegistry()), params);

	    return client;
	}
}
