package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jackb on 4/15/2018.
 */

//Fragment used to connect app client to server (client object is stored in MainActivity)
public class SettingsFragment extends Fragment {

    //ui
    private View rootview;
    private EditText txturl;
    private EditText txtport;
    private Button btnconnect;
    private Button btndisconnect;

    //other variables
    private SettingsFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(rootview == null) {
            rootview = inflater.inflate(R.layout.settings_layout, container, false);
            //ui references
            txturl = rootview.findViewById(R.id.txturl);
            txtport = rootview.findViewById(R.id.txtport);
            btnconnect = rootview.findViewById(R.id.btnconnect);
            btndisconnect = rootview.findViewById(R.id.btndisconnect);

            //connect event callback to MainActivity
            btnconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tempurl = txturl.getText().toString();
                    int tempport = Integer.valueOf(txtport.getText().toString()); //only can input numbers

                    listener.connect(tempurl, tempport);
                }
            });

            //disconnect event callback to MainActivity
            btndisconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.disconnect();
                }
            });

            //testing autofill
            /*String strurl = "192.168.0.106";
            int intport = 9000;
            txturl.setText(strurl);
            txtport.setText(String.valueOf(intport));*/
            //testing autofill
        }

        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SettingsFragmentListener) context;
    }

    @Override
    public void onDestroyView() {
        if(rootview.getParent() != null) {
            ((ViewGroup)rootview.getParent()).removeView(rootview);
        }
        super.onDestroyView();
    }
}
