import java.lang.reflect.Field;
import java.util.Map;

public class MapMapper implements JsonMapper {

    public MapMapper() {

    }

    public void write(Map<?, ?> map, JsonWriter jsonWriter) {
        jsonWriter.writeObjectBegin();
        for (Map.Entry<?, ?> e : map.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            jsonWriter.writeString((String) key);
            jsonWriter.writePropertySeparator();
            jsonWriter.writeString((String) value);
            jsonWriter.writeString("\n");
        }
        jsonWriter.writeObjectEnd();
        jsonWriter.writeString("\n");
        jsonWriter.flush();
    }

    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        if (object == null) {
            jsonWriter.writeNull();
            jsonWriter.flush();
        } else {
            if (object instanceof Map<?, ?>) {
                jsonWriter.writeObjectBegin();
                jsonWriter.writeString(field.getName());
                jsonWriter.writePropertySeparator();
                jsonWriter.writeString("\n");
                jsonWriter.flush();
                write((Map<?, ?>) object, jsonWriter);
            }
        }
    }
}
