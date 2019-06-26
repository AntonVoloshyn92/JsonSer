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



    }

}


class ClassForTest {

    int age = 26;

    public String name = "Anton";
    boolean isTrue = true;
    long aLong = 568765;
    private char aChar = '4';

    People people = new People();
    Woman woman = new Woman();

    Object[] objects = new Object[2];

    Integer[] massInt = new Integer[3];

    public void setMassInt() {
        massInt[0] = 1;
        massInt[1] = 2;
        massInt[2] = 3;
    }

    List<String> list = new ArrayList<>();

    public void setList1(){
        list.add("AAAAA");
        list.add("BBBBB");
        list.add("CCCCC");
    }

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

    static public class Woman {
        int age = 21;
        String name = "Lena";
    }

}

