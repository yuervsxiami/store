package com.cnuip.authorize.repository;

import com.cnuip.base.domain.authorize.AuthorizePatent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xjt on 2018/8/30.
 */
@Repository
public interface AuthorizePatentMapper {

    AuthorizePatent checkAn(@Param("orgId") Long orgId, @Param("an") String an);
}
