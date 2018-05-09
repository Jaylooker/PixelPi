/*
MIT License

        Copyright (c) 2018 Jack Francis Bartolone, Robert Benjamin Osborne
        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
        to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
        and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
        IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
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
