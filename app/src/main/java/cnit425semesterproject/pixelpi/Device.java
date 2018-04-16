package cnit425semesterproject.pixelpi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by jackb on 3/28/2018.
 */

public class Device {
    private String devicename;
    private String devicecode;
    private ArrayList<DeviceTask> deviceTasks;
    // TODO: figure out attributes

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

    public Device(String devicename) { //for testing
        this.devicename = devicename;
    }

    public Device(JSONObject jsonObject)
    {
        ArrayList<DeviceTask> deviceTasks = new ArrayList<>();
        String devicecode = "";

        try
        {
            devicecode = jsonObject.getString("device code"); //id for device code placeholder

            JSONArray tasks = jsonObject.getJSONArray("tasks");
            for (int i = 0; i < tasks.length(); i++)
            {
                JSONObject taskjson = tasks.getJSONObject(i);
                DeviceTask deviceTask = new DeviceTask(taskjson); //check instanceof and absract devicetask
                this.deviceTasks.add(deviceTask);
            }
            this.devicename = devicecode;
            this.devicecode = devicecode;



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
