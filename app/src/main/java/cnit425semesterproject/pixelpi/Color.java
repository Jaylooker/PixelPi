package cnit425semesterproject.pixelpi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jackb on 4/22/2018.
 */

//Own implementation of color to store RGB values and convert to int when displaying as color for blankcolor.png
// TODO: 4/24/2018 consider extending android.graphics.Color
public class Color implements JSON {
    private int red;
    private int green;
    private int blue;

    //functions
    public int toint()
    {
        int color = android.graphics.Color.rgb(red, green, blue);
        return color;
    }

    //getters and setters
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    //constuctors
    public Color() {

    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = this.toJSONArray();
        try {
            jsonObject.put("colors", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONArray toJSONArray() {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put(0, this.red);
            jsonArray.put(1, this.green);
            jsonArray.put(2, this.blue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;

    }

}
