package cnit425semesterproject.pixelpi;

import org.json.JSONArray;
import org.json.JSONException;
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

    public DisplayTask(String name, ArrayList<Color> colors) {
        super(name);
        this.mode = DeviceTask.DISPLAY;
        this.colors = colors;
        //this.activated = false;

    }

    public DisplayTask(JSONObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public JSONObject toJSON() {
        //get previous values
        JSONObject jsonObject = super.toJSON();
        //put new values
        try {
        JSONArray jsoncolors = new JSONArray();
        for (int i = 0; i < colors.size(); i++) {
            Color color = colors.get(i);
            // TODO: 4/23/2018 implement color.tojson 
            //color.toJSON();
            int red = color.getRed();
            int blue = color.getBlue();
            int green = color.getGreen();
            JSONArray colorarray = new JSONArray();
            colorarray.put(0, red);
            colorarray.put(1, green);
            colorarray.put(2, blue);

            jsoncolors.put(i, colorarray);
        }

            jsonObject.put("rgb array", jsoncolors);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}
