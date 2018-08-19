package cnit425semesterproject.pixelpi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by jackb on 8/18/2018.
 */

public class ColorTest {
    @Test
    public void serialization_isCorrect() throws ParseException, JsonProcessingException {
        Color color = new Color(24, 24, 24);
        ObjectMapper mapper = new ObjectMapper();

    }

    @Test
    public void deserialization_isCorrect() throws IOException {

    }
}
