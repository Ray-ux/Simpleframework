package com.ray.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 张烈文
 */
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
//0.不可用  1.可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;

}
