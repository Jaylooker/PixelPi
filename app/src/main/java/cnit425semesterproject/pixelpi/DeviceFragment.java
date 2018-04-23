package cnit425semesterproject.pixelpi;

//import android.app.Fragment; //Fragment is older version
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment; // requires API 11+, newer version
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by jackb on 4/14/2018.
 */

public class DeviceFragment extends Fragment {

    private View rootview;
    private ListView lvdevices;
    private DeviceArrayAdapter deviceadapter;
    private ArrayList<Device> devices;
    private DeviceFragmentListener listener;

    public static String DEVICES_FRAGMENT = "devices";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //parse
        devices = new ArrayList<>();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        deviceadapter = new DeviceArrayAdapter(getActivity(), devices);
        lvdevices.setAdapter(deviceadapter);
        lvdevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Device device = devices.get(position);
                listener.selecteddevice(device);
                //Log.i("device clicked", device.getDevicename());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DeviceFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(rootview == null) {
            rootview = inflater.inflate(R.layout.device_fragment_layout, container, false);
            lvdevices =  rootview.findViewById(R.id.lvdevices);
        }

        return rootview;
    }

    @Override
    public void onDestroyView() {
        if(rootview.getParent() != null) {
            ((ViewGroup)rootview.getParent()).removeView(rootview);
        }
        super.onDestroyView();
    }


    public void updatedevices(ArrayList<Device> devices)
    {
        deviceadapter.updatedevices(devices);
    }
}
