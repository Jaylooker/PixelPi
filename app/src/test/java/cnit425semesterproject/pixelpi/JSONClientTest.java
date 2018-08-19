package cnit425semesterproject.pixelpi;

import android.content.Context;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URISyntaxException;

/**
 * Created by jackb on 6/11/2018.
 */

//tests JSON client
@RunWith(MockitoJUnitRunner.class)
public class JSONClientTest {
    @Spy
    private WebSocketServer jsonServerMock;

    @Mock
    private JSONWebSocketClient jsonClientMock;

    @Mock
    private Context contextMock;

    @Test
    public void connecting() {

    }

    @Test
    public void send_message() {

    }

    @Test
    public void recieve_callback() {

    }
}
