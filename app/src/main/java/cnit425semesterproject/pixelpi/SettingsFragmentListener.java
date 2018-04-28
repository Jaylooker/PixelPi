package cnit425semesterproject.pixelpi;

/**
 * Created by jackb on 4/21/2018.
 */

//Used by Settings Fragment to pass methods and data back to MainActivity container
public interface SettingsFragmentListener {
    void connect(String url, int port);
    void disconnect();
}
