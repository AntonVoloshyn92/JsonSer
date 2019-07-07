import java.lang.reflect.Field;

public class NumberMapper implements JsonMapper {


    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        if (object == null) {
            jsonWriter.writeNull();
            jsonWriter.flush();
        } else {
            jsonWriter.writeObjectBegin();
            jsonWriter.writeString(field.getName());
            jsonWriter.writePropertySeparator();
            jsonWriter.writeNumber((Number) object);
            jsonWriter.writeObjectEnd();
            jsonWriter.flush();
        }
    }
}
