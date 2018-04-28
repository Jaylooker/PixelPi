package cnit425semesterproject.pixelpi;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jackb on 4/15/2018.
 */

//Subtype of DeviceTask Task for changing the display color of a lights
// TODO: 4/28/2018 consider changing name
public class DisplayTask extends DeviceTask {

    private ArrayList<Color> colors; //colors that determine the repeating pattern

    public DisplayTask(String mode, String name, int id, boolean activated, ArrayList<Color> colors) {
        super(mode, name, id, activated);
        this.colors = colors;
    }

    public DisplayTask(String name, ArrayList<Color> colors) {
        super(name);
        this.mode = Resources.getSystem().getString(R.string.SIMPLE);
        this.colors = colors;
        //this.activated = false;
    }

    // TODO: 4/24/2018 Add more DisplayTask specific variables to constructor
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
            JSONArray colorarray = color.toJSONArray();
            jsoncolors.put(i, colorarray);
        }

            jsonObject.put("rgb array", jsoncolors);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}
