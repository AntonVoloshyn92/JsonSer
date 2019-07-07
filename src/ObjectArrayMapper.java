import java.lang.reflect.Field;

public class ObjectArrayMapper implements JsonMapper {

    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        if (object == null) {
            jsonWriter.writeNull();
            jsonWriter.flush();
        } else {
            jsonWriter.writeString(field.getName());
            jsonWriter.writeArrayBegin();
            for (Object o : (Object[]) object) {
                jsonWriter.writeString(o.toString());
            }
            jsonWriter.writeObjectEnd();
            jsonWriter.writeArrayEnd();
            jsonWriter.flush();
        }
    }
}
