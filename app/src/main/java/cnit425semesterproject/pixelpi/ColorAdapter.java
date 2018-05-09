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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by jackb on 4/21/2018.
 */

//Custom ArrayAdapter for Colors, used in SimpleDialog for each color
public class ColorAdapter extends ArrayAdapter<Color> {

    private ArrayList<Color> colors;
    private static Color color; //static for reuse of reference

    public ColorAdapter(Context context, ArrayList<Color> colors) {
        super(context, 0, colors);
        this.colors = colors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        color = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.color_layout_2, parent, false);
        }
        //using color_layout_2 for now, will switch to color_layout

        //set image to color
        //ImageView imageView = convertView.findViewById(R.id.imgcolor);
        //imageView.setBackgroundColor(color.toint());

        //using layout_2
        final EditText txtred = convertView.findViewById(R.id.txtred);
        final EditText txtgreen = convertView.findViewById(R.id.txtgreen);
        final EditText txtblue = convertView.findViewById(R.id.txtblue);

        /*add text change listener or focus listener*/

        // TODO: 4/23/2018 Fix focus switching to first edittext after done typing  
        txtred.addTextChangedListener(new TextWatcher() {
            boolean redchange;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(redchange) {
                    txtred.setText(charSequence);
                    redchange = false;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String red = txtred.getText().toString();
                if(!(red.isEmpty()))
                {
                    color.setRed(Integer.valueOf(red));
                }
                else
                {
                    color.setRed(0);
                }
            }
        });

        txtgreen.addTextChangedListener(new TextWatcher() {
            boolean greenchange;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(greenchange)
                txtgreen.setText(charSequence);
                greenchange = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String green = txtgreen.getText().toString();
                if(!(green.isEmpty()))
                {
                    color.setGreen(Integer.valueOf(green));
                }
                else
                {
                    color.setGreen(0);
                }
            }
        });

        txtblue.addTextChangedListener(new TextWatcher() {
            boolean bluechange;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(bluechange)
                {
                    txtblue.setText(charSequence);
                    bluechange = false;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String blue = txtblue.getText().toString();
                if(!(blue.isEmpty()))
                {
                    color.setBlue(Integer.valueOf(blue));
                }
                else
                {
                    color.setBlue(0);
                }
            }
        });

        //using layout_2

        // TODO: 4/21/2018 Set color of imageview

        return convertView;
    }

    //getters and setters
    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

}
