package util;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 HashMap
 * @author JackWu
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", new Object());
        System.out.println(map);
    }

}