package cnit425semesterproject.pixelpi;

import android.graphics.Color;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jackb on 4/15/2018.
 */

public class DisplayTask extends DeviceTask {

    private ArrayList<Color> colors;

    public DisplayTask(String mode, String name, int id, boolean activated, ArrayList<Color> colors) {
        super(mode, name, id, activated);
        this.colors = colors;
    }


    public DisplayTask(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;

    }
}
