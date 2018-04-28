package cnit425semesterproject.pixelpi;

import org.json.JSONObject;

/**
 * Created by jackb on 4/1/2018.
 */

//Used as callback for OnMessage by MainActivity
public interface JSONWebSocketClientListener {
        void servermessage(JSONObject jsonObject);
}
