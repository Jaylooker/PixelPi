package cnit425semesterproject.pixelpi;

import org.json.JSONObject;

/**
 * Created by jackb on 4/24/2018.
 */

//for any object that needs to be sent via JSON to server should implement this method
// TODO: 4/28/2018 Switch from using Interface to using Jackson library to handle JSON to POJO conversion
public interface JSON {
    JSONObject toJSON();
}
