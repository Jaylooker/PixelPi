package cnit425semesterproject.pixelpi;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * Created by jackb on 3/28/2018.
 */

//Device class for Devices recieved from server. Devices persist on server's database.
public class Device {
    @JsonProperty("device name")
    private String devicename; // name of device, will be editable
    @JsonProperty("device code")
    private String devicecode; // unique identifier used by server

    private ArrayList<DeviceTask> deviceTasks; //device tasks that have been created

    //getter and setters
    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    @JsonGetter("tasks") //get when receiving
    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    public ArrayList<DeviceTask> getDeviceTasks() {
        return deviceTasks;
    }

    @JsonIgnore //ignore when sending
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
        this.deviceTasks = new ArrayList<>();
    }

}
