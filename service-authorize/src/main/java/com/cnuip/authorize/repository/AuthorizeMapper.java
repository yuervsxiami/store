package com.cnuip.authorize.repository;

import com.cnuip.authorize.vo.AuthorizeModel;
import com.cnuip.base.domain.authorize.Authorize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xjt on 2018/8/30.
 */
@Repository
public interface AuthorizeMapper {
    List<Authorize> selectAuthorizeList(@Param("orgId") Long orgId, @Param("authorizeModel") AuthorizeModel authorizeModel, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<String> searchAn(@Param("editorName") String editorName, @Param("orgName") String orgName);
}
