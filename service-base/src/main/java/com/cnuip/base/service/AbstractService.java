package com.cnuip.base.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface AbstractService<T, P> extends BaseService {

    /**
     * 插入一条记录
     * @param record 实体
     * @return 实体
     * @throws Exception
     */
    T insert(T record) throws Exception;

    /**
     * 根据主键删除一条记录
     * @param id 主键值
     * @return 实体
     * @throws Exception
     */
    void deleteByKey(Long id) throws Exception;

    /**
     * 根据字段删除一条或多条记录
     * @param field 字段
     * @param valueFrom 字段值范围
     * @param valueTo
     * @throws Exception
     */
    void deleteByField(String field, Object valueFrom, Object valueTo) throws Exception;

    /**
     * 根据条件删除记录
     * @param params MapperParams<T>
     * @return 实体
     * @throws Exception
     */
    void delete(P params) throws Exception;

    /**
     * 根据主键更新属性
     * @param id 主键值
     * @param field 字段
     * @param value 字段值
     * @return 实体
     * @throws Exception
     */
    T updateFieldByKey(Long id, String field, Object value) throws Exception;

    /**
     * 根据主键更新一条记录
     * @param id 主键值
     * @param record 实体
     * @return 实体
     * @throws Exception
     */
    T updateByKey(Long id, T record) throws Exception;

    /**
     * 根据条件更新记录
     * @param params MapperParams<T>
     * @param record 实体
     * @return 实体
     * @throws Exception
     */
    T update(P params, T record) throws Exception;

    /**
     * 根据条件统计记录数
     * @param params MapperParams<T>
     * @return 记录数
     */
    int count(P params);

    /**
     * 根据条件获取所有记录、可排序
     * @param params MapperParams<T>
     * @return 实体列表
     */
    List<T> getAll(P params);

    /**
     * 根据主键获取一条记录
     * @param id 主键值
     * @return 实体
     */
    T selectOneByKey(Long id);

    /**
     * 根据字段获取一条记录
     * @param field 字段
     * @param valueFrom 字段值范围
     * @param valueTo
     * @return 实体
     */
    T selectOneByField(String field, Object valueFrom, Object valueTo);

    /**
     * 根据条件获取一条记录
     * @param params MapperParams<T>
     * @return 实体
     */
    T selectOne(P params);

    /**
     * 根据条件获取一页记录、可排序
     * @param params MapperParams<T>

     * @return 实体列表
     */
    PageInfo<T> selectMany(P params) throws Exception;

    /**
     * 根据字段获取一页记录、可排序
     * @param field 字段
     * @param valueFrom 字段值范围
     * @param valueTo
     * @param orderBy 排序 xxx desc
     * @return 实体列表
     */
    PageInfo<T> selectManyByField(String field, Object valueFrom, Object valueTo, String orderBy, Integer pageNum, Integer pageSize) throws Exception;
}
