package com.zjht.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by leaves on 14/12/4.
 */
public class MapUtils extends org.apache.commons.collections.MapUtils {
    private static Map<Class<?>, Map<String, ?>> staticPropertyCache = Collections.synchronizedMap(
            new HashMap<Class<?>, Map<String, ?>>());

    public static <K, V> Map<K, V> toMap(Collection<V> collection, KeyGetter<K, V> keyGetter) {
        Map<K, V> map = new HashMap<K, V>();
        for (V v : collection) {
            map.put(keyGetter.getKey(v), v);
        }
        return map;
    }

    public interface KeyGetter<K, V>{
        K getKey(V instance);
    }

    /**
     * 把类公共静态类变量转换进map
     * @param clazz 类
     * @return 返回map
     */
    public static Map<String, ?> convertStaticProperty2Map(Class<?> clazz) {
        return convertStaticProperty2Map(clazz, new String[0]);
    }

    /**
     * 把类公共静态类变量转换进map
     * @param clazz 类
     * @param exclude 排除项
     * @return 返回map
     */
    public static Map<String, ?> convertStaticProperty2Map(Class<?> clazz, String... exclude) {
        if (staticPropertyCache.containsKey(clazz)) {
            return staticPropertyCache.get(clazz);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            Set<String> excludeSet = new HashSet<String>(Arrays.asList(exclude==null?new String[0]:exclude));
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && !excludeSet.contains(field.getName())) {
                    try {
                        map.put(field.getName(), field.get(null));
                    } catch (IllegalAccessException e) {
                    }
                }
            }
            staticPropertyCache.put(clazz, map);
            return map;
        }
    }

    public static void main(String args[]){
        System.out.println(convertStaticProperty2Map(MapUtils.class,new String[0]));
    }
}
