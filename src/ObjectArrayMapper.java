import java.lang.reflect.Field;

public class ObjectArrayMapper implements JsonMapper {
    public ObjectArrayMapper() {

    }


    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        jsonWriter.writeString(field.getName());
        jsonWriter.writeObjectBegin();
        for (Object o : (Object[]) object) {
            jsonWriter.writeString(o.toString());
        }
        jsonWriter.writeObjectEnd();
        jsonWriter.writeArrayEnd();
        jsonWriter.flush();
    }
}
