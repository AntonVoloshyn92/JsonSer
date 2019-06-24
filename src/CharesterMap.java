import java.lang.reflect.Field;

public class CharesterMap implements JsonMapper {

    public CharesterMap (){

    }

    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        if (object == null) {
            jsonWriter.writeNull();
            jsonWriter.flush();
        } else {
            jsonWriter.writeObjectBegin();
            jsonWriter.writeString(field.getName());
            jsonWriter.writePropertySeparator();
            jsonWriter.writeString(object.toString());
            jsonWriter.writeObjectEnd();
            jsonWriter.writeString("\n");
            jsonWriter.flush();
        }
    }
}
