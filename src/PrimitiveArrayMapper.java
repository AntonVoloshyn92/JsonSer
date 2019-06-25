import java.lang.reflect.Field;

public class PrimitiveArrayMapper implements JsonMapper {
    public PrimitiveArrayMapper() {

    }

    @Override
    public void write(Object object, Field field, JsonWriter jsonWriter) {
        jsonWriter.writeString(field.getName());
        jsonWriter.writeObjectBegin();

        if (object.getClass().equals(byte[].class)) {
            for (byte i : (byte[]) object) {
                jsonWriter.writeString(i + "");
            }
        } else if (object.getClass().equals(short[].class)) {
            for (short i : (short[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(int[].class)) {
            for (int i : (int[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(double[].class)) {
            for (double i : (double[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(float[].class)) {
            for (float i : (float[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(char[].class)) {
            for (char i : (char[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(long[].class)) {
            for (long i : (long[]) object) {
                jsonWriter.writeString(i + "");
            }
        }else if (object.getClass().equals(boolean[].class)) {
            for (boolean i : (boolean[]) object) {
                jsonWriter.writeString(i + "");
            }
        }

        jsonWriter.writeObjectEnd();
        jsonWriter.writeArrayEnd();
        jsonWriter.flush();
    }
}
