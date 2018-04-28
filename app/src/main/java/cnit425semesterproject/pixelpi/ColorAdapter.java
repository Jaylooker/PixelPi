package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.graphics.ColorFilter;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jackb on 4/21/2018.
 */

//Custom ArrayAdapter for Colors, used in DisplayDialog for each color
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_color_layout_2, parent, false);
        }
        //using display_color_layout_2 for now, will switch to display_color_layout

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
