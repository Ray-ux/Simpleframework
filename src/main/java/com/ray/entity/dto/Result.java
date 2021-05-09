package com.ray.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张烈文
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{

    private int code;

    private String message;

    private T data;
}
