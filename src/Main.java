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

        new Serializer().jsonSerializer(class1);
    }





   /* public static Class testMap(Map<Class, JsonMapper> map) {
        for (Map.Entry<Class, JsonMapper> e : map.entrySet()) {
            Class key = e.getKey();
            *//*Object value = e.getValue();
            System.out.print("{" + key);
            System.out.print(" : ");
            System.out.println(value + "}");*//*
        }
        return
    }*/
}

class CustomMapper {

    public Map<Class, Field[]> createMapper(Object object) {
        Map<Class, Field[]> map = new HashMap<>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        map.put(clazz, fields);
        return map;
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

    int age = 26;

    public String name = "Anton";
    boolean isTrue = true;
    long aLong = 568765;
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

