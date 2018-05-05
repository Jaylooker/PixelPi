package cnit425semesterproject.pixelpi;

/**
 * Created by jackb on 4/21/2018.
 */

//Call for display dialog that will update the addition of a new device task to the selected device
// (Task specific Dialog) -> SimpleDialog -> EditFragment -> MainActivity
public interface DeviceTaskCallback {
    void sendnewdevicetask(DeviceTask deviceTask);
}
