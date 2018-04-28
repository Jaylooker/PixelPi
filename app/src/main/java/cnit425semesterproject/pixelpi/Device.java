package cnit425semesterproject.pixelpi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by jackb on 3/28/2018.
 */

//Device class for Devices recieved from server. Devices persist on server's database.
public class Device implements JSON {
    private String devicename; // name of device, will be editable
    private String devicecode; // unique identifier used by server
    private ArrayList<DeviceTask> deviceTasks; //device tasks that have been created

    //getter and setters
    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    public ArrayList<DeviceTask> getDeviceTasks() {
        return deviceTasks;
    }

    public void setDeviceTasks(ArrayList<DeviceTask> deviceTasks) {
        this.deviceTasks = deviceTasks;
    }

    //constructors
    public Device()
    {

    }

    public Device(String devicename, String devicecode, ArrayList<DeviceTask> deviceTasks) {
        this.devicename = devicename;
        this.deviceTasks = deviceTasks;
        this.devicecode = devicecode;
    }

    //for testing and creating loading new device
    public Device(String devicename) {
        this.devicename = devicename;
        this.devicecode = devicename;
        this.deviceTasks = new ArrayList();
    }

    public Device(JSONObject jsonObject)
    {
        Log.i("JSON Device", jsonObject.toString());
        ArrayList<DeviceTask> deviceTasks = new ArrayList<>();
        String devicecode = "";

        try
        {
            devicecode = jsonObject.getString("device code"); //id for device code placeholder

            if (jsonObject.has("tasks")) { //check if has tasks
                JSONArray tasks = jsonObject.getJSONArray("tasks");
                for (int i = 0; i < tasks.length(); i++)
                {
                    JSONObject taskjson = tasks.getJSONObject(i);
                    DeviceTask deviceTask = new DeviceTask(taskjson); //check instanceof and absract devicetask
                    deviceTasks.add(deviceTask);
                }
            }
            this.devicename = devicecode;
            this.devicecode = devicecode;
            this.deviceTasks = deviceTasks;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //interfaces
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("device name", this.devicename);
            jsonObject.put("device code", this.devicecode);
            JSONArray jsonArray = new JSONArray();
            int i = 0;
            for (DeviceTask  deviceTask: this.deviceTasks) {
                jsonArray.put(i, deviceTask.toJSON());
                i++;
            }
            jsonObject.put("tasks", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
