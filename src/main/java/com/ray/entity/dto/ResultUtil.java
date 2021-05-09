package com.ray.entity.dto;

import java.util.Objects;

/**
 * @author 张烈文
 */
public class ResultUtil {

    public static Result success(Object data) {
         return Result.builder()
                .code(1)
                .message("请求成狗")
                .data(data)
                .build();
    }

    public static Result error() {
        return Result.builder()
                .code(0)
                .message("请求错误")
                .build();
    }
}
