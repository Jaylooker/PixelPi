package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jackb on 3/28/2018.
 */

public class DeviceArrayAdapter extends ArrayAdapter {
    // TODO: finish custom ArrayAdapter

    private ArrayList<Device> devices;

    public DeviceArrayAdapter(Context context, ArrayList<Device> devices) {
        super(context, 0, devices);
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device device = (Device) getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_layout, parent, false);
        }

        TextView txtdevicename = convertView.findViewById(R.id.txtdevicename);
        ImageView imhdeviceicon = convertView.findViewById(R.id.imgdeviceicon);

        txtdevicename.setText(device.getDevicename());
        imhdeviceicon.setImageResource(R.drawable.genericdevice);

        return convertView;
    }

    public void updatedevices(ArrayList<Device> devices)
    {
        this.devices.clear();
        this.devices.addAll(devices);
        notifyDataSetChanged();
    }


}
