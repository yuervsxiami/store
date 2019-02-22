package com.cnuip.console.repository.base;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.base.domain.params.SplashParam;
import com.cnuip.base.repository.AbstractMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SplashBaseMapper extends AbstractMapper<Splash, SplashParam> {
}
