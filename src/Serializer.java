import Annotations.JsonProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Serializer {
    public static void jsonSerializer(Object object) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InstantiationException {

        Class c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        Annotation[] annotates = c.getAnnotations();// oll annotations for JSON


        JsonWriter jsonWriter = new JsonWriter();

        //place for mappers
        StringMapper stringMapper = new StringMapper();
        NumberMapper numberMapper = new NumberMapper();
        BooleanMapper booleanMapper = new BooleanMapper();
        MapMapper mapMapper = new MapMapper();
        CharesterMap charesterMap = new CharesterMap();

        Map<Class, JsonMapper> mappersCache = new HashMap<>();
        Map<Class, Field[]> customMapper = new HashMap<>();

        mappersCache.put(int.class, numberMapper);
        mappersCache.put(Integer.class, numberMapper);
        mappersCache.put(float.class, numberMapper);
        mappersCache.put(Float.class, numberMapper);
        mappersCache.put(double.class, numberMapper);
        mappersCache.put(Double.class, numberMapper);
        mappersCache.put(byte.class, numberMapper);
        mappersCache.put(Byte.class, numberMapper);
        mappersCache.put(short.class, numberMapper);
        mappersCache.put(Short.class, numberMapper);
        mappersCache.put(long.class, numberMapper);
        mappersCache.put(Long.class, numberMapper);

        mappersCache.put(String.class, stringMapper);

        mappersCache.put(char.class, charesterMap);
        mappersCache.put(Character.class, charesterMap);

        mappersCache.put(boolean.class, booleanMapper);
        mappersCache.put(Boolean.class, booleanMapper);

        mappersCache.put(Map.class, mapMapper);


        for (Field field : fields) {
            int mods = field.getModifiers();
            if (Modifier.isTransient(mods) || Modifier.isPrivate(mods)) {
                if (!field.isAnnotationPresent(JsonProperty.class)) {//todo test
                    continue;
                } else {
                    field.setAccessible(true);
                }
            }

            if (mappersCache.containsKey(field.getType())) {
                mappersCache.get(field.getType()).write(field.get(object), field, jsonWriter);
            } else {
                Object o = field.get(object);
                Field[] fields1 = o.getClass().getDeclaredFields();
                customMapper.put(field.getType(), fields1);
                if (customMapper.containsKey(field.getType())) {
                    Field[] fil = customMapper.get(field.getType());
                    jsonWriter.writeString(field.getName() + "\n");
                    jsonWriter.writeObjectBegin();
                    jsonWriter.flush();
                    for (Field field1 : fil) {
//                        System.out.println(field1.getName() + " " + field1.get(o));
                        if (mappersCache.containsKey(field1.getType())) {
                            mappersCache.get(field1.getType()).write(field1.get(o), field1, jsonWriter);
                        }
                    }
                    jsonWriter.writeObjectEnd();
                    jsonWriter.writeString("\n");
                    jsonWriter.flush();
                }
            }

        }
    }
}
