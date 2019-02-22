package com.cnuip.console.service;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.base.domain.params.SplashParam;
import com.cnuip.console.rest.exceptions.CustomException;
import com.cnuip.console.vo.SplashVo;
import com.github.pagehelper.PageInfo;

public interface SplashService {
    Splash insertSplash(Splash splash) throws Exception;

    Splash updateSplash(Splash splash) throws CustomException;

    PageInfo<SplashVo> list(SplashParam splashParam);

    Splash current();
}
