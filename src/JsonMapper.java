import java.lang.reflect.Field;

public interface JsonMapper<T> {
    void write(T object, Field field, JsonWriter jsonWriter);
}
