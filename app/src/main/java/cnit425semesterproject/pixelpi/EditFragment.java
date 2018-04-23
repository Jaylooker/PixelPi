package cnit425semesterproject.pixelpi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jackb on 4/16/2018.
 */

public class EditFragment extends Fragment implements DeviceTaskCallback {
    private View rootview;
    private View footerview;
    private TextView txtdevicenameedit;
    private ListView lvdevicetasks;
    private ImageView imgplus;

    private DeviceTaskAdapter deviceTaskAdapter;
    private ArrayList<DeviceTask> deviceTasks;
    private Device selecteddevice;
    private String mode;

    public static String EDIT_FRAGMENT = "edit";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceTasks = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootview == null) {
            rootview = inflater.inflate(R.layout.edit_layout, null, false);
            footerview = inflater.inflate(R.layout.listview_plus_footer, null, false);

            txtdevicenameedit = rootview.findViewById(R.id.txtdevicenameedit);
            imgplus = footerview.findViewById(R.id.imgplus);

            // adding new device task
            imgplus.setOnClickListener(new View.OnClickListener() { 
                @Override
                public void onClick(View view) {
                    //build dialog to get task mode/type
                    final AlertDialog.Builder selecttypedialog = new AlertDialog.Builder(getActivity());
                    selecttypedialog.setTitle("Select type of new task");
                    //selecting item
                    selecttypedialog.setSingleChoiceItems(R.array.devicemodes, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //once chosen
                            String[] list = getResources().getStringArray(R.array.devicemodes);
                            //list.addAll();
                            //type of device task
                            mode = list[i];
                        }
                    });
                    //cancelling dialog
                    selecttypedialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                        }
                    });
                    //moving to next dialog
                    selecttypedialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(mode != null) {
                                switch (mode) {
                                    case "display": DisplayDialog displaydialog = DisplayDialog.newInstance();
                                        displaydialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Fullscreen);
                                        displaydialog.show(getFragmentManager(), DisplayDialog.DISPLAY_DIALOG);
                                        dialogInterface.dismiss();
                                        break;
                                    case "timer": AlertDialog.Builder timerdialog = new AlertDialog.Builder(getActivity());
                                        // TODO: 4/21/2018 Decide on whether to use Android provided timer, AlertDialog.Builder, or custom dialog class
                                        break;
                                }
                            }
                            else
                            {
                                dialogInterface.cancel();
                            }
                        }
                    });
                    selecttypedialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            Toast.makeText(getActivity(), "Please select a task mode", Toast.LENGTH_LONG).show();
                        }
                    });
                    //selecttypedialog.cancellable(bool) default is true
                    selecttypedialog.create();
                    //show dialog
                    selecttypedialog.show();
                }
            });

        }
        return rootview;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceTaskAdapter = new DeviceTaskAdapter(getActivity(), deviceTasks);
        lvdevicetasks = getView().findViewById(R.id.lvdevicetasks);
        lvdevicetasks.addFooterView(footerview); //adding footer before setting adapter
        lvdevicetasks.setAdapter(deviceTaskAdapter);
    }

    @Override
    public void onDestroyView() {
        if(rootview.getParent() != null) {
            ((ViewGroup)rootview.getParent()).removeView(rootview);
        }
        super.onDestroyView();
    }

    //callback from dialog fragments
    //update device tasks, send back to main activity
    @Override
    public void sendnewdevicetask(DeviceTask deviceTask) {
        //todo: not not updating
        ArrayList<DeviceTask> deviceTasks = selecteddevice.getDeviceTasks();
        deviceTasks.add(deviceTask);
        selecteddevice.setDeviceTasks(deviceTasks);
        deviceTaskAdapter.notifyDataSetChanged();
    }

    //getters and setters
    public Device getSelecteddevice() {
        return selecteddevice;
    }

    public void setSelecteddevice(Device selecteddevice) {
        this.selecteddevice = selecteddevice;
        txtdevicenameedit.setText("Device: " + selecteddevice.getDevicename());
        this.deviceTasks = selecteddevice.getDeviceTasks();
        if(!(selecteddevice.getDeviceTasks().isEmpty())) //if device has tasks, update adapter
        {
            deviceTasks.clear();
            deviceTasks.addAll(selecteddevice.getDeviceTasks());
            deviceTaskAdapter.notifyDataSetChanged();
        }

    }

}
