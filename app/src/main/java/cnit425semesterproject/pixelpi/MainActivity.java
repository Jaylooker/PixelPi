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
import java.util.Iterator;
import java.util.List;


/*holds instance of client and communicates between fragments */
public class MainActivity extends AppCompatActivity implements SettingsFragmentListener, DeviceFragmentListener, EditFragmentListener, DeviceTaskCallback {

    private ImageButton btndevices;
    private ImageButton btnsettings;
  //  private ImageButton btnedit;
    private DeviceFragment devicefragment;
    private SettingsFragment settingsfragment;
    private EditFragment editfragment;
    private URI uri;
    private boolean connected;
    private JSONWebSocketClient client;
    private JSONWebSocketClientListener JSONclientlistener;
    private ArrayList<Device> devices;
    private Device selecteddevice;
    private String CURRENTTAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test_websocket not being used

        //activity references
        btndevices = (ImageButton) findViewById(R.id.btndevices);
        btnsettings = (ImageButton) findViewById(R.id.btnsettings);
       // btnedit = (ImageButton) findViewById(R.id.btnedit);

        selecteddevice = new Device();
        devices = new ArrayList<>();

        //testing passing device to fragment
        /*devices = new ArrayList<>();
        Device dummydevice = new Device("dummydevice");
        devices.add(dummydevice); */


        //fragment references
        devicefragment = new DeviceFragment();
        settingsfragment = new SettingsFragment();
        editfragment = new EditFragment();


        //put fragments in framelayout
        if (findViewById(R.id.fragcontainer) != null) {
            if(savedInstanceState != null) {
                return;
            }

            devicefragment.setArguments(getIntent().getExtras());

            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            fts.add(R.id.fragcontainer, devicefragment, DeviceFragment.DEVICES_FRAGMENT);
            fts.add(R.id.fragcontainer, settingsfragment, SettingsFragment.SETTINGS_FRAGMENT);
            fts.add(R.id.fragcontainer, editfragment, EditFragment.EDIT_FRAGMENT);

            fts.attach(devicefragment);
            fts.attach(settingsfragment);
            fts.attach(editfragment);

            fts.hide(settingsfragment);
            fts.hide(editfragment);
            fts.show(devicefragment);
            fts.commit();

            CURRENTTAG = DeviceFragment.DEVICES_FRAGMENT;
        }



        // TODO: 4/14/2018 Hide Fragments when switching between them 

        //define host and port for testing
        final String url = "192.168.0.106";
        final int port = 9000;

        //define listener
        JSONclientlistener = new JSONWebSocketClientListener() {
            @Override
            public void servermessage(final JSONObject jsonObject) {
                //update UI with callback
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                                    //update devices
                                    devicefragment.updatedevices(devices);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                });


            }
        };

        //called for testing, server is connecting automatically instead of by fragment
        //connect(url, port);

        //set onclick listeners for buttons
        btndevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchfragto(devicefragment);
            }
        });

        btnsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {switchfragto(settingsfragment);
            }
        });

      /*  btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchfragto(editfragment);
            }
        }); */

    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister listeners
        //testing dummy device with dummy device task
        //DeviceTask deviceTask = new DeviceTask("dummydevicetask");
        Device device = new Device("dummydevice");
        ArrayList<Device> deviceArrayList = new ArrayList<>();
        ArrayList<DeviceTask> deviceTaskArrayList = new ArrayList<>();
        //deviceTaskArrayList.add(deviceTask);
        device.setDeviceTasks(deviceTaskArrayList);
        deviceArrayList.add(device);
        //Testing
        //testing
        devicefragment.updatedevices(deviceArrayList);
        //testing

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

    //more MainActivity methods
    private void switchfragto(Fragment fragment)
    {
        Fragment currentfragment = getSupportFragmentManager().findFragmentByTag(CURRENTTAG); //get  current fragment in container
        
        //switch current CURRENTTAG
        if (fragment instanceof  DeviceFragment) {
            if(currentfragment instanceof  DeviceFragment) {
                return;
            }
            CURRENTTAG = DeviceFragment.DEVICES_FRAGMENT;
        }
        else if (fragment instanceof SettingsFragment) {
            if(currentfragment instanceof  SettingsFragment) {
                return;
            }
            CURRENTTAG = SettingsFragment.SETTINGS_FRAGMENT;
        }
        else if (fragment instanceof  EditFragment) {
            if(currentfragment instanceof  EditFragment) {
                return;
            }
            CURRENTTAG = EditFragment.EDIT_FRAGMENT;
        }


        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();


        fts.hide(currentfragment);
        fts.show(fragment);

        fts.commit();

    }

    /*public void displaycolors( /*rgbcolor array ) {
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

    }*/

    public JSONObject merge(JSONObject obj1, JSONObject obj2) throws JSONException {
        JSONObject merged = new JSONObject();
        JSONObject[] objects = {obj1, obj2};
        for (JSONObject obj: objects) {
            Iterator it = obj.keys();
            while(it.hasNext()) {
                String key = (String) it.next();
                merged.put(key, obj.get(key));
            }
        }
        return merged;
    }

    //Fragment callback methods

    //SettingsFragment
    @Override
    public void connect(String url, int port) {
        //connect to server
        //make client
        if (client != null)  { //if already have client, close and release current one
            client.close();
            client = null;
        }
        try {
            client = new JSONWebSocketClient(url, port, MainActivity.this, JSONclientlistener);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URISyntaxException", e.toString());
        }

        //try connecting to server and requesting available clients
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
    }

    @Override
    public void disconnect() {
        client.close();
        client = null;
    }

    //DeviceFragment
    @Override
    public void selecteddevice(Device device) {
            this.selecteddevice = device;
            switchfragto(editfragment); //switch before calling any methods
            editfragment.setSelecteddevice(device);

    }

    //EditFragment
    @Override
    public void updatedevice(Device device) {
        //reassign device to new device
        selecteddevice = device;
    }

    @Override
    public void activatedevicetask(DeviceTask deviceTask) {
        //send device task as JSON
        client.sendJSON(deviceTask.toJSON());
    }

    //Display Dialog
    @Override
    public void sendnewdevicetask(DeviceTask deviceTask) {
        ArrayList<DeviceTask> deviceTasks = selecteddevice.getDeviceTasks();
        deviceTasks.add(deviceTask);
        selecteddevice.setDeviceTasks(deviceTasks);

        //sending devticetask for now
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", "DISPLAY");
            jsonObject.put("send to", selecteddevice.getDevicecode()); //work on adding device code
            jsonObject.put("mode", deviceTask.getMode());
            /*
            switch (deviceTask.getMode()) {
                case display:
                    break;
            }
            */ //use switch, constants messing things up
            if(deviceTask.getMode().equals(DeviceTask.DISPLAY)) {
                DisplayTask displayTask = (DisplayTask) deviceTask;
                //jsonObject.put("task", displayTask.toJSON());
                jsonObject = merge(jsonObject, displayTask.toJSON());
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.sendJSON(jsonObject);
        Log.i("Sent device task", jsonObject.toString());

        //update device
        //devicefragment.updatedevice(selecteddevice);
    }

}