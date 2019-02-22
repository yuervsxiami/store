package com.cnuip.base.service.impl;

import com.cnuip.base.repository.AbstractMapper;
import com.cnuip.base.service.AbstractService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 公用方法实现
 * @param <T> 实体
 * @param <P> 条件
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public class AbstractServiceImpl<T, P> extends BaseServiceImpl implements AbstractService<T, P> {
    @Autowired
    private AbstractMapper<T, P> abstractMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T insert(T record) throws Exception {
        try {
            abstractMapper.insert(record);
            Method m = record.getClass().getMethod("getId");
            Long id = (Long) m.invoke(record);
            return abstractMapper.selectOneByKey(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByKey(Long id) throws Exception {
        try {
            abstractMapper.deleteByKey(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByField(String field, Object valueFrom, Object valueTo) {
        try {
            abstractMapper.deleteByField(field, valueFrom, valueTo);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(P params) throws Exception {
        try {
            abstractMapper.delete(params);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T updateFieldByKey(Long id, String field, Object value) throws Exception {
        try {
            abstractMapper.updateFieldByKey(id, field, value);
            return abstractMapper.selectOneByKey(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T updateByKey(Long id, T record) throws Exception {
        try {
            abstractMapper.updateByKey(id, record);
            return abstractMapper.selectOneByKey(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T update(P params, T record) throws Exception {
        try {
            abstractMapper.update(params, record);
            Method m = record.getClass().getMethod("getId");
            Long id = (Long) m.invoke(record);
            return abstractMapper.selectOneByKey(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int count(P params) {
        return abstractMapper.count(params);
    }

    @Override
    public List<T> getAll(P params) {
        return abstractMapper.getAll(params);
    }

    @Override
    public T selectOneByKey(Long id) {
        return abstractMapper.selectOneByKey(id);
    }

    @Override
    public T selectOneByField(String field, Object valueFrom, Object valueTo) {
        return abstractMapper.selectOneByField(field, valueFrom, valueTo);
    }

    @Override
    public T selectOne(P params) {
        return abstractMapper.selectOne(params);
    }

    @Override
    public PageInfo<T> selectMany(P params) throws Exception {
        Method getPageNum = params.getClass().getMethod("getPageNum");
        Integer pageNum = (Integer) getPageNum.invoke(params);
        if (pageNum == null) {
            pageNum = 1;
        }
        Method getPageSize = params.getClass().getMethod("getPageSize");
        Integer pageSize = (Integer) getPageSize.invoke(params);
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<T> page = (Page<T>) abstractMapper.selectMany(params, pageNum, pageSize);
        return page.toPageInfo();
    }

    @Override
    public PageInfo<T> selectManyByField(String field, Object valueFrom, Object valueTo, String orderBy, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<T> page = (Page<T>) abstractMapper.selectManyByField(field, valueFrom, valueTo, orderBy, pageNum, pageSize);
        return page.toPageInfo();
    }
}
