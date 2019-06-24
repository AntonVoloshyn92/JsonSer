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
        class1.setObjects();

        new Serializer().jsonSerializer(class1);
    }

}


class ClassForTest {

    int age = 26;

    public String name = "Anton";
    boolean isTrue = true;
    long aLong = 568765;
    private char aChar = '4';

    People people = new People();

    Object[] objects = new Object[2];

    public void setObjects() {
        objects[0] = 3;
        objects[1] = "sss";
    }


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

