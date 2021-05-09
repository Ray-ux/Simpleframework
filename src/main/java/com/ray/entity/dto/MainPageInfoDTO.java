package com.ray.entity.dto;

import com.ray.entity.bo.HeadLine;
import com.ray.entity.bo.ShopCategory;
import com.ray.service.solo.HeadLineService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 张烈文
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
