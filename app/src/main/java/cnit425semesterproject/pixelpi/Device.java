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
