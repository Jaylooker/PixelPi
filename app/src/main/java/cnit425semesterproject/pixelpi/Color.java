package cnit425semesterproject.pixelpi;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

/**
 * Created by jackb on 4/22/2018.
 */

//Own implementation of color to store RGB values and convert to int when displaying as color for blankcolor.png
// TODO: 4/24/2018 consider extending android.graphics.Color
@JsonSerialize(using = JSONColorSerialize.class)
@JsonDeserialize(using = JSONColorDeserialize.class)
public class Color {
    //make get only values of fields recognized
    private int red;
    private int green;
    private int blue;

    //functions
    public int toint()
    {
        int color = android.graphics.Color.rgb(red, green, blue);
        return color;
    }

    public int[] toarray()
    {
        return new int[]{this.red, this.green, this.blue};
    }

    //getters and setters
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    //constuctors
    public Color() {

    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}

//helper classes
class JSONColorSerialize extends JsonSerializer<Color> {
    @Override
    public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        value.toarray();
    }

}

class JSONColorDeserialize extends JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
