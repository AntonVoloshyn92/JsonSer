import java.lang.reflect.Field;
import java.util.Collection;

public class CollectionMapper implements JsonMapper {

    public void write(Collection collection, JsonWriter jsonWriter) {
        for (Object collection1 : collection) {
            jsonWriter.writeObjectBegin();
            jsonWriter.writeString(collection1.toString());
            jsonWriter.writeObjectEnd();
        }
        jsonWriter.writeArrayEnd();
    }


    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        if (object == null) {
            jsonWriter.writeNull();
            return;
        }
        if (object instanceof Collection<?>) {
            jsonWriter.writeString(field.getName());
            jsonWriter.writeArrayBegin();
            write((Collection<?>) object, jsonWriter);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
