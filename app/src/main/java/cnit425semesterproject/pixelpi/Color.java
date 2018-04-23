package cnit425semesterproject.pixelpi;

import org.json.JSONArray;

/**
 * Created by jackb on 4/22/2018.
 */

public class Color {
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

    public void toJSON()
    {
        //// TODO: 4/23/2018 made to JSON function implement in displaytask
        /*
        JSONArray colorarray = new JSONArray();
        colorarray.put(0, red);
        colorarray.put(1, green);
        colorarray.put(2, blue);
        */
    }

}
