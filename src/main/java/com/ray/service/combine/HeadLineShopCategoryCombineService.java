package com.ray.service.combine;

import com.ray.entity.dto.MainPageInfoDTO;
import com.ray.entity.dto.Result;

/**
 * @author 张烈文
 */
public interface HeadLineShopCategoryCombineService {

    Result<MainPageInfoDTO> getMainPageInfo();
}
