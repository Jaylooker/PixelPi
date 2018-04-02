package cnit425semesterproject.pixelpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.*;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private URI uri;
    private boolean connected;
    private JSONWebSocketClient client;
    private ListView lvdevices;
    private DeviceArrayAdapter deviceadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_websocket); //using Test Websocket while testing

        //testing
        Button btnconnect = (Button) findViewById(R.id.btnconnect);
        Button btntestjson = (Button) findViewById(R.id.btntestjson);
        //testing

        lvdevices = (ListView) findViewById(R.id.lvdevices);
        deviceadapter = new DeviceArrayAdapter(MainActivity.this, R.layout.device_layout);

        //connect to server
        //define URI
        final String host = "192.168.0.106";
        final int port = 9000;
        final JSONWebSocketClientListener listener = new JSONWebSocketClientListener() {
            @Override
            public void servermessage(final JSONObject jsonObject) {
                //update UI with callback

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("serverjson", jsonObject.toString());
                        //lvdevices.setAdapter(deviceadapter);
                    }
                });

                Log.i("Server", "sent JSON message");
            }
        };

        try {
            client = new JSONWebSocketClient(host, port, MainActivity.this, listener);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URISyntaxException", e.toString());
        }

        //using test resource
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put in thread off of UI
                Thread clientthread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client.connect();
                        Toast.makeText(getBaseContext(),"Connected pressed", Toast.LENGTH_SHORT).show();
                    /*
                    try {
                        connected = client.connectBlocking(); //try to connect
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    */

                    }
                });
                clientthread.run();
            }
        });

        btntestjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("cmd", "DISPLAY");
                    jsonObject.put("device code", "AD01");
                    jsonObject.put("send to", "NPO1");
                    JSONArray displaycolors = new JSONArray();
                    JSONArray rgbcolor1 = new JSONArray();
                    rgbcolor1.put(0, 255); //make into for loop
                    rgbcolor1.put(1, 0);
                    rgbcolor1.put(2, 0);

                    JSONArray rgbcolor2 = new JSONArray();
                    rgbcolor2.put(0, 0);
                    rgbcolor2.put(1, 255);
                    rgbcolor2.put(2, 0);

                    displaycolors.put(0, rgbcolor1);
                    displaycolors.put(1, rgbcolor2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                client.sendJSON(jsonObject);
            }
        });


    }

    // TODO: 4/1/2018 clean up client after closing app

    public void display( /*rgbcolor array*/ ) {

    }



}