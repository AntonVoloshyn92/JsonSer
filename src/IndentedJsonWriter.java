import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class IndentedJsonWriter extends JsonWriter {

    private static final String NEW_LINE = "\n";
    private static final String INDENT_SEPARATOR = " ";
    private static final char SPACE = ' ';

    private int indentSize = 2;
    private int currentLevel = 0;

    private Map<Integer, String> newLineIndentMap;

    public IndentedJsonWriter(Writer writer) {
//        super(writer);
        newLineIndentMap = new HashMap<Integer, String>();
    }

    public IndentedJsonWriter(Writer writer, int indentSize) {
        this(writer);
        this.indentSize = indentSize;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    @Override
    public void writeObjectBegin() {
        try {
            currentLevel++;
            writer.append(OBJ_BEGIN).write(NEW_LINE);
            writer.write(getNewLineIndentString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeObjectEnd() {
        try {
            currentLevel--;
            writer.write(NEW_LINE);
            writer.write(getNewLineIndentString());
            writer.write(OBJ_END);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeArrayBegin() {
        try {
            currentLevel++;
            writer.append(ARR_BEGIN).write(NEW_LINE);
            writer.write(getNewLineIndentString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeArrayEnd() {
        separatorLast = false;
        try {
            currentLevel--;
            writer.write(NEW_LINE);
            writer.write(getNewLineIndentString());
            writer.write(ARR_END);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writePropertySeparator() {
        try {

            writer.append(SPACE).append(PROP_SEPARATOR).append(SPACE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeSeparator() {
        try {
            writer.append(SEPARATOR).write(NEW_LINE);
            writer.write(getNewLineIndentString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNewLineIndentString() {
        String indentStr = newLineIndentMap.get(currentLevel);
        if (indentStr != null) {
            return indentStr;
        } else {
            StringBuilder str = new StringBuilder("");
            for (int i = 0; i < currentLevel * indentSize; i++) {
                str.append(INDENT_SEPARATOR);
            }
            indentStr = str.toString();
            newLineIndentMap.put(currentLevel, indentStr);
            return indentStr;
        }
    }

}
