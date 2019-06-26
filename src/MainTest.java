import com.google.gson.Gson;

public class MainTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
//        new Serializer().jsonSerializer(scratch_1.Instance);
        System.out.println(new Gson().toJson(scratch_1.Instance));
    }
}
