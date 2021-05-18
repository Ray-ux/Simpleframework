package com.ray.service.solo.impl;

import com.ray.entity.bo.HeadLine;
import com.ray.entity.bo.ShopCategory;
import com.ray.entity.dto.Result;
import com.ray.service.solo.HeadLineService;
import com.ray.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;


/**
 * @author 张烈文
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
