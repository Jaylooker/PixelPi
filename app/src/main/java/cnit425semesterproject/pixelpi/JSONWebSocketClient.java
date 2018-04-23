package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jackb on 4/1/2018.
 */

//Websocket Documentation
//https://www.javadoc.io/doc/org.java-websocket/Java-WebSocket/1.3.8

public class JSONWebSocketClient extends WebSocketClient /*implements Runnable*/ {
    private Context context;
    private JSONObject servermessage;
    private JSONWebSocketClientListener listener;

    /*public JSONWebSocketClient(URI serverURI) {
        super(serverURI);
    }*/

    public JSONWebSocketClient(String host, int port, Context context, JSONWebSocketClientListener listener) throws URISyntaxException {
        super(new URI("ws://" + host + ":" + Integer.toString(port)));
        this.context = context;
        this.listener = listener;
    }

    //connect() and connectBlocking() start in background thread

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Toast.makeText(context, "Connected to server " + "\n" + handshakedata.toString(), Toast.LENGTH_SHORT).show();
        //register device
        JSONObject registerdevice = new JSONObject();
        try {
            registerdevice.put("cmd", "REGISTER DEVICE");
            registerdevice.put("device code", "AD01");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.sendJSON(registerdevice);
        // TODO: 4/1/2018 device is not registering once 'connected', need to send once connected 
    }

    @Override
    public void onMessage(String message) {
        //message received from server
        Log.i("serverjson recieved", message);
        //parse JSON
        try {
            servermessage = new JSONObject(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //send callback
        listener.servermessage(servermessage);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {
        if(ex instanceof JSONException)
        {
            Log.e("ClientException", "JSONException");
        }

        if (ex instanceof IOException)
        {
            Log.e("ClientException", "IOException");
        }

    }

    public void sendJSON(JSONObject jsonObject)
    {
        //send string of json
        String stringjson = jsonObject.toString();
        this.send(stringjson);

        Log.i("sent json", stringjson);
    }

}

