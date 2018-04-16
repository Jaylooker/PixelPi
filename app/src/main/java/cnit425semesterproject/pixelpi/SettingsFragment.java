package cnit425semesterproject.pixelpi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jackb on 4/15/2018.
 */

public class SettingsFragment extends Fragment {

    private View rootview;

    public static String SETTINGS_FRAGMENT = "settings";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(rootview == null) {
            rootview = inflater.inflate(R.layout.settings_fragment_layout, container, false);
            //ui references
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
}
