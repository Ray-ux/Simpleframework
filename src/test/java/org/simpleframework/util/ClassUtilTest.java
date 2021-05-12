package org.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ClassUtilTest {

    @DisplayName("提取目标类方法:extractPackageClassTest")
    @Test
    public void extractPackageClassTest() {

        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.ray.entity");

        System.out.println(classSet);

        Assertions.assertEquals(5, classSet.size());
    }
}
