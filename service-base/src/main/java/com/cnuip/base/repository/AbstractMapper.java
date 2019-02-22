package com.cnuip.base.repository;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public interface AbstractMapper<T, P> {

   /**
    * 插入一条记录
    * @param record 实体
    * @return
    */
   int insert(T record);

   /**
    * 根据主键删除一条记录
    * @param id 主键值
    * @return
    */
   int deleteByKey(@Param("id") Long id);

   /**
    * 根据字段删除一条或多条记录
    * @param field 字段
    * @param valueFrom 字段值范围
    * @param valueTo
    * @return
    */
   int deleteByField(@Param("field") String field, @Param("valueFrom") Object valueFrom, @Param("valueTo") Object valueTo);

   /**
    * 根据条件删除记录
    * @param params MapperParams<T>
    * @return
    */
   int delete(@Param("params") P params);

   /**
    * 根据主键更新一条记录一个字段
    * @param id 主键值
    * @param field 字段
    * @param value 字段值
    * @return
    */
   int updateFieldByKey(@Param("id") Long id, @Param("field") String field, @Param("value") Object value);

   /**
    * 根据主键更新一条记录
    * @param id 主键值
    * @param record 实体
    * @return
    */
   int updateByKey(@Param("id") Long id, @Param("record") T record);

   /**
    * 根据条件更新记录
    * @param params MapperParams<T>
    * @param record 实体
    * @return
    */
   int update(@Param("params") P params, @Param("record") T record);

   /**
    * 根据条件统计记录数
    * @param params MapperParams<T>
    * @return 记录数
    */
   int count(@Param("params") P params);

   /**
    * 根据条件获取所有记录、可排序
    * @param params MapperParams<T>
    * @return 实体列表
    */
   List<T> getAll(@Param("params") P params);

   /**
    * 根据主键获取一条记录
    * @param id 主键值
    * @return 实体
    */
   T selectOneByKey(@Param("id") Long id);

   /**
    * 根据字段获取一条记录
    * @param field 字段
    * @param valueFrom 字段值范围
    * @param valueTo
    * @return 实体
    */
   T selectOneByField(@Param("field") String field, @Param("valueFrom") Object valueFrom, @Param("valueTo") Object valueTo);

   /**
    * 根据条件获取一条记录
    * @param params MapperParams<T>
    * @return 实体
    */
   T selectOne(@Param("params") P params);

   /**
    * 根据条件获取一页记录、可排序
    * @param params MapperParams<T>
    * @param pageNum 当前页号
    * @param pageSize 一页记录数
    * @return 实体列表
    */
   List<T> selectMany(@Param("params") P params, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

   /**
    * 根据字段获取一页记录、可排序
    * @param field 字段
    * @param valueFrom 字段值范围
    * @param valueTo
    * @param orderBy 排序
    * @param pageNum 当前页号
    * @param pageSize 一页记录数
    * @return 实体列表
    */
   List<T> selectManyByField(@Param("field") String field, @Param("valueFrom") Object valueFrom, @Param("valueTo") Object valueTo, @Param("orderBy") String orderBy, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}