import Annotations.JsonProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InstantiationException {
        ClassForTest class1 = new ClassForTest();

        class1.setMap();


        jsonSerializer(class1);
    }


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
            if (field.getType().equals(int.class) || field.getType().equals(Integer.class) ||
                    field.getType().equals(float.class) || field.getType().equals(Float.class) ||
                    field.getType().equals(double.class) || field.getType().equals(Double.class) ||
                    field.getType().equals(byte.class) || field.getType().equals(Byte.class) ||
                    field.getType().equals(short.class) || field.getType().equals(Short.class) ||
                    field.getType().equals(long.class) || field.getType().equals(Long.class)) {


                Object o = field.get(object);
                numberMapper.write(o, field, jsonWriter);


            } else if (field.getType().equals(String.class)) {

                Object o = field.get(object);
                stringMapper.write(o, field, jsonWriter);

            } else if (field.getType().equals(char.class) || field.getType().equals(Character.class)) {

                Object o = field.get(object);
                charesterMap.write(o, field, jsonWriter);

            } else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {

                Object o = field.get(object);
                booleanMapper.write(o, field, jsonWriter);


            } else if (field.getType().equals(Map.class)) {

                Object o = field.get(object);
                mapMapper.write(o, field, jsonWriter);

            } else if (field.getType().equals(Class.class)) {

            }
        }
    }


    public static void forScrean(Map<?, ?> map) {
        for (Map.Entry<?, ?> e : map.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            System.out.print("{" + key);
            System.out.print(" : ");
            System.out.println(value + "}");
        }
    }
}

class CustomMapper {

    public JsonMapper createMapper(Class<?> clazz) {
        JsonMapper newMapper = new JsonMapper() { //TODO


            @Override
            public void write(Object object, Field field, JsonWriter jsonWriter) {

            }
        };


        Field[] fields = clazz.getFields();
        for (Field fil : fields) {

        }


        return newMapper;
//        if (clazz.getComponentType().isPrimitive()) {
//            return primitivArrayMapper;
//        } else {
//            return objectArrayMapper;
//        }
    }


    private Map<Field, JsonMapper> mappers;

    CustomMapper(Object object) throws IllegalAccessException {
        Class c = object.getClass();
        Field[] fields = c.getFields();

        for (Field field : fields) {

            String name = field.getName();
            String value = (String) field.get(object);

            System.out.println(name);
            System.out.println(value);
          /* if (field is string) {
                mappers.put(field, stringMapper)
            }*/

        }
    }
}


class ClassForTest {

    private int age = 26;

    public String name = "Anton";
    boolean isTrue = true;
    @JsonProperty
    private char aChar = '4';

    People people = new People();


    Map<String, String> map = new HashMap<>();

    public void setMap() {
        map.put("LOL", "HAHA");
        map.put("Anton", "Krasava");
        map.put("Pety", null);
    }


    static public class People {
        int age = 31;
        String name = "Petya";
    }

}

