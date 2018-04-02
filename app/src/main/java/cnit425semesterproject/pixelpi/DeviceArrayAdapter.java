package cnit425semesterproject.pixelpi;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jackb on 3/28/2018.
 */

public class DeviceArrayAdapter extends ArrayAdapter {
    // TODO: finish custom ArrayAdapter
    private ArrayList<Device> devices = new ArrayList<>();

    public DeviceArrayAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
