import Annotations.JsonProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public enum  Serializer {//todo with ENUM;
    // вынести enum в основной метод
    // вынести Map в основной метод
    // сделать до  09/07/2019
    INSTANCE;
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
        ObjectArrayMapper objectArrayMapper = new ObjectArrayMapper();
        PrimitiveArrayMapper primitiveArrayMapper = new PrimitiveArrayMapper();
        CollectionMapper collectionMapper = new CollectionMapper();

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

        mappersCache.put(Object[].class, objectArrayMapper);
        mappersCache.put(Byte[].class, objectArrayMapper);
        mappersCache.put(Short[].class, objectArrayMapper);
        mappersCache.put(Integer[].class, objectArrayMapper);
        mappersCache.put(Character[].class, objectArrayMapper);
        mappersCache.put(Float[].class, objectArrayMapper);
        mappersCache.put(Double[].class, objectArrayMapper);
        mappersCache.put(Long[].class, objectArrayMapper);
        mappersCache.put(Boolean[].class, objectArrayMapper);

        mappersCache.put(byte[].class, primitiveArrayMapper);
        mappersCache.put(short[].class, primitiveArrayMapper);
        mappersCache.put(int[].class, primitiveArrayMapper);
        mappersCache.put(char[].class, primitiveArrayMapper);
        mappersCache.put(float[].class, primitiveArrayMapper);
        mappersCache.put(double[].class, primitiveArrayMapper);
        mappersCache.put(long[].class, primitiveArrayMapper);
        mappersCache.put(boolean[].class, primitiveArrayMapper);

        mappersCache.put(Collection.class, collectionMapper);
        mappersCache.put(List.class, collectionMapper);
        mappersCache.put(Set.class, collectionMapper);
        mappersCache.put(HashSet.class, collectionMapper);
        mappersCache.put(TreeSet.class, collectionMapper);
        mappersCache.put(ArrayList.class, collectionMapper);
        mappersCache.put(LinkedList.class, collectionMapper);
        mappersCache.put(Vector.class, collectionMapper);
        mappersCache.put(Collectors.class, collectionMapper);
        mappersCache.put(Arrays.class, collectionMapper);
        mappersCache.put(AbstractCollection.class, collectionMapper);


        for (Field field : fields) {
            if (field == null) {
                jsonWriter.writeNull();
                continue;
            }
            int mods = field.getModifiers();
            if (Modifier.isTransient(mods) || Modifier.isPrivate(mods)) {
                if (!field.isAnnotationPresent(JsonProperty.class)) {
                    continue;
                } else {
                    field.setAccessible(true);
                }
            }

            if (mappersCache.containsKey(field.getType())) {
                mappersCache.get(field.getType()).write(field.get(object), field, jsonWriter);
            } else {
                Object o = field.get(object);
                if (o == null){
                    jsonWriter.writeNull();
                    continue;
                }
                Field[] fields1 = o.getClass().getDeclaredFields();
                customMapper.put(field.getType(), fields1);
                if (customMapper.containsKey(field.getType())) {
                    Field[] fil = customMapper.get(field.getType());
                    jsonWriter.writeString(field.getName());
                    jsonWriter.writeObjectBegin();
                    jsonWriter.flush();
                    for (Field field1 : fil) {
                        if (field1 == null){
                            jsonWriter.writeNull();
                            continue;
                        }
                        if (mappersCache.containsKey(field1.getType())) {
                            mappersCache.get(field1.getType()).write(field1.get(o), field1, jsonWriter);
                        }
                    }
                    jsonWriter.writeObjectEnd();
                    jsonWriter.flush();
                }
            }

        }
    }
}
