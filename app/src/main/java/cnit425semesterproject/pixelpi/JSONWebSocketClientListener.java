package cnit425semesterproject.pixelpi;

import org.json.JSONObject;

/**
 * Created by jackb on 4/1/2018.
 */

public interface JSONWebSocketClientListener {
        void servermessage(JSONObject jsonObject);
}
