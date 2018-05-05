package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment;

import java.util.ArrayList;

/**
 * Created by jackb on 4/21/2018.
 */

//Display dialog used to pick which device task to make and by extension what dialog to display next
// TODO: 4/28/2018 consider changing name
public class SimpleDialog extends DialogFragment {

    //ui elements
    private View rootview;
    private View footer;
    private Button btncancel;
    private Button btndone;
    private EditText txtsimpletaskname;
    private ImageView imgplus;
    private ListView lvcolors;
    private ColorAdapter colorAdapter;

    //other elements
    private SimpleTask simpleTask;
    private DeviceTaskCallback callback;
    private ArrayList<Color> colors;
    private ColorPickerDialogFragment colorPickerDialogFragment;

    public SimpleDialog() {
        //do not use to construct dialog
    }

    //constructor method
    public static SimpleDialog newInstance() {
        Bundle args = new Bundle();
        SimpleDialog fragment = new SimpleDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Color color = new Color(); //first empty color
        colors = new ArrayList<>();
        colors.add(color);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootview == null) {
            rootview = inflater.inflate(R.layout.simple_dialog_layout, container, false);
        }

        //get ui reference
        lvcolors = rootview.findViewById(R.id.lvcolors);
        btncancel = rootview.findViewById(R.id.btncancel);
        btndone = rootview.findViewById(R.id.btndone);
        txtsimpletaskname = rootview.findViewById(R.id.txtsimpletaskname);
        colorAdapter = new ColorAdapter(getActivity(), colors);
        //footer
        footer = inflater.inflate(R.layout.listview_plus_footer, container, false);
        imgplus = footer.findViewById(R.id.imgplus);

        //add footer
        lvcolors.addFooterView(footer);
        //add listview listener
        lvcolors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //launch color dialog
            }
        });
        //set adapter
        lvcolors.setAdapter(colorAdapter);

        //add footer listner
        imgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch color dialog
                colorPickerDialogFragment = new ColorPickerDialogFragment();
                //android.graphics.Color color = new android.graphics.Color();
                colorPickerDialogFragment.newInstance(0, "Choose a color", "Ok" , /*color int */0, true);
                //colorPickerDialogFragment
                //colorPickerDialogFragment.show(getFragmentManager(), "color dialog"); // TODO: 4/21/2018 fingure out .show
                Color color = new Color(0,0,0);
                colors.add(color);
                colorAdapter.notifyDataSetChanged();
                //add color to arrayadapter/list
                //colorAdapter.notifyDataSetChanged();
            }
        });

        //add button listeners
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss dialog
                getDialog().dismiss();
            }
        });

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //once oked callback data to send to server
                String taskname = txtsimpletaskname.getText().toString();
                //update adapter
                colorAdapter.notifyDataSetChanged();
                //create task to send to server
                simpleTask = new SimpleTask(taskname, colorAdapter.getColors(), getActivity().getApplicationContext());
                //callback to server
                callback.sendnewdevicetask(simpleTask);
                //dismiss dialog
                getDialog().dismiss();
            }
        });

        //testing
        //String dummytask = "dummytask";
        //txtsimpletaskname.setText(dummytask);
        //testing
        return rootview;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

}

    //making dialog fullscreen
    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context); //check whether callback is attached
        try
        {
            callback = (DeviceTaskCallback) context;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException(context.toString());
        }

    }
}
