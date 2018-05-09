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
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by jackb on 4/21/2018.
 */

//Custom Adapter for Device Tasks
public class DeviceTaskAdapter extends ArrayAdapter {

    private View rootview;
    private View footerview;
    private TextView txtmode;
    private TextView txttaskname;
    private RadioButton raactivated;
    private Button btnedittask;

    private ArrayList<DeviceTask> deviceTasks;

    public DeviceTaskAdapter(Context context, ArrayList<DeviceTask> deviceTasks) {
        super(context, 0, deviceTasks);
        this.deviceTasks = deviceTasks;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DeviceTask deviceTask = deviceTasks.get(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_task_layout, parent, false);
        }

        txtmode = convertView.findViewById(R.id.txtmode);
        txttaskname = convertView.findViewById(R.id.txttaskname);
        raactivated = convertView.findViewById(R.id.raactivated);
        btnedittask =  convertView.findViewById(R.id.btnedittask);

        if(deviceTask != null) {
            txtmode.setText(deviceTask.getMode());
            txttaskname.setText(deviceTask.getName());
            raactivated.setChecked(deviceTask.isActivated());
        }

        btnedittask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    public void updatedevicetasks(ArrayList<DeviceTask> devicestask)
    {
        this.deviceTasks.clear();
        this.deviceTasks.addAll(devicestask);
        notifyDataSetChanged();
    }
}
