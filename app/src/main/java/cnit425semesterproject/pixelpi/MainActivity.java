package cnit425semesterproject.pixelpi;

import android.support.v4.app.Fragment; //API 11+ required
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/*holds instance of client and communicates between fragments */
public class MainActivity extends AppCompatActivity {

    private ImageButton btndevices;
    private ImageButton btnsettings;
    private DeviceFragment devicefragment;
    private SettingsFragment settingsfragment;
    private URI uri;
    private boolean connected;
    private JSONWebSocketClient client;
    private JSONWebSocketClientListener JSONclientlistener;
    private ArrayList<Device> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test_websocket not being used

        //activity references
        btndevices = (ImageButton) findViewById(R.id.btndevices);
        btnsettings = (ImageButton) findViewById(R.id.btnsettings);

        devices = new ArrayList<>();

        //testing passing device to fragment
        /*devices = new ArrayList<>();
        Device dummydevice = new Device("dummydevice");
        devices.add(dummydevice); */


        //fragment references
        devicefragment = new DeviceFragment();
        settingsfragment = new SettingsFragment();

        //put  devicefragment in framelayout
        if (findViewById(R.id.fragcontainer) != null) {
            if(savedInstanceState != null) {
                return;
            }

            devicefragment.setArguments(getIntent().getExtras());

            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            fts.add(R.id.fragcontainer, devicefragment, DeviceFragment.DEVICES_FRAGMENT);
            fts.add(R.id.fragcontainer, settingsfragment, SettingsFragment.SETTINGS_FRAGMENT);

            fts.hide(settingsfragment);
            fts.show(devicefragment);
            fts.commit();
        }

        // TODO: 4/14/2018 Hide Fragments when switching between them 

        //testing
        //devicefragment.updatedevices(devices);
        //testing

        //connect to server
        //define host and port
        final String host = "192.168.0.106";
        final int port = 9000;
        //define listener
        JSONclientlistener = new JSONWebSocketClientListener() {
            @Override
            public void servermessage(final JSONObject jsonObject) {
                //update UI with callback

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("serverjson recieved", jsonObject.toString());
                        Toast.makeText(getApplicationContext(), "serverjson recieved" + jsonObject.toString(), Toast.LENGTH_LONG).show();
                        if (jsonObject.has("devices")) //if receiving devices
                        {
                            try {
                                JSONArray jsondevices = jsonObject.getJSONArray("devices");
                                for (int i = 0; i < jsondevices.length(); i++)
                                {
                                    JSONObject jsondevice = jsondevices.getJSONObject(i);
                                    Device device = new Device(jsondevice);
                                    devices.add(device);
                                }
                                Log.i("Server device amount", String.valueOf(devices.size()));
                                Log.i("Tasks", devices.get(0).getDeviceTasks().get(0).toString()); //check first task
                                //devicefragment.updatedevices(devices); //update devices
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                });


            }
        };

        try {
            client = new JSONWebSocketClient(host, port, MainActivity.this, JSONclientlistener);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URISyntaxException", e.toString());
        }

        try {
            connected = client.connectBlocking();
            JSONObject jsonObject = new JSONObject(); //get available clients
            try {
                jsonObject.put("cmd", "VIEW AVAILABLE CLIENTS");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            client.sendJSON(jsonObject);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

;
        //Toast.makeText(getBaseContext(),"Connected", Toast.LENGTH_SHORT).show();

        /*
        try {
            connected = client.connectBlocking(); //try to connect
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */


        btndevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchfragto(devicefragment);
            }
        });

        btnsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchfragto(settingsfragment);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister listeners
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register listeners
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //clean up objects
        client.close();


    }

    private void switchfragto(Fragment fragment)
    {
         String TAG = "";
        if (fragment instanceof  DeviceFragment) {
            TAG = DeviceFragment.DEVICES_FRAGMENT;
        }
        //else if (fragment instanceof )

        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();

        Fragment currentfragment = getSupportFragmentManager().findFragmentById(R.id.fragcontainer); //get  current fragment in container
        fts.hide(currentfragment);
        fts.show(fragment);

        fts.commit();

    }

    // TODO: 4/12/2018 create function to send a JSON array of RGB colors of varying size

    public void displaycolors( /*rgbcolor array*/ ) {
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




}