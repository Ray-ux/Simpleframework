package com.ray.controller.frontend;

import com.ray.entity.dto.MainPageInfoDTO;
import com.ray.entity.dto.Result;
import com.ray.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Getter
public class MainPageController {

    @Autowired
    private HeadLineShopCategoryCombineService service;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse reps) {
        return service.getMainPageInfo();
    }
}
