import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        MethodsOfCollection methodsOfCollection = new MethodsOfCollection();
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        System.out.println(methodsOfCollection.returnHashset(list));


        Map<String, String> map2 = new HashMap<>();
        map2.put("111", "222");
        System.out.println(methodsOfCollection.inverse(map2));
    }
}
