package com.cnuip.base.utils;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class PageInfoTransferUtils {
    /**
     * 替换PageInfo中的List
     *
     * @param source  原pageInfo
     * @param content 新List
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> PageInfo<T> transfer(PageInfo<S> source, List<T> content) {
        PageInfo<T> tPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(source, tPageInfo);
        tPageInfo.setList(content);
        return tPageInfo;
    }
}
