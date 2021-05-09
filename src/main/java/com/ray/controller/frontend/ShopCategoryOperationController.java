package com.ray.controller.frontend;

import com.ray.entity.bo.ShopCategory;
import com.ray.entity.dto.Result;
import com.ray.service.solo.ShopCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShopCategoryOperationController {

    private ShopCategoryService shopCategoryService;

    public   Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse reps) {
        return shopCategoryService.addShopCategory(new ShopCategory());
    }

    public  Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse reps) {
        return shopCategoryService.removeShopCategory(1);
    }

    public   Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse reps) {
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }

    public   Result<ShopCategory> queryShopCategoryById(HttpServletRequest req, HttpServletResponse reps) {
        return shopCategoryService.queryShopCategoryById(1);
    }

    public   Result<List<ShopCategory>> queryShopCategory(HttpServletRequest req, HttpServletResponse reps) {
        return shopCategoryService.queryShopCategory(null, 1, 100);
    }
}
