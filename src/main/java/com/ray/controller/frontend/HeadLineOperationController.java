package com.ray.controller.frontend;

import com.ray.entity.bo.HeadLine;
import com.ray.entity.dto.Result;
import com.ray.service.solo.HeadLineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HeadLineOperationController {
    private HeadLineService headLineService;

   public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse reps) {

//        TODO 参数校验
     return    headLineService.addHeadLine(new HeadLine());
    }

   public Result<Boolean> removeHeadLine(HttpServletRequest req, HttpServletResponse reps) {
    return     headLineService.removeHeadLine(1);
    }

    public  Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse reps) {

        return headLineService.modifyHeadLine(new HeadLine());
    }

    public  Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse reps) {
        return headLineService.queryHeadLineById(1);
    }

    public   Result<List<HeadLine>> queryHeadLine(HttpServletRequest req, HttpServletResponse reps) {

        return headLineService.queryHeadLine(null, 1, 100);
    }
}
