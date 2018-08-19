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

import android.support.v4.math.MathUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by jackb on 4/22/2018.
 */

//Own implementation of color to store RGB values and convert to int when displaying as color for blankcolor.png
// TODO: 4/24/2018 consider extending android.graphics.Color
public class Color {
    //colors array indices
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    //color array object
    private int[] colors;

    //functions
    public int toint()
    {
       return android.graphics.Color.rgb(colors[RED], colors[GREEN], colors[BLUE]);
    }

    //getters and setters
    public int getRed() {
        return colors[RED];
    }

    public void setRed(int red) {
        this.colors[RED] = MathUtils.clamp(red, 0, 255);
    }

    public int getGreen() {
        return colors[GREEN];
    }

    public void setGreen(int green) {
        this.colors[GREEN] = MathUtils.clamp(green, 0, 255);
    }

    public int getBlue() {
        return colors[BLUE];
    }

    public void setBlue(int blue) {
        this.colors[BLUE] = MathUtils.clamp(blue, 0, 255);
    }

    //constuctors
    public Color() {
        this.colors = new int[3];
    }

    @JsonCreator
    public Color(int red, int green, int blue) {
        this.colors = new int[] {MathUtils.clamp(red, 0, 255), MathUtils.clamp(green, 0, 255), MathUtils.clamp(blue, 0, 255)};
    }
}

