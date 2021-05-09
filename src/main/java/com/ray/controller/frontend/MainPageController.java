package com.ray.controller.frontend;

import com.ray.entity.dto.MainPageInfoDTO;
import com.ray.entity.dto.Result;
import com.ray.service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageController {

    private HeadLineShopCategoryCombineService service;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse reps) {
        return service.getMainPageInfo();
    }
}
