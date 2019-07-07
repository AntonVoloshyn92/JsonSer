import Annotations.JsonProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InstantiationException {

        ClassForTest classForTest = new ClassForTest();
        new Serializer().jsonSerializer(classForTest);

    }

}


class ClassForTest {

    String day = "Friday";
    int year = 2019;

    People people = new People("Sofia", 15);
    People people1 = new People("Anton", 26);

    class People {
        String name;
        int age;

        People(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

}

