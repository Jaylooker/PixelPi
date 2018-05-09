package cnit425semesterproject.pixelpi;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * Created by jackb on 4/15/2018.
 */

//Subtype of DeviceTask Task for changing the display color of a lights
// TODO: 4/28/2018 consider changing name
public class SimpleTask extends DeviceTask {

    //get properties from DeviceTask
    @JsonProperty("rgb array")
    private ArrayList<Color> colors; //colors that determine the repeating pattern

    public SimpleTask(String mode, String name, int id, boolean activated, ArrayList<Color> colors, Context context) {
        super(mode, name, id, activated, context);
        this.colors = colors;
    }

    public SimpleTask(String name, ArrayList<Color> colors, Context context) {
        super(name, context);
        this.mode = context.getString(R.string.SIMPLE);
        this.colors = colors;
        //this.activated = false;
    }

}
