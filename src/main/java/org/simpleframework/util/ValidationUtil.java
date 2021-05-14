package org.simpleframework.util;

import java.util.Collection;
import java.util.Map;

/**
 *
 *
 * @author 张烈文
 */
public class ValidationUtil {
    /**
     * Map是否为null或size为0
     *
     * @param obj
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
     * String是否为null或""
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(String obj) {
        return obj == null || "".endsWith(obj);
    }

    /**
     * Array是否为null或者size为0
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }


    /**
     * Map是否为null或size为0
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return obj == null || obj.isEmpty();
    }


}