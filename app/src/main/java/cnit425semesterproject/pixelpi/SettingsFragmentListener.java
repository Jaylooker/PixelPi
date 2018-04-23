package cnit425semesterproject.pixelpi;

/**
 * Created by jackb on 4/21/2018.
 */

public interface SettingsFragmentListener {
    void connect(String url, int port);
    void disconnect();
}
