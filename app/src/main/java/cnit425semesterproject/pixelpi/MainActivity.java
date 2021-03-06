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

import android.support.v4.app.Fragment; //API 11+ required
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: 4/28/2018 For future:
/*
Update Readme
 */

/*Holds instance of client and communicates between fragments.
  Fragments are android.support.v4.app.Fragment which are only supported by API 11+ instead
  on android.app.Fragment which is older and original implementation.
  Edit button not in use. May be replaced by another button to different fragment
* */
public class MainActivity extends AppCompatActivity implements SettingsFragmentListener, DeviceFragmentListener, EditFragmentListener, DeviceTaskCallback {

    private ImageButton btndevices;
    private ImageButton btnsettings;
    private DeviceFragment devicefragment;
    private SettingsFragment settingsfragment;
    private EditFragment editfragment;
    private ObjectMapper objectMapper;
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
        //ui
        btndevices = (ImageButton) findViewById(R.id.btndevices);
        btnsettings = (ImageButton) findViewById(R.id.btnsettings);

        //other variables
        selecteddevice = new Device();
        devices = new ArrayList<>();
        objectMapper = new ObjectMapper();
        connected = false;

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
            fts.add(R.id.fragcontainer, devicefragment, getString(R.string.DEVICES_FRAGMENT));
            fts.add(R.id.fragcontainer, settingsfragment, getString(R.string.SETTINGS_FRAGMENT));
            fts.add(R.id.fragcontainer, editfragment, getString(R.string.EDIT_FRAGMENT));

            fts.attach(devicefragment);
            fts.attach(settingsfragment);
            fts.attach(editfragment);

            fts.hide(settingsfragment);
            fts.hide(editfragment);
            fts.show(devicefragment);
            fts.commit();

            CURRENTTAG = getString(R.string.DEVICES_FRAGMENT);
        }



        // TODO: 4/14/2018 Hide Fragments when switching between them 

        //define host and port for testing
        //final String url = "192.168.0.106";
        //final int port = 9000;

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
                                    Device device = objectMapper.readValue(jsondevice.toString(), Device.class);
                                    devices.add(device);
                                }
                                    Log.i("Server device amount", String.valueOf(devices.size()));
                                    //update devices
                                    devicefragment.updatedevices(devices);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
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
    //switch between fragments using tags
    private void switchfragto(Fragment fragment)
    {
        Fragment currentfragment = getSupportFragmentManager().findFragmentByTag(CURRENTTAG); //get  current fragment in container
        
        //switch current CURRENTTAG
        if (fragment instanceof  DeviceFragment) {
            if(currentfragment instanceof  DeviceFragment) {
                return;
            }
            CURRENTTAG = getString(R.string.DEVICES_FRAGMENT);
        }
        else if (fragment instanceof SettingsFragment) {
            if(currentfragment instanceof  SettingsFragment) {
                return;
            }
            CURRENTTAG = getString(R.string.SETTINGS_FRAGMENT);
        }
        else if (fragment instanceof  EditFragment) {
            if(currentfragment instanceof  EditFragment) {
                return;
            }
            CURRENTTAG = getString(R.string.EDIT_FRAGMENT);
        }

        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();

        fts.hide(currentfragment);
        fts.show(fragment);

        fts.commit();
    }

    //Fragment callback methods

    //SettingsFragment
    //connect to server
    @Override
    public void connect(String url, int port) {
        //disconnect if necessary
        disconnect();
        //make client
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

    //if already have client, close and release current one
    @Override
    public void disconnect() {
        if (connected) {
            client.close();
            client = null;
            connected = false;
        }
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
        //set device task to activated
        //
        //send device task as JSON
       /* JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = objectMapper.
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        client.sendJSON(jsonObject);
        */
    }

    //Display Dialog
    @Override
    public void sendnewdevicetask(DeviceTask deviceTask) {
        ArrayList<DeviceTask> deviceTasks = selecteddevice.getDeviceTasks();
        deviceTasks.add(deviceTask);
        selecteddevice.setDeviceTasks(deviceTasks);
        Device sentdevice = selecteddevice;

        //sending devticetask for now
        //try {
            //Will be handled by Jackson Library
            //jsonObject.put("cmd", "SIMPLE"); //updated to simple
            //jsonObject.put("send to", selecteddevice.getDevicecode()); //work on adding device code
            //jsonObject.put("mode", deviceTask.getMode());


            if(deviceTask.getMode().equals(getString(R.string.SIMPLE))) {
                SimpleTask simpleTask = (SimpleTask) deviceTask;
                ArrayList<DeviceTask> sentdevicetasks = new ArrayList<>();
                sentdevicetasks.add(simpleTask);
                sentdevice.setDeviceTasks(deviceTasks);

                //map with special mapper for jsonObject.put values above
                //jsonObject.put("task", simpleTask.toJSON());
                /*JSONObject simplejson = new JSONObject();
                try {
                    objectMapper. //simpleTask
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                }
                */
            }
            else if(deviceTask.getMode().equals(getString(R.string.TIMER))) {
                /*
                Timer Task task code
                 */
            }
            else if(deviceTask.getMode().equals(getString(R.string.SHIMMER))){
                /*
                Shimmer task code
                 */
            }
            else {

            }

        /*} catch (JSONException e) {
            e.printStackTrace();
        }*/
        //client.sendJSON(jsonObject);
        //Log.i("Sent device task", jsonObject.toString());

        //update device
        //devicefragment.updatedevice(selecteddevice);
    }

}