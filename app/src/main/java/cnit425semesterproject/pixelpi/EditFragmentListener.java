package cnit425semesterproject.pixelpi;

/**
 * Created by jackb on 4/21/2018.
 */

//used by EditFragment to send data back to MainActivity
public interface EditFragmentListener {
    void updatedevice(Device device);
    void activatedevicetask(DeviceTask deviceTask);
}