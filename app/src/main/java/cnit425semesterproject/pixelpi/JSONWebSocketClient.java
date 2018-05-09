/*
MIT License

        Copyright (c) 2018 Jack Francis Bartolone, Robert Benjamin Osborne
        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
        to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
        and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
        IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jackb on 4/1/2018.
 */

//JSON WebSocket client extended from java provided Web Socket client
//Websocket Documentation
//https://www.javadoc.io/doc/org.java-websocket/Java-WebSocket/1.3.8
// TODO: 4/28/2018 consider encrypting JSONobject sent as bytes using Jackson API with wss
public class JSONWebSocketClient extends WebSocketClient /*implements Runnable*/ {
    private Context context;
    private JSONObject servermessage;
    private JSONWebSocketClientListener listener;

    public JSONWebSocketClient(String host, int port, Context context, JSONWebSocketClientListener listener) throws URISyntaxException {
        super(new URI("ws://" + host + ":" + Integer.toString(port)));
        this.context = context;
        this.listener = listener;
    }

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
        //Error logging
        if(ex instanceof JSONException)
        {
            Log.e("ClientException", "JSONException");
        }

        if (ex instanceof IOException)
        {
            Log.e("ClientException", "IOException");
        }

    }

    //send stringified JSON in plain text
    public void sendJSON(JSONObject jsonObject)
    {
        String stringjson = jsonObject.toString();
        this.send(stringjson);

        Log.i("sent json", stringjson);
    }

}

