import java.lang.reflect.Method;
import java.util.Map;

enum scratch_1 {
    Instance;
    int A1 = 5;
    interface In1{
        void get();
    }
    In1 sh1 = new In1() {

        String myString = "ha - ha";

        @Override
        public void get() {

        }
    };


    In1 a = ()->{
        //
    };

    Map<String,In1> map;

    Class c = this.getClass();

    class Gen1<T>{
        T ob;

        public Gen1(T ob) {
            this.ob = ob;
        }
    }

    static Gen1<In1> m;
    static {
        m = null;
    }


}