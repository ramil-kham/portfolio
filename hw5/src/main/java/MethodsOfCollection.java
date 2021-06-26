import java.util.*;

public class MethodsOfCollection {
    public Collection<String> returnHashset (List<String> list1) {
        return new HashSet<>(list1);
    }

    public <K,V> HashMap<V,K> inverse(Map<K,V> map) {
        HashMap<V,K> hashMap = new HashMap<V, K>();
        for(Map.Entry<K,V> entry : map.entrySet())
            hashMap.put(entry.getValue(), entry.getKey());
        return hashMap;
    }
}
