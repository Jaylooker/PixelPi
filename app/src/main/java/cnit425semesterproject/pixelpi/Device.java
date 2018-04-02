package cnit425semesterproject.pixelpi;


import org.json.JSONObject;
import java.net.URI;

/**
 * Created by jackb on 3/28/2018.
 */

public class Device {
    private String devicename;
    private String devicecode;
    private URI macaddress;
    // TODO: figure out attributes

    //getter and setters
    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public URI getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(URI macaddress) {
        this.macaddress = macaddress;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    //constructors
    public Device()
    {

    }

    public Device(String devicename, URI macaddress, String devicecode) {
        this.devicename = devicename;
        this.macaddress = macaddress;
        this.devicecode = devicecode;
    }

    public Device(JSONObject jsonObject)
    {
        String devicename; // =
        URI macaddress; // = new URI();
        String devicecode; //=

        //this.devicename = devicename;
        //this.macaddress = macaddress;
        //this.devicecode = devicecode;
    }
}
