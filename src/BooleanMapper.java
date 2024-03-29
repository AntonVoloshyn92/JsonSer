import java.lang.reflect.Field;



public class BooleanMapper implements JsonMapper {

    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {

        if (object == null) {
            jsonWriter.writeNull();
            jsonWriter.flush();
        } else {
            jsonWriter.writeObjectBegin();
            jsonWriter.writeString(field.getName());
            jsonWriter.writePropertySeparator();
            jsonWriter.writeBoolean((Boolean) object);
            jsonWriter.writeObjectEnd();
            jsonWriter.flush();
        }
    }
}
