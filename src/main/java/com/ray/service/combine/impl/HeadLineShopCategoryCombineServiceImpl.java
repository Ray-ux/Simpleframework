package com.ray.service.combine.impl;

import com.ray.entity.bo.HeadLine;
import com.ray.entity.bo.ShopCategory;
import com.ray.entity.dto.MainPageInfoDTO;
import com.ray.entity.dto.Result;
import com.ray.entity.dto.ResultUtil;
import com.ray.service.combine.HeadLineShopCategoryCombineService;
import com.ray.service.solo.HeadLineService;
import com.ray.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> listResult = headLineService.queryHeadLine(headLineCondition, 1, 5);
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> listResult1 = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 5);

//        MainPageInfoDTO mainPageInfoDTO = MainPageInfoDTO.builder()
//                .headLineList(listResult.getData())
//                .shopCategoryList(listResult1.getData())
//                .build();
//        return ResultUtil.success(mainPageInfoDTO);
        return null;
    }
}
