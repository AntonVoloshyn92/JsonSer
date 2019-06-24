import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {
     static final char OBJ_BEGIN = '{';
    static final char OBJ_END = '}';
    static final char ARR_BEGIN = '[';
    static final char ARR_END = ']';
    static final char SEPARATOR = ',';
    static final char PROP_SEPARATOR = ':';
    private static final char STR_SEPARATOR = '\"';
    private static final String NULL = "null";

    boolean separatorLast;

    private String path = "/Users/antonvoloshyn/Desktop/test.json";
    public static Writer writer; //Todo private

    {
        try {
            writer = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void JsonWriter(Writer writer) {
        this.writer = writer;
    }

    public void writeObjectBegin() {
        try {
            writer.write(OBJ_BEGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectEnd() {
        try {
            writer.write(OBJ_END);//TODO with Separator
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeArrayBegin() {
        try {
            writer.write(ARR_BEGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeArrayEnd() {
        try {
            writer.write(ARR_END);
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
    }

    public void writeString(String string) {
        try {
            writer.append(STR_SEPARATOR).append(string).append(STR_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNumber(Number number) {
        try {
            writer.write(number.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSeparator() {
        try {
            writer.write(SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePropertySeparator() {
        try {
            writer.write(PROP_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBoolean(Boolean bool) {
        try {
            writer.write(bool.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNull() {
        try {
            writer.write(NULL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeThread() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
