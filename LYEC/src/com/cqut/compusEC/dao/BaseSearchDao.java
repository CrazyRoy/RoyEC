package com.cqut.compusEC.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户多表查询和分页的DAO
 * @author Ly
 *
 */
public interface BaseSearchDao {
	public List<Map<String, Object>> searchForeign(
			@Param("properties") String[] properties,
			@Param("baseEntity") String baseEntity,
			@Param("joinEntity") String joinEntity,
			@Param("foreignEntity") String[] foreignEntity,
			@Param("foreingKey") List<String> foreingKeySql,
			@Param("condition") String condition);

	public int getForeignCount(@Param("primaryKey") String primaryKey,
			@Param("baseEntity") String baseEntity,
			@Param("joinEntity") String joinEntity,
			@Param("foreignEntity") String[] foreignEntity,
			@Param("foreingKey") List<String> foreingKeySql,
			@Param("condition") String condition);

	public List<Map<String, Object>> searchWithpagingInMysql(
			@Param("properties") String[] properties,
			@Param("baseEntity") String baseEntity,
			@Param("joinEntity") String joinEntity,// 左链接语句
			@Param("foreignEntity") String[] foreignEntity,
			@Param("foreingKey") List<String> foreingKeySql,
			@Param("condition") String condition,
			@Param("groupField") String groupField,
			@Param("orderField") String orderField,
			@Param("sortMode") String sortMode,
			@Param("startIndex") int startIndex, @Param("pageNum") int pageNum);

	public List<Map<String, Object>> searchWithpagingInOracle(
			@Param("properties") String[] properties,
			@Param("baseEntity") String baseEntity,
			@Param("joinEntity") String joinEntity,
			@Param("foreignEntity") String[] foreignEntity,
			@Param("foreingKey") List<String> foreingKeySql,
			@Param("condition") String condition,
			@Param("groupField") String groupField,
			@Param("orderField") String orderField,
			@Param("sortMode") String sortMode,
			@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);
}
