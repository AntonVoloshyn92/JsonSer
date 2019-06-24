import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public enum ModifierType {
        PUBLIC, PRIVATE, PROTECTED, TRANSIENT, ELSE_ACCESS
    }

    public ModifierType getModifierType(Field field) {

        int modifiers = field.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            return ModifierType.PUBLIC;
        } else if (Modifier.isProtected(modifiers)) {
            return ModifierType.PROTECTED;
        } else if (Modifier.isPrivate(modifiers)) {
            return ModifierType.PRIVATE;
        } else if (Modifier.isTransient(modifiers)) {
            return ModifierType.TRANSIENT;
        } else {
            return ModifierType.ELSE_ACCESS;
        }
    }
}
